# DuckDNS Tailscale Troubleshooting Guide

This guide covers common issues and solutions when setting up DuckDNS for Tailscale direct connections.

## Table of Contents

- [Common Issues](#common-issues)
- [DuckDNS Issues](#duckdns-issues)
- [Tailscale Issues](#tailscale-issues)
- [Network Issues](#network-issues)
- [Service Issues](#service-issues)
- [Debugging Tools](#debugging-tools)
- [Log Analysis](#log-analysis)
- [Recovery Procedures](#recovery-procedures)

## Common Issues

### Issue: Script Permission Denied

**Symptoms:**
- `bash: ./setup-duckdns.sh: Permission denied`
- `bash: /usr/local/bin/update-duckdns.sh: Permission denied`

**Solution:**
```bash
# Make scripts executable
sudo chmod +x scripts/setup-duckdns.sh
sudo chmod +x scripts/update-duckdns.sh
sudo chmod +x /usr/local/bin/update-duckdns.sh
```

### Issue: Configuration File Not Found

**Symptoms:**
- `Configuration file not found: /etc/duckdns/duckdns.conf`

**Solution:**
```bash
# Create configuration directory
sudo mkdir -p /etc/duckdns

# Copy and edit configuration
sudo cp config/duckdns.conf.example /etc/duckdns/duckdns.conf
sudo nano /etc/duckdns/duckdns.conf

# Set proper permissions
sudo chmod 600 /etc/duckdns/duckdns.conf
```

### Issue: Systemd Service Not Found

**Symptoms:**
- `Failed to start duckdns-updater.service: Unit duckdns-updater.service not found`

**Solution:**
```bash
# Copy service file
sudo cp systemd/duckdns-updater.service /etc/systemd/system/

# Reload systemd
sudo systemctl daemon-reload

# Enable and start service
sudo systemctl enable duckdns-updater.timer
sudo systemctl start duckdns-updater.timer
```

## DuckDNS Issues

### Issue: DuckDNS API Returns "KO"

**Symptoms:**
- DuckDNS API response: KO
- IP not updating on DuckDNS website

**Possible Causes & Solutions:**

1. **Invalid Token:**
   ```bash
   # Verify token in configuration
   sudo grep DUCKDNS_TOKEN /etc/duckdns/duckdns.conf
   
   # Test with manual curl
   curl "https://www.duckdns.org/update?domains=YOUR_DOMAIN&token=YOUR_TOKEN&ip="
   ```

2. **Invalid Domain:**
   ```bash
   # Check domain name (without .duckdns.org)
   sudo grep DUCKDNS_DOMAIN /etc/duckdns/duckdns.conf
   
   # Verify domain exists on DuckDNS website
   ```

3. **Network Connectivity:**
   ```bash
   # Test internet connectivity
   curl -I https://www.duckdns.org
   
   # Test DNS resolution
   nslookup www.duckdns.org
   ```

### Issue: IP Address Not Updating

**Symptoms:**
- Script runs successfully but IP doesn't change
- DuckDNS shows old IP address

**Debugging Steps:**

1. **Check Current vs Cached IP:**
   ```bash
   # View cached IP
   sudo cat /var/cache/duckdns/last_ip
   
   # Get current public IP
   curl https://ipv4.icanhazip.com
   
   # Force update
   sudo /usr/local/bin/update-duckdns.sh --force
   ```

2. **Verify IP Detection:**
   ```bash
   # Test IP detection services
   curl https://ipv4.icanhazip.com
   curl https://api.ipify.org
   curl https://ifconfig.me/ip
   ```

3. **Clear Cache and Force Update:**
   ```bash
   sudo rm -f /var/cache/duckdns/last_ip
   sudo /usr/local/bin/update-duckdns.sh --force
   ```

### Issue: Domain Not Resolving

**Symptoms:**
- `nslookup` or `dig` returns NXDOMAIN
- Domain doesn't resolve to any IP

**Solution:**
```bash
# Wait for DNS propagation (can take up to 24 hours)
# Test with different DNS servers
nslookup your-domain.duckdns.org 8.8.8.8
nslookup your-domain.duckdns.org 1.1.1.1

# Force update DuckDNS
sudo /usr/local/bin/update-duckdns.sh --force

# Check DuckDNS website for domain status
```

## Tailscale Issues

### Issue: Tailscale Still Using Relays

**Symptoms:**
- `tailscale netcheck` shows DERP relay usage
- No direct connections in `tailscale status`

**Troubleshooting Steps:**

1. **Check NAT and Firewall:**
   ```bash
   # Test if Tailscale port is accessible
   sudo netstat -tulpn | grep 41641
   
   # Check firewall rules
   sudo ufw status
   sudo iptables -L
   ```

2. **Verify Port Forwarding:**
   - Log into router admin panel
   - Check port forwarding rule for UDP 41641
   - Ensure it points to correct internal IP

3. **Test Direct Connectivity:**
   ```bash
   # Get Tailscale node info
   tailscale status
   
   # Test connectivity to specific peer
   tailscale ping PEER_IP
   
   # Check detailed network status
   tailscale netcheck --verbose
   ```

### Issue: Tailscale Can't Reach DuckDNS Domain

**Symptoms:**
- Tailscale works but can't connect via DuckDNS domain
- Direct IP works but domain doesn't

**Solution:**
```bash
# Test domain resolution from Tailscale network
dig your-domain.duckdns.org

# Check if Tailscale is intercepting DNS
tailscale status | grep -i dns

# Try connecting with explicit IP
tailscale ping $(dig +short your-domain.duckdns.org)
```

### Issue: Intermittent Connection Drops

**Symptoms:**
- Direct connections work sometimes
- Fallback to relay connections

**Debugging:**
```bash
# Monitor connection stability
watch -n 5 'tailscale status | head -10'

# Check for IP changes
watch -n 60 'curl -s https://ipv4.icanhazip.com'

# Monitor DuckDNS updates
sudo tail -f /var/log/duckdns/duckdns.log
```

## Network Issues

### Issue: Can't Detect Public IP

**Symptoms:**
- Script fails to get current public IP
- Error: "Failed to retrieve current IPv4 address"

**Solution:**
```bash
# Test different IP detection services
curl --max-time 10 https://ipv4.icanhazip.com
curl --max-time 10 https://api.ipify.org
curl --max-time 10 https://ifconfig.me/ip

# Check proxy settings
echo $http_proxy
echo $https_proxy

# Test with verbose output
curl -v https://ipv4.icanhazip.com
```

### Issue: Firewall Blocking Connections

**Symptoms:**
- Local connections work but external don't
- Port forwarding configured but not working

**Solution:**
```bash
# Check current firewall rules
sudo ufw status verbose
sudo iptables -L -n

# Allow Tailscale port
sudo ufw allow 41641/udp

# Test port accessibility externally
# From another machine:
nmap -p 41641 -sU your-domain.duckdns.org
```

### Issue: ISP NAT Issues

**Symptoms:**
- Behind carrier-grade NAT
- No public IP available

**Solutions:**
1. **Contact ISP** for public IP option
2. **Use VPN with public IP**
3. **Consider cloud relay server**

## Service Issues

### Issue: Systemd Service Fails to Start

**Symptoms:**
- `systemctl start duckdns-updater.service` fails
- Service shows failed status

**Debugging:**
```bash
# Check service status
sudo systemctl status duckdns-updater.service

# View detailed logs
sudo journalctl -u duckdns-updater.service -n 50

# Test script manually
sudo /usr/local/bin/update-duckdns.sh --test

# Check script syntax
bash -n /usr/local/bin/update-duckdns.sh
```

### Issue: Timer Not Triggering

**Symptoms:**
- Service installed but not running automatically
- No regular updates happening

**Solution:**
```bash
# Check timer status
sudo systemctl status duckdns-updater.timer

# List all timers
sudo systemctl list-timers

# Check timer configuration
sudo systemctl cat duckdns-updater.timer

# Restart timer
sudo systemctl restart duckdns-updater.timer
```

### Issue: Service Running But Not Working

**Symptoms:**
- Service shows active but updates fail
- No errors in systemd logs

**Debugging:**
```bash
# Run service manually with verbose output
sudo systemd-run --uid=root /usr/local/bin/update-duckdns.sh --force

# Check service environment
sudo systemctl show duckdns-updater.service

# Verify script execution
sudo strace -f systemctl start duckdns-updater.service
```

## Debugging Tools

### Network Testing Tools

```bash
# Install debugging tools
sudo apt install dnsutils netcat-openbsd tcpdump nmap

# Test DNS resolution
dig your-domain.duckdns.org
nslookup your-domain.duckdns.org

# Test port connectivity
nc -u -v your-domain.duckdns.org 41641

# Monitor network traffic
sudo tcpdump -i any port 41641

# Scan for open ports
nmap -p 1-65535 your-domain.duckdns.org
```

### Service Debugging

```bash
# Enable debug logging for systemd
sudo systemctl edit duckdns-updater.service

# Add override:
[Service]
Environment=DEBUG=1
StandardOutput=journal
StandardError=journal

# Reload and test
sudo systemctl daemon-reload
sudo systemctl restart duckdns-updater.service
```

### Script Debugging

```bash
# Add debug mode to update script
export DEBUG=1
sudo /usr/local/bin/update-duckdns.sh --test

# Run with bash debugging
bash -x /usr/local/bin/update-duckdns.sh --test

# Check script dependencies
ldd /usr/bin/curl
which curl dig
```

## Log Analysis

### Common Log Patterns

**Successful Update:**
```
2024-01-15 10:30:15: Starting DuckDNS update for domain: myserver
2024-01-15 10:30:15: Current IPv4: 203.0.113.100
2024-01-15 10:30:16: Updating DuckDNS...
2024-01-15 10:30:17: DuckDNS update successful
```

**IP Unchanged:**
```
2024-01-15 10:35:15: Starting DuckDNS update for domain: myserver
2024-01-15 10:35:15: Current IPv4: 203.0.113.100
2024-01-15 10:35:15: IP address unchanged, skipping update
```

**Network Error:**
```
2024-01-15 10:40:15: Starting DuckDNS update for domain: myserver
2024-01-15 10:40:25: Failed to retrieve current IPv4 address
```

**API Error:**
```
2024-01-15 10:45:15: Starting DuckDNS update for domain: myserver
2024-01-15 10:45:16: DuckDNS API response: KO
2024-01-15 10:45:16: DuckDNS update failed
```

### Log File Locations

```bash
# DuckDNS logs
sudo tail -f /var/log/duckdns/duckdns.log

# Systemd service logs
sudo journalctl -u duckdns-updater.service -f

# System logs
sudo journalctl -f | grep -i duckdns

# Tailscale logs
sudo journalctl -u tailscaled -f
```

## Recovery Procedures

### Complete Reinstallation

If all else fails, perform a clean reinstall:

```bash
# Stop and disable services
sudo systemctl stop duckdns-updater.timer
sudo systemctl disable duckdns-updater.timer
sudo systemctl stop duckdns-updater.service

# Remove all files
sudo rm -f /etc/systemd/system/duckdns-updater.*
sudo rm -f /usr/local/bin/update-duckdns.sh
sudo rm -rf /etc/duckdns
sudo rm -rf /var/log/duckdns
sudo rm -rf /var/cache/duckdns

# Reload systemd
sudo systemctl daemon-reload

# Reinstall
sudo ./scripts/setup-duckdns.sh
```

### Backup and Restore Configuration

```bash
# Backup current configuration
sudo tar -czf /root/duckdns-backup-$(date +%Y%m%d).tar.gz \
    /etc/duckdns/ \
    /etc/systemd/system/duckdns-updater.* \
    /usr/local/bin/update-duckdns.sh

# Restore from backup
sudo tar -xzf /root/duckdns-backup-20240115.tar.gz -C /
sudo systemctl daemon-reload
sudo systemctl enable duckdns-updater.timer
sudo systemctl start duckdns-updater.timer
```

### Emergency Manual Update

If automated updates fail, update manually:

```bash
# Get current IP
CURRENT_IP=$(curl -s https://ipv4.icanhazip.com)

# Update DuckDNS manually
curl "https://www.duckdns.org/update?domains=YOUR_DOMAIN&token=YOUR_TOKEN&ip=$CURRENT_IP"
```

## Getting Help

If you continue to experience issues:

1. **Check the logs** for specific error messages
2. **Test each component individually** (network, DuckDNS API, Tailscale)
3. **Use the connection test script** for comprehensive diagnostics
4. **Generate a connectivity report** with `./scripts/test-connection.sh --report`
5. **Search existing issues** on GitHub
6. **Create a new issue** with detailed logs and system information

## Prevention Tips

- **Monitor logs regularly** during initial setup
- **Set up health checks** to catch issues early
- **Keep systems updated** for security and stability
- **Document your specific configuration** for future reference
- **Test connectivity after any network changes**
- **Backup configurations** before making changes