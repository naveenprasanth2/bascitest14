#!/bin/bash

# Tailscale Connection Testing Script
# Tests direct connections and validates DuckDNS/Tailscale configuration

set -euo pipefail

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Logging function
log() {
    echo -e "${2:-$GREEN}[$(date '+%Y-%m-%d %H:%M:%S')] $1${NC}"
}

error() {
    log "$1" "$RED"
}

warning() {
    log "$1" "$YELLOW"
}

info() {
    log "$1" "$BLUE"
}

success() {
    log "$1" "$GREEN"
}

# Configuration
CONFIG_FILE="/etc/duckdns/duckdns.conf"
TAILSCALE_STATUS_CMD="tailscale status"
TAILSCALE_NETCHECK_CMD="tailscale netcheck"

# Load DuckDNS configuration if available
load_config() {
    if [[ -f "$CONFIG_FILE" ]]; then
        source "$CONFIG_FILE"
        return 0
    else
        warning "DuckDNS configuration not found at $CONFIG_FILE"
        return 1
    fi
}

# Test DuckDNS resolution
test_duckdns_resolution() {
    info "Testing DuckDNS resolution..."
    
    if [[ -z "${DUCKDNS_DOMAIN:-}" ]]; then
        error "DUCKDNS_DOMAIN not configured"
        return 1
    fi
    
    local domain="${DUCKDNS_DOMAIN}.duckdns.org"
    local resolved_ip
    
    # Test IPv4 resolution
    if resolved_ip=$(dig +short A "$domain" 2>/dev/null | head -1); then
        if [[ -n "$resolved_ip" && "$resolved_ip" =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
            success "IPv4 resolution successful: $domain -> $resolved_ip"
        else
            error "IPv4 resolution failed or returned invalid IP: $resolved_ip"
            return 1
        fi
    else
        error "Failed to resolve IPv4 for $domain"
        return 1
    fi
    
    # Test IPv6 resolution if enabled
    if [[ "${UPDATE_IPV6:-false}" == "true" ]]; then
        if resolved_ip=$(dig +short AAAA "$domain" 2>/dev/null | head -1); then
            if [[ -n "$resolved_ip" ]]; then
                success "IPv6 resolution successful: $domain -> $resolved_ip"
            else
                warning "IPv6 resolution returned empty result"
            fi
        else
            warning "Failed to resolve IPv6 for $domain"
        fi
    fi
    
    return 0
}

# Test current public IP vs DuckDNS
test_ip_consistency() {
    info "Testing IP consistency..."
    
    if [[ -z "${DUCKDNS_DOMAIN:-}" ]]; then
        error "DUCKDNS_DOMAIN not configured"
        return 1
    fi
    
    local domain="${DUCKDNS_DOMAIN}.duckdns.org"
    local current_ip
    local duckdns_ip
    
    # Get current public IP
    current_ip=$(curl -s --max-time 10 "https://ipv4.icanhazip.com" 2>/dev/null | grep -E '^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$')
    if [[ -z "$current_ip" ]]; then
        error "Failed to get current public IP"
        return 1
    fi
    
    # Get DuckDNS resolved IP
    duckdns_ip=$(dig +short A "$domain" 2>/dev/null | head -1)
    if [[ -z "$duckdns_ip" ]]; then
        error "Failed to resolve DuckDNS IP"
        return 1
    fi
    
    if [[ "$current_ip" == "$duckdns_ip" ]]; then
        success "IP consistency check passed: Current IP ($current_ip) matches DuckDNS IP ($duckdns_ip)"
        return 0
    else
        error "IP mismatch: Current IP ($current_ip) != DuckDNS IP ($duckdns_ip)"
        warning "This may indicate DuckDNS needs to be updated"
        return 1
    fi
}

# Test Tailscale status
test_tailscale_status() {
    info "Testing Tailscale status..."
    
    if ! command -v tailscale &> /dev/null; then
        error "Tailscale is not installed"
        return 1
    fi
    
    # Check if Tailscale is running
    if ! $TAILSCALE_STATUS_CMD &> /dev/null; then
        error "Tailscale is not running or accessible"
        return 1
    fi
    
    # Get detailed status
    local status_output
    status_output=$($TAILSCALE_STATUS_CMD 2>/dev/null)
    
    if echo "$status_output" | grep -q "Logged out"; then
        error "Tailscale is logged out"
        return 1
    fi
    
    success "Tailscale is running and authenticated"
    
    # Show current peers
    info "Current Tailscale peers:"
    echo "$status_output" | grep -E "^[0-9]" | head -5 || echo "  No peers found"
    
    return 0
}

# Test Tailscale network connectivity
test_tailscale_netcheck() {
    info "Testing Tailscale network connectivity..."
    
    if ! command -v tailscale &> /dev/null; then
        error "Tailscale is not installed"
        return 1
    fi
    
    local netcheck_output
    if netcheck_output=$($TAILSCALE_NETCHECK_CMD 2>/dev/null); then
        echo "$netcheck_output"
        
        # Check for direct connectivity
        if echo "$netcheck_output" | grep -q "UPnP:\s*true\|NAT:\s*easy\|DERP latency:"; then
            success "Tailscale connectivity test passed"
            
            # Check for relay usage
            if echo "$netcheck_output" | grep -q "DERP" && ! echo "$netcheck_output" | grep -q "direct"; then
                warning "Connection may be using DERP relay instead of direct connection"
            fi
            
            return 0
        else
            warning "Tailscale connectivity may have issues"
            return 1
        fi
    else
        error "Failed to run Tailscale network check"
        return 1
    fi
}

# Test DuckDNS service status
test_duckdns_service() {
    info "Testing DuckDNS service status..."
    
    # Check if systemd service exists and is enabled
    if systemctl list-unit-files | grep -q "duckdns-updater.timer"; then
        if systemctl is-enabled --quiet duckdns-updater.timer; then
            success "DuckDNS timer is enabled"
        else
            warning "DuckDNS timer is not enabled"
        fi
        
        if systemctl is-active --quiet duckdns-updater.timer; then
            success "DuckDNS timer is active"
        else
            error "DuckDNS timer is not active"
            return 1
        fi
        
        # Check recent service runs
        local last_run
        last_run=$(systemctl show duckdns-updater.timer --property=LastTriggerUSec --value)
        if [[ "$last_run" != "n/a" && -n "$last_run" ]]; then
            success "Last DuckDNS update: $last_run"
        else
            warning "DuckDNS timer has not been triggered yet"
        fi
        
    else
        error "DuckDNS service is not installed"
        return 1
    fi
    
    return 0
}

# Test port connectivity
test_port_connectivity() {
    info "Testing port connectivity..."
    
    local test_ports=("22" "443" "80")
    local public_ip
    
    # Get current public IP
    public_ip=$(curl -s --max-time 10 "https://ipv4.icanhazip.com" 2>/dev/null)
    if [[ -z "$public_ip" ]]; then
        warning "Cannot get public IP for port testing"
        return 1
    fi
    
    info "Testing connectivity to public IP: $public_ip"
    
    for port in "${test_ports[@]}"; do
        if timeout 5 nc -z "$public_ip" "$port" 2>/dev/null; then
            success "Port $port is accessible"
        else
            warning "Port $port is not accessible (this may be normal)"
        fi
    done
    
    return 0
}

# Generate connectivity report
generate_report() {
    info "Generating connectivity report..."
    
    local report_file="/tmp/tailscale-connectivity-report-$(date +%Y%m%d-%H%M%S).txt"
    
    {
        echo "Tailscale Connectivity Report"
        echo "Generated on: $(date)"
        echo "=============================================="
        echo
        
        echo "System Information:"
        echo "- OS: $(uname -s -r)"
        echo "- Hostname: $(hostname)"
        echo "- Public IP: $(curl -s --max-time 10 "https://ipv4.icanhazip.com" 2>/dev/null || echo "Unable to determine")"
        echo
        
        if [[ -n "${DUCKDNS_DOMAIN:-}" ]]; then
            echo "DuckDNS Configuration:"
            echo "- Domain: ${DUCKDNS_DOMAIN}.duckdns.org"
            echo "- IPv6 Enabled: ${UPDATE_IPV6:-false}"
            echo
        fi
        
        echo "Tailscale Status:"
        $TAILSCALE_STATUS_CMD 2>/dev/null || echo "Tailscale status unavailable"
        echo
        
        echo "Tailscale Network Check:"
        $TAILSCALE_NETCHECK_CMD 2>/dev/null || echo "Tailscale netcheck unavailable"
        echo
        
        echo "DuckDNS Service Status:"
        systemctl status duckdns-updater.timer 2>/dev/null || echo "DuckDNS service not found"
        echo
        
        echo "Recent DuckDNS Logs:"
        journalctl -u duckdns-updater.service --no-pager -n 10 2>/dev/null || echo "No recent logs found"
        
    } > "$report_file"
    
    success "Report generated: $report_file"
    return 0
}

# Main test function
run_all_tests() {
    info "Starting comprehensive connection tests..."
    echo
    
    local tests_passed=0
    local tests_total=0
    
    # Run all tests
    for test_func in load_config test_duckdns_resolution test_ip_consistency test_tailscale_status test_tailscale_netcheck test_duckdns_service test_port_connectivity; do
        echo
        ((tests_total++))
        if $test_func; then
            ((tests_passed++))
        fi
    done
    
    echo
    echo "=============================================="
    info "Test Summary: $tests_passed/$tests_total tests passed"
    
    if [[ $tests_passed -eq $tests_total ]]; then
        success "All tests passed! Direct connections should be working."
        return 0
    elif [[ $tests_passed -gt $((tests_total / 2)) ]]; then
        warning "Some tests failed, but basic functionality appears to work."
        return 1
    else
        error "Multiple tests failed. Please check your configuration."
        return 2
    fi
}

# Display help
show_help() {
    echo "Tailscale Connection Testing Script"
    echo
    echo "Usage: $0 [OPTIONS]"
    echo
    echo "Options:"
    echo "  --all          Run all tests (default)"
    echo "  --duckdns      Test DuckDNS functionality only"
    echo "  --tailscale    Test Tailscale functionality only"
    echo "  --report       Generate detailed connectivity report"
    echo "  --help         Show this help message"
    echo
    echo "Examples:"
    echo "  $0                    # Run all tests"
    echo "  $0 --duckdns         # Test DuckDNS only"
    echo "  $0 --tailscale       # Test Tailscale only"
    echo "  $0 --report          # Generate detailed report"
}

# Main execution
case "${1:---all}" in
    --all)
        run_all_tests
        ;;
    --duckdns)
        load_config
        test_duckdns_resolution
        test_ip_consistency
        test_duckdns_service
        ;;
    --tailscale)
        test_tailscale_status
        test_tailscale_netcheck
        ;;
    --report)
        generate_report
        ;;
    --help|-h)
        show_help
        ;;
    *)
        error "Unknown option: $1"
        echo
        show_help
        exit 1
        ;;
esac