# DuckDNS Dynamic IP Setup for Tailscale Direct Connections

This guide will help you set up DuckDNS on your Raspberry Pi to enable Tailscale direct connections by providing a dynamic DNS service that automatically updates with your current public IP address.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Detailed Setup](#detailed-setup)
- [Configuration](#configuration)
- [Testing](#testing)
- [Tailscale Configuration](#tailscale-configuration)
- [Monitoring](#monitoring)
- [Security Considerations](#security-considerations)

## Prerequisites

Before starting, ensure you have:

1. **Raspberry Pi** with internet connection
2. **DuckDNS account** - Sign up at [duckdns.org](https://www.duckdns.org/)
3. **Root access** or sudo privileges
4. **Tailscale installed** on your devices
5. **Basic Linux knowledge**

### System Requirements

- **Operating System**: Any Linux distribution with systemd
- **Required packages**: curl, systemctl, crontab
- **Network**: Internet connectivity with ability to make outbound HTTPS requests
- **Permissions**: Root access for service installation

## Quick Start

For a rapid setup, use the automated installation script:

```bash
# Clone the repository (if not already done)
git clone https://github.com/naveenprasanth2/bascitest14.git
cd bascitest14

# Make the setup script executable
sudo chmod +x scripts/setup-duckdns.sh

# Run the automated setup
sudo ./scripts/setup-duckdns.sh
```

The script will:
1. Install required dependencies
2. Prompt for your DuckDNS domain and token
3. Create configuration files
4. Install and start the systemd service
5. Test the installation

## Detailed Setup

### Step 1: DuckDNS Account Setup

1. Visit [duckdns.org](https://www.duckdns.org/)
2. Sign in with Google, GitHub, or other supported accounts
3. Create a new domain (e.g., `myserver.duckdns.org`)
4. Note your **token** from the top of the page

### Step 2: Install Dependencies

```bash
# On Debian/Ubuntu
sudo apt update
sudo apt install curl

# On RHEL/CentOS/Fedora
sudo yum install curl
# or
sudo dnf install curl

# On Arch Linux
sudo pacman -S curl
```

### Step 3: Download and Install Scripts

```bash
# Create necessary directories
sudo mkdir -p /etc/duckdns /var/log/duckdns /var/cache/duckdns

# Copy update script to system location
sudo cp scripts/update-duckdns.sh /usr/local/bin/
sudo chmod +x /usr/local/bin/update-duckdns.sh
```

### Step 4: Create Configuration

```bash
# Copy and edit configuration template
sudo cp config/duckdns.conf.example /etc/duckdns/duckdns.conf
sudo nano /etc/duckdns/duckdns.conf
```

Edit the configuration file with your details:

```bash
DUCKDNS_DOMAIN="your-domain-name"      # Without .duckdns.org
DUCKDNS_TOKEN="your-duckdns-token"     # From DuckDNS website
UPDATE_IPV4="true"                     # Enable IPv4 updates
UPDATE_IPV6="false"                    # Enable IPv6 if needed
LOG_FILE="/var/log/duckdns/duckdns.log"
FORCE_UPDATE="false"
```

Set appropriate permissions:

```bash
sudo chmod 600 /etc/duckdns/duckdns.conf
sudo chmod 755 /var/log/duckdns /var/cache/duckdns
```

### Step 5: Install Systemd Service

```bash
# Copy service file
sudo cp systemd/duckdns-updater.service /etc/systemd/system/

# Create timer file (for automatic updates every 5 minutes)
sudo tee /etc/systemd/system/duckdns-updater.timer << EOF
[Unit]
Description=Run DuckDNS updater every 5 minutes
Requires=duckdns-updater.service

[Timer]
OnBootSec=5min
OnUnitActiveSec=5min
AccuracySec=1min

[Install]
WantedBy=timers.target
EOF

# Reload systemd and enable service
sudo systemctl daemon-reload
sudo systemctl enable duckdns-updater.timer
sudo systemctl start duckdns-updater.timer
```

## Configuration

### DuckDNS Configuration Options

The configuration file `/etc/duckdns/duckdns.conf` supports these options:

| Option | Description | Default | Example |
|--------|-------------|---------|---------|
| `DUCKDNS_DOMAIN` | Your DuckDNS domain (without .duckdns.org) | Required | `myserver` |
| `DUCKDNS_TOKEN` | Authentication token from DuckDNS | Required | `12345678-1234-1234-1234-123456789012` |
| `UPDATE_IPV4` | Enable IPv4 address updates | `true` | `true`/`false` |
| `UPDATE_IPV6` | Enable IPv6 address updates | `false` | `true`/`false` |
| `LOG_FILE` | Path to log file | `/var/log/duckdns/duckdns.log` | Custom path |
| `FORCE_UPDATE` | Force update even if IP unchanged | `false` | `true`/`false` |

### Systemd Timer Configuration

To change the update interval, edit the timer file:

```bash
sudo systemctl edit duckdns-updater.timer
```

Add your custom configuration:

```ini
[Timer]
OnBootSec=10min
OnUnitActiveSec=10min
```

## Testing

### Test DuckDNS Updates

```bash
# Test the update script manually
sudo /usr/local/bin/update-duckdns.sh --test

# Force an update
sudo /usr/local/bin/update-duckdns.sh --force

# Check if domain resolves correctly
nslookup your-domain.duckdns.org

# Verify IP matches your current public IP
curl https://ipv4.icanhazip.com
```

### Test Service Status

```bash
# Check timer status
sudo systemctl status duckdns-updater.timer

# Check service logs
sudo journalctl -u duckdns-updater.service -f

# View DuckDNS logs
sudo tail -f /var/log/duckdns/duckdns.log
```

### Comprehensive Testing

Use the provided test script:

```bash
# Run all connectivity tests
./scripts/test-connection.sh

# Test only DuckDNS functionality
./scripts/test-connection.sh --duckdns

# Generate detailed report
./scripts/test-connection.sh --report
```

## Tailscale Configuration

### Setting Up Direct Connections

Once DuckDNS is working, configure Tailscale to use direct connections:

1. **Set up advertise routes** (if needed):
   ```bash
   sudo tailscale up --advertise-routes=192.168.1.0/24 --accept-routes
   ```

2. **Configure exit node** (optional):
   ```bash
   sudo tailscale up --advertise-exit-node
   ```

3. **Set custom domain** for easier access:
   ```bash
   # This is typically done through Tailscale admin console
   # or by configuring MagicDNS with your DuckDNS domain
   ```

### Port Forwarding

Configure your router to forward the Tailscale port (typically 41641) to your Raspberry Pi:

1. Access your router's configuration page
2. Navigate to Port Forwarding settings
3. Add rule: External Port 41641 → Internal IP (Pi) Port 41641 (UDP)
4. Save and restart router if needed

### Verify Direct Connections

```bash
# Check Tailscale status and connection types
tailscale status

# Perform network connectivity test
tailscale netcheck

# Monitor for direct vs relay connections
tailscale ping <peer-ip>
```

## Monitoring

### Log Files

Monitor these log files for issues:

```bash
# DuckDNS update logs
sudo tail -f /var/log/duckdns/duckdns.log

# Systemd service logs
sudo journalctl -u duckdns-updater.service -f

# System logs for networking issues
sudo journalctl -f | grep -i network
```

### Health Checks

Set up regular health checks:

```bash
# Create monitoring script
cat > /usr/local/bin/duckdns-health-check.sh << 'EOF'
#!/bin/bash
DOMAIN="your-domain.duckdns.org"
CURRENT_IP=$(curl -s https://ipv4.icanhazip.com)
RESOLVED_IP=$(dig +short $DOMAIN)

if [[ "$CURRENT_IP" != "$RESOLVED_IP" ]]; then
    echo "WARNING: IP mismatch detected!"
    echo "Current: $CURRENT_IP, Resolved: $RESOLVED_IP"
    # Force update
    /usr/local/bin/update-duckdns.sh --force
fi
EOF

sudo chmod +x /usr/local/bin/duckdns-health-check.sh

# Add to crontab for regular checks
echo "*/15 * * * * /usr/local/bin/duckdns-health-check.sh" | sudo crontab -
```

### Alerting

Set up email alerts for failures:

```bash
# Install mail utilities
sudo apt install mailutils

# Modify the health check script to send alerts
# Add this to the health check script:
# echo "DuckDNS IP mismatch on $(hostname)" | mail -s "DuckDNS Alert" admin@yourdomain.com
```

## Security Considerations

### File Permissions

Ensure proper file permissions:

```bash
# Configuration file should be readable only by root
sudo chmod 600 /etc/duckdns/duckdns.conf

# Log directories should be accessible
sudo chmod 755 /var/log/duckdns /var/cache/duckdns

# Scripts should be executable by root only
sudo chmod 700 /usr/local/bin/update-duckdns.sh
```

### Network Security

1. **Firewall Configuration**:
   ```bash
   # Allow Tailscale port
   sudo ufw allow 41641/udp
   
   # Allow SSH (if needed)
   sudo ufw allow ssh
   
   # Enable firewall
   sudo ufw enable
   ```

2. **DuckDNS Token Security**:
   - Never share your DuckDNS token
   - Store it only in the configuration file with restricted permissions
   - Consider rotating the token periodically

3. **Regular Updates**:
   ```bash
   # Keep system updated
   sudo apt update && sudo apt upgrade
   
   # Monitor for security updates
   sudo apt list --upgradable
   ```

### Backup and Recovery

Create backups of your configuration:

```bash
# Backup configuration
sudo cp /etc/duckdns/duckdns.conf /etc/duckdns/duckdns.conf.backup.$(date +%Y%m%d)

# Backup systemd files
sudo cp /etc/systemd/system/duckdns-updater.* /root/duckdns-backup/
```

## Troubleshooting

If you encounter issues, refer to the [Troubleshooting Guide](TROUBLESHOOTING.md) for detailed solutions to common problems.

## Next Steps

1. **Configure Tailscale clients** to use the new direct connections
2. **Test performance** and compare with relay connections
3. **Monitor logs** for the first few days to ensure stability
4. **Set up monitoring** and alerting for production use
5. **Document your specific configuration** for future reference

## Support

- [DuckDNS Documentation](https://www.duckdns.org/spec.jsp)
- [Tailscale Documentation](https://tailscale.com/kb/)
- [GitHub Issues](https://github.com/naveenprasanth2/bascitest14/issues) for this project