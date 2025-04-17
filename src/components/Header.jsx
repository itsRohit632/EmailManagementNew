import React from 'react';
import logo from '../assets/Stem_logo.png';
import { AppBar, Toolbar, Box, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Header = () => {
    return (
        <AppBar
            position="static"
            sx={{
                boxShadow: 'none',
                background: 'linear-gradient(90deg,rgb(204, 194, 147) 0%,rgb(206, 154, 115) 100%)', // Gradient color
            }}
        >
            <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                {/* Logo Section */}
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <RouterLink to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                        <img 
                            src={logo}
                            alt="Stem Sol LLC"
                            style={{
                                width: '150px', // Set a fixed width for the logo
                                height: 'auto', // Maintain aspect ratio
                            }}
                        />
                    </RouterLink>
                </Box>

                {/* Navigation Section */}
                <Box sx={{ display: 'flex', gap: 2 }}>
                    <Button
                        component={RouterLink}
                        to="/contact"
                        sx={{
                            color: 'rgba(122, 9, 9, 0.62)',
                            textTransform: 'none',
                            fontWeight: 'bold',
                            fontSize: '16px',
                            '&:hover': { textDecoration: 'underline' },
                        }}
                    >
                        Contact
                    </Button>
                    <Button
                        component={RouterLink}
                        to="/login"
                        sx={{
                            color: 'rgba(122, 9, 9, 0.62)', // Adjust text color for better visibility
                            textTransform: 'none',
                            fontWeight: 'bold',
                            fontSize: '16px',
                            border: '2px solid rgba(122, 9, 9, 0.62)', // Add border
                            borderRadius: '8px', // Rounded corners
                            padding: '5px 15px', // Add padding
                            transition: 'transform 0.3s ease, box-shadow 0.3s ease', // Add smooth transition
                            '&:hover': {
                                textDecoration: 'underline',
                                transform: 'translateY(-3px)', // Floating effect
                                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)', // Add shadow on hover
                            },
                        }}
                    >
                        Login
                    </Button>
                    <Button
                        component={RouterLink}
                        to="/register"
                        sx={{
                            color: 'rgba(122, 9, 9, 0.62)', // Adjust text color for better visibility
                            textTransform: 'none',
                            fontWeight: 'bold',
                            fontSize: '16px',
                            border: '2px solid rgba(122, 9, 9, 0.62)', // Add border
                            borderRadius: '8px', // Rounded corners
                            padding: '5px 15px', // Add padding
                            transition: 'transform 0.3s ease, box-shadow 0.3s ease', // Add smooth transition
                            '&:hover': {
                                textDecoration: 'underline',
                                transform: 'translateY(-3px)', // Floating effect
                                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)', // Add shadow on hover
                            },
                        }}
                    >
                        Register
                    </Button>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Header;