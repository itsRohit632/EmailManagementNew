import { useState } from 'react';
import {
    Box,
    Typography,
    TextField,
    Button,
    Link,
    CircularProgress,
    Fade, // Import Fade transition
    Slide // Import Slide transition
} from '@mui/material';
import Header from '../components/Header';
import Footer from '../components/Footer';
import axios from 'axios';

const AdminLogin = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false); // State to handle loading

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage('');
        setLoading(true); // Set loading to true

        try {
            const baseURL = 'http://localhost:8080/api/admin/auth';
            const response = await axios.post(`${baseURL}/login`, {
                email: formData.email,
                password: formData.password
            });
            alert('Admin login successful!');
            console.log('Admin data:', response.data);
        } catch (error) {
            setErrorMessage(error.response?.data || 'An error occurred. Please try again.');
        } finally {
            setLoading(false); // Set loading to false
        }
    };

    return (
        <Box sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
            <Header />
            <Box
                sx={{
                    flex: 1,
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    background: 'linear-gradient(135deg,rgb(54, 187, 140),rgb(8, 36, 84))',
                    padding: 4,
                }}
            >
                <Slide direction="up" in={!loading} mountOnEnter unmountOnExit>
                    <Box
                        sx={{
                            width: '100%',
                            maxWidth: '400px',
                            background: 'rgba(127, 11, 11, 0.4)',
                            borderRadius: '16px',
                            padding: 4,
                            boxShadow: '0 8px 16px rgba(133, 35, 178, 0.5)',
                            transition: 'transform 0.3s ease, box-shadow 0.3s ease',
                            '&:hover': {
                                transform: 'scale(1.02)',
                                boxShadow: '0 12px 24px rgba(133, 35, 178, 0.7)',
                            },
                        }}
                    >
                        <Fade in={!!errorMessage} timeout={500}>
                            <Typography
                                variant="body2"
                                sx={{ color: 'red', mb: 2, textAlign: 'center' }}
                            >
                                {errorMessage}
                            </Typography>
                        </Fade>
                        <Typography
                            variant="h4"
                            sx={{ fontWeight: 'bold', mb: 3, textAlign: 'center', color: 'rgba(8, 28, 129, 0.86)' }}
                        >
                            Admin Login
                        </Typography>
                        <Box component="form" onSubmit={handleSubmit}>
                            <TextField
                                fullWidth
                                label="Email address"
                                name="email"
                                type="email"
                                value={formData.email}
                                onChange={handleChange}
                                margin="normal"
                                InputProps={{
                                    style: { color: '#fff' },
                                }}
                                InputLabelProps={{
                                    style: { color: '#aaa' },
                                }}
                                sx={{
                                    background: 'rgba(52, 60, 124, 0.53)',
                                    borderRadius: '8px',
                                    mb: 2,
                                    '&:hover': {
                                        background: 'rgba(255, 255, 255, 0.2)',
                                    },
                                }}
                                required
                            />
                            <TextField
                                fullWidth
                                label="Password"
                                name="password"
                                type="password"
                                value={formData.password}
                                onChange={handleChange}
                                margin="normal"
                                InputProps={{
                                    style: { color: '#fff' },
                                }}
                                InputLabelProps={{
                                    style: { color: '#aaa' },
                                }}
                                sx={{
                                    background: 'rgba(52, 60, 124, 0.53)',
                                    borderRadius: '8px',
                                    mb: 2,
                                    '&:hover': {
                                        background: 'rgba(255, 255, 255, 0.2)',
                                    },
                                }}
                                required
                            />
                            <Button
                                type="submit"
                                variant="contained"
                                fullWidth
                                disabled={loading} // Disable button while loading
                                sx={{
                                    background: loading
                                        ? 'rgba(0, 0, 0, 0.5)'
                                        : 'linear-gradient(90deg, #1e3c72, #2a5298)',
                                    color: loading ? '#aaa' : 'rgba(166, 17, 89, 0.83)',
                                    padding: '12px',
                                    fontSize: '16px',
                                    borderRadius: '8px',
                                    '&:hover': {
                                        background: loading
                                            ? 'rgba(0, 0, 0, 0.5)'
                                            : 'linear-gradient(90deg, #163a5f, #244a7c)',
                                    },
                                }}
                            >
                                {loading ? <CircularProgress size={24} sx={{ color: '#fff' }} /> : 'Login'}
                            </Button>
                        </Box>
                        <Link
                            href="#"
                            underline="hover"
                            sx={{
                                color: '#fff',
                                display: 'block',
                                mt: 3,
                                textAlign: 'center',
                                '&:hover': {
                                    color: '#ffd700',
                                },
                            }}
                        >
                            Forgot Password?
                        </Link>
                    </Box>
                </Slide>
            </Box>
            <Footer />
        </Box>
    );
};

export default AdminLogin;