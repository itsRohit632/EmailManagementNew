import { LocationOn, Email, Phone } from '@mui/icons-material'; // Import Material Icons

const Footer = () => {
    return (
      <footer className="site-footer">
        <div className="container">
          <div className="footer-grid">
            {/* Get in Touch Section */}
            <div className="get-in-touch">
              <h4>Get in Touch</h4>
              <div className="info-item">
                <LocationOn className="icon" />
                <p>201 E Academy St, Fuquay Varina, North Carolina, 27526</p>
              </div>
              <div className="info-item">
                <Email className="icon" />
                <p>
                  <a href="mailto:dj@stemsolllc.com">dj@stemsolllc.com</a>
                </p>
              </div>
              <div className="info-item">
                <Phone className="icon" />
                <p>
                  <a href="tel:+19104038482">+1 910-403-8482</a>
                </p>
              </div>
            </div>

            {/* Footer columns here */}
            <div className="copyright">
              <p>Copyright © 2025 Stem Sol LLC. All rights reserved.</p>
            </div>
          </div>
        </div>
      </footer>
    );
  };

export default Footer;