# DuckDNS Dynamic IP Update for Tailscale Direct Connections

This repository provides a comprehensive solution for setting up DuckDNS dynamic DNS service on Raspberry Pi to enable Tailscale direct connections. By automatically updating your public IP address with DuckDNS, you can bypass DERP relays and establish direct peer-to-peer connections through Tailscale.

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/naveenprasanth2/bascitest14.git
cd bascitest14

# Run the automated setup (requires sudo)
sudo chmod +x scripts/setup-duckdns.sh
sudo ./scripts/setup-duckdns.sh
```

The setup script will guide you through:
- Creating a DuckDNS account and domain
- Installing and configuring the update service
- Setting up automatic IP updates every 5 minutes
- Testing the installation

## 📋 What This Solves

**Problem**: Tailscale connections often use DERP relays instead of direct connections, leading to:
- Higher latency
- Reduced bandwidth
- Dependency on Tailscale's relay servers

**Solution**: DuckDNS provides a dynamic DNS service that:
- Automatically updates when your public IP changes
- Enables Tailscale to establish direct connections
- Works reliably after server restarts or network changes
- Provides better performance and reduced latency

## 🏗️ Architecture

```
[Internet] → [Router/NAT] → [Raspberry Pi with DuckDNS]
                                     ↓
[DuckDNS Service] ← [Dynamic IP Updates] ← [systemd Timer]
                                     ↓
[your-domain.duckdns.org] → [Direct Tailscale Connections]
```

## 📁 Repository Structure

```
├── scripts/
│   ├── setup-duckdns.sh      # Automated installation script
│   ├── update-duckdns.sh     # DuckDNS IP update script
│   └── test-connection.sh    # Connection testing and validation
├── systemd/
│   └── duckdns-updater.service  # Systemd service definition
├── config/
│   └── duckdns.conf.example     # Configuration template
├── docs/
│   ├── SETUP.md                 # Detailed setup guide
│   └── TROUBLESHOOTING.md       # Troubleshooting guide
└── README.md                    # This file
```

## 🔧 Features

### Automated Setup
- **One-command installation** with guided configuration
- **Dependency detection** and automatic installation
- **Service validation** with comprehensive testing
- **Error handling** with clear diagnostic messages

### Robust Operation
- **Multiple IP detection services** for redundancy
- **Intelligent caching** to avoid unnecessary updates
- **Automatic retry logic** for failed requests
- **Comprehensive logging** for troubleshooting

### Security & Reliability
- **Systemd integration** with automatic restarts
- **Secure configuration** with proper file permissions
- **Health monitoring** and status checking
- **Clean uninstallation** option

### Monitoring & Testing
- **Connection validation** scripts
- **Performance testing** tools
- **Detailed reporting** for diagnostics
- **Log analysis** utilities

## 📖 Documentation

- **[Setup Guide](docs/SETUP.md)** - Comprehensive installation and configuration
- **[Troubleshooting](docs/TROUBLESHOOTING.md)** - Common issues and solutions

## 🎯 Prerequisites

- **Raspberry Pi** (or any Linux system with systemd)
- **Internet connection** with outbound HTTPS access
- **DuckDNS account** ([sign up free](https://www.duckdns.org/))
- **Tailscale installed** on your devices
- **Root/sudo access** for installation

## ⚡ Quick Commands

### Installation
```bash
sudo ./scripts/setup-duckdns.sh          # Full installation
sudo ./scripts/setup-duckdns.sh --help   # Show help
```

### Testing
```bash
./scripts/test-connection.sh             # Run all tests
./scripts/test-connection.sh --duckdns   # Test DuckDNS only
./scripts/test-connection.sh --tailscale # Test Tailscale only
./scripts/test-connection.sh --report    # Generate report
```

### Manual Operations
```bash
sudo /usr/local/bin/update-duckdns.sh --force    # Force update
sudo /usr/local/bin/update-duckdns.sh --test     # Test connectivity
sudo systemctl status duckdns-updater.timer      # Check service
sudo journalctl -u duckdns-updater.service -f    # View logs
```

### Uninstallation
```bash
sudo ./scripts/setup-duckdns.sh --uninstall      # Remove everything
```

## 🔧 Configuration

The main configuration is stored in `/etc/duckdns/duckdns.conf`:

```bash
DUCKDNS_DOMAIN="your-domain-name"    # Your DuckDNS domain
DUCKDNS_TOKEN="your-token-here"      # Authentication token
UPDATE_IPV4="true"                   # Enable IPv4 updates
UPDATE_IPV6="false"                  # Enable IPv6 updates
FORCE_UPDATE="false"                 # Only update on IP change
```

## 📊 Monitoring

### Service Status
```bash
systemctl status duckdns-updater.timer   # Timer status
systemctl list-timers | grep duckdns     # Next run time
```

### Logs
```bash
tail -f /var/log/duckdns/duckdns.log         # DuckDNS logs
journalctl -u duckdns-updater.service -f    # Service logs
```

### Health Checks
```bash
# Test domain resolution
nslookup your-domain.duckdns.org

# Check IP consistency
current_ip=$(curl -s https://ipv4.icanhazip.com)
duckdns_ip=$(dig +short your-domain.duckdns.org)
echo "Current: $current_ip, DuckDNS: $duckdns_ip"
```

## 🔍 Troubleshooting

### Common Issues

1. **Service not starting**: Check logs with `journalctl -u duckdns-updater.service`
2. **IP not updating**: Verify token and domain in `/etc/duckdns/duckdns.conf`
3. **Direct connections not working**: Check router port forwarding for UDP 41641
4. **Domain not resolving**: Wait for DNS propagation (up to 24 hours)

### Quick Diagnostics
```bash
# Run comprehensive tests
./scripts/test-connection.sh

# Test DuckDNS API manually
curl "https://www.duckdns.org/update?domains=YOUR_DOMAIN&token=YOUR_TOKEN&ip="

# Check Tailscale connectivity
tailscale netcheck
tailscale status
```

For detailed troubleshooting, see [TROUBLESHOOTING.md](docs/TROUBLESHOOTING.md).

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues and pull requests.

### Development Setup
```bash
git clone https://github.com/naveenprasanth2/bascitest14.git
cd bascitest14

# Test scripts syntax
bash -n scripts/*.sh

# Run tests in dry-run mode
sudo DEBUG=1 ./scripts/setup-duckdns.sh
```

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

- [DuckDNS](https://www.duckdns.org/) for providing free dynamic DNS service
- [Tailscale](https://tailscale.com/) for their excellent VPN solution
- The open source community for tools and inspiration

## 📞 Support

- **Documentation**: Check [docs/](docs/) directory
- **Issues**: [GitHub Issues](https://github.com/naveenprasanth2/bascitest14/issues)
- **Discussions**: [GitHub Discussions](https://github.com/naveenprasanth2/bascitest14/discussions)

---

**🎉 Get started now and enjoy faster, more reliable Tailscale connections!**