#!/bin/bash

# DuckDNS Dynamic IP Update Script
# Updates DuckDNS subdomain with current public IP address
# Supports both IPv4 and IPv6 updates

# Configuration file path
CONFIG_FILE="/etc/duckdns/duckdns.conf"

# Load configuration (only if not showing help)
load_config() {
    if [[ -f "$CONFIG_FILE" ]]; then
        source "$CONFIG_FILE"
    else
        echo "$(date): Configuration file not found: $CONFIG_FILE" >&2
        exit 1
    fi
}

# Validate configuration (separate from loading)
validate_config() {
    if [[ -z "$DUCKDNS_DOMAIN" || -z "$DUCKDNS_TOKEN" ]]; then
        echo "$(date): Missing required configuration: DUCKDNS_DOMAIN or DUCKDNS_TOKEN" >&2
        exit 1
    fi
}

# Logging function
log_message() {
    echo "$(date '+%Y-%m-%d %H:%M:%S'): $1"
    if [[ -n "$LOG_FILE" ]]; then
        echo "$(date '+%Y-%m-%d %H:%M:%S'): $1" >> "$LOG_FILE"
    fi
}

# Function to get current public IPv4
get_public_ipv4() {
    local ip
    # Try multiple services for redundancy
    for service in "https://ipv4.icanhazip.com" "https://api.ipify.org" "https://ifconfig.me/ip"; do
        ip=$(curl -s --max-time 10 "$service" 2>/dev/null | grep -E '^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$')
        if [[ -n "$ip" ]]; then
            echo "$ip"
            return 0
        fi
    done
    return 1
}

# Function to get current public IPv6
get_public_ipv6() {
    local ip
    # Try multiple services for redundancy
    for service in "https://ipv6.icanhazip.com" "https://api6.ipify.org"; do
        ip=$(curl -s --max-time 10 "$service" 2>/dev/null | grep -E '^[0-9a-fA-F:]+$')
        if [[ -n "$ip" ]]; then
            echo "$ip"
            return 0
        fi
    done
    return 1
}

# Function to update DuckDNS
update_duckdns() {
    local ip="$1"
    local ipv6="$2"
    local url="https://www.duckdns.org/update?domains=${DUCKDNS_DOMAIN}&token=${DUCKDNS_TOKEN}"
    
    if [[ -n "$ip" ]]; then
        url="${url}&ip=${ip}"
    fi
    
    if [[ -n "$ipv6" ]]; then
        url="${url}&ipv6=${ipv6}"
    fi
    
    local response
    response=$(curl -s --max-time 30 "$url" 2>/dev/null)
    
    if [[ "$response" == "OK" ]]; then
        return 0
    else
        echo "DuckDNS API response: $response" >&2
        return 1
    fi
}

# Main execution
main() {
    load_config
    validate_config
    log_message "Starting DuckDNS update for domain: $DUCKDNS_DOMAIN"
    
    local current_ipv4
    local current_ipv6
    local update_needed=false
    local cache_file="/var/cache/duckdns/last_ip"
    
    # Create cache directory if it doesn't exist
    mkdir -p "$(dirname "$cache_file")"
    
    # Get current public IPs
    if [[ "${UPDATE_IPV4:-true}" == "true" ]]; then
        current_ipv4=$(get_public_ipv4)
        if [[ -n "$current_ipv4" ]]; then
            log_message "Current IPv4: $current_ipv4"
        else
            log_message "Failed to retrieve current IPv4 address"
        fi
    fi
    
    if [[ "${UPDATE_IPV6:-false}" == "true" ]]; then
        current_ipv6=$(get_public_ipv6)
        if [[ -n "$current_ipv6" ]]; then
            log_message "Current IPv6: $current_ipv6"
        else
            log_message "Failed to retrieve current IPv6 address"
        fi
    fi
    
    # Check if update is needed (compare with cached values)
    if [[ -f "$cache_file" ]]; then
        local cached_ip
        cached_ip=$(cat "$cache_file" 2>/dev/null)
        if [[ "$cached_ip" != "${current_ipv4:-}:${current_ipv6:-}" ]]; then
            update_needed=true
        fi
    else
        update_needed=true
    fi
    
    # Force update if requested
    if [[ "${FORCE_UPDATE:-false}" == "true" ]]; then
        update_needed=true
        log_message "Force update requested"
    fi
    
    if [[ "$update_needed" == "true" ]]; then
        if [[ -n "$current_ipv4" || -n "$current_ipv6" ]]; then
            log_message "Updating DuckDNS..."
            if update_duckdns "$current_ipv4" "$current_ipv6"; then
                log_message "DuckDNS update successful"
                # Cache the current IPs
                echo "${current_ipv4:-}:${current_ipv6:-}" > "$cache_file"
                exit 0
            else
                log_message "DuckDNS update failed"
                exit 1
            fi
        else
            log_message "No valid IP addresses found for update"
            exit 1
        fi
    else
        log_message "IP address unchanged, skipping update"
        exit 0
    fi
}

# Handle script arguments
case "${1:-}" in
    --force)
        FORCE_UPDATE=true
        main
        ;;
    --test)
        echo "Testing DuckDNS connectivity..."
        load_config
        validate_config
        if current_ipv4=$(get_public_ipv4); then
            echo "Current IPv4: $current_ipv4"
        else
            echo "Failed to get IPv4"
        fi
        if [[ "${UPDATE_IPV6:-false}" == "true" ]] && current_ipv6=$(get_public_ipv6); then
            echo "Current IPv6: $current_ipv6"
        fi
        ;;
    --help|-h)
        echo "Usage: $0 [--force|--test|--help]"
        echo "  --force    Force update even if IP hasn't changed"
        echo "  --test     Test connectivity and show current IPs"
        echo "  --help     Show this help message"
        ;;
    "")
        main
        ;;
    *)
        echo "Unknown option: $1" >&2
        echo "Use --help for usage information" >&2
        exit 1
        ;;
esac