import React, { useState } from 'react';
import {
    Box,
    Typography,
    Button,
    AppBar,
    Toolbar,
    Drawer,
    List,
    ListItem,
    ListItemText,
    ListItemIcon,
    Divider,
} from '@mui/material';
import { Dashboard, People, QueryBuilder, Settings, Logout, School, Description, RateReview, ScreenSearchDesktop, Campaign, BarChart } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import { Link as RouterLink } from 'react-router-dom'; // Import RouterLink
import logo from '../assets/Stem_logo.png'; // Import logo
import ManageUsers from './ManageUsers'; // Import ManageUsers page
import { motion } from 'framer-motion'; // Import Framer Motion for animations

const Header = ({ onLogout }) => {
    return (
        <AppBar
            position="fixed" // Change to fixed to ensure it stays at the top
            sx={{
                boxShadow: 'none',
                background: 'linear-gradient(90deg,rgb(101, 81, 150) 0%,rgb(51, 147, 160) 100%)',
                zIndex: (theme) => theme.zIndex.drawer + 1, // Ensure it stays above the sidebar
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

                {/* Logout Button */}
                <Button
                    variant="contained"
                    onClick={onLogout}
                    sx={{
                        background: 'linear-gradient(90deg, #ff7e5f, #feb47b)',
                        color: '#fff',
                        textTransform: 'none',
                        fontWeight: 'bold',
                        '&:hover': { background: 'linear-gradient(90deg, #ff6a4d, #fd9a6e)' },
                    }}
                >
                    Logout
                </Button>
            </Toolbar>
        </AppBar>
    );
};

const AdminDashboard = () => {
    const navigate = useNavigate();
    const [activeContent, setActiveContent] = useState('Dashboard'); // State to track active content

    const handleLogout = () => {
        alert('Logged out successfully!');
        navigate('/admin-login');
    };

    const renderContent = () => {
        switch (activeContent) {
            case 'Dashboard':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color: 'rgb(101, 81, 150)' }}>
                                Dashboard
                            </Typography>
                            <Typography variant="body1">Welcome to the Admin Dashboard!</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Manage Users':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <ManageUsers />
                    </motion.div>
                );
            case 'Submitted Queries':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) '}}>
                                Submitted Queries
                            </Typography>
                            <Typography variant="body1">Here you can view all submitted queries.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Pipeline Candidates':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Pipeline Candidates for Training
                            </Typography>
                            <Typography variant="body1">Manage pipeline candidates for training here.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Under Training':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Under Training
                            </Typography>
                            <Typography variant="body1">View candidates currently under training.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Resume Preparation':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Resume Preparation
                            </Typography>
                            <Typography variant="body1">Manage resume preparation tasks here.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Resume Review':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Resume Review
                            </Typography>
                            <Typography variant="body1">Review resumes submitted by candidates.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Screening Interview':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Screening Interview
                            </Typography>
                            <Typography variant="body1">Manage screening interviews here.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Candidates Marketing':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Candidates Marketing
                            </Typography>
                            <Typography variant="body1">Handle marketing for candidates here.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Marketing Statistics':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2, color:'rgb(101, 81, 150) ' }}>
                                Marketing Statistics
                            </Typography>
                            <Typography variant="body1">View marketing statistics and analytics.</Typography>
                        </Box>
                    </motion.div>
                );
            case 'Settings':
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2 }}>
                                Settings
                            </Typography>
                            <Typography variant="body1">Configure application settings here.</Typography>
                        </Box>
                    </motion.div>
                );
            default:
                return (
                    <motion.div
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                        transition={{ duration: 0.3 }}
                    >
                        <Box>
                            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2 }}>
                                Welcome
                            </Typography>
                            <Typography variant="body1">Select an option from the sidebar to get started.</Typography>
                        </Box>
                    </motion.div>
                );
        }
    };

    return (
        <>
            <Header onLogout={handleLogout} /> {/* Pass the logout handler to the Header */}
            <Box
                sx={{
                    display: 'flex',
                    minHeight: '100vh',
                    background: 'linear-gradient(135deg, rgba(228, 223, 236, 0.9) 0%, rgba(165, 136, 140, 0.9) 100%)', // Modern gradient background
                    pt: 12, // Padding to account for the fixed header
                    color: '#fff', // Ensure text remains readable
                    overflow: 'hidden', // Prevent content overflow
                }}
            >
                {/* Sidebar */}
                <Drawer
                    variant="permanent"
                    sx={{
                        width: 280,
                        flexShrink: 0,
                        [`& .MuiDrawer-paper`]: {
                            width: 280,
                            boxSizing: 'border-box',
                            background: 'linear-gradient(180deg, rgba(131, 91, 206, 0.8) 0%, rgba(124, 126, 12, 0.8) 100%)', // Gradient background
                            color: '#fff', // Ensure text remains readable
                        },
                    }}
                >
                    <Toolbar />
                    <Box sx={{ overflow: 'auto', mt: 6 }}>
                        <List>
                            { [
                                { label: 'Dashboard', icon: <Dashboard /> },
                                { label: 'Manage Users', icon: <People /> },
                                { label: 'Submitted Queries', icon: <QueryBuilder /> },
                                { label: 'Pipeline Candidates', icon: <People /> },
                                { label: 'Under Training', icon: <School /> },
                                { label: 'Resume Preparation', icon: <Description /> },
                                { label: 'Resume Review', icon: <RateReview /> },
                                { label: 'Screening Interview', icon: <ScreenSearchDesktop /> },
                                { label: 'Candidates Marketing', icon: <Campaign /> },
                                { label: 'Marketing Statistics', icon: <BarChart /> },
                                { label: 'Settings', icon: <Settings /> },
                            ].map((item) => (
                                <ListItem
                                    button
                                    key={item.label}
                                    onClick={() => setActiveContent(item.label)}
                                    sx={{
                                        backgroundColor: activeContent === item.label ? 'rgba(255, 255, 255, 0.2)' : 'transparent',
                                        '&:hover': { backgroundColor: 'rgba(255, 255, 255, 0.1)' },
                                        transition: 'background-color 0.3s ease',
                                    }}
                                >
                                    <ListItemIcon sx={{ color: '#fff' }}>{item.icon}</ListItemIcon>
                                    <ListItemText
                                        primary={item.label}
                                        sx={{
                                            color: activeContent === item.label ? '#ffd700' : '#fff',
                                            fontWeight: activeContent === item.label ? 'bold' : 'normal',
                                        }}
                                    />
                                </ListItem>
                            ))}
                        </List>
                    </Box>
                </Drawer>

                {/* Main Content */}
                <Box sx={{ flexGrow: 1, p: 3 }}> {/* Removed mt: 8 since pt: 8 is applied to the parent */}
                    {renderContent()} {/* Render content dynamically based on activeContent */}
                </Box>
            </Box>
        </>
    );
};

export default AdminDashboard;
