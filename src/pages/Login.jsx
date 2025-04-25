import { useState } from 'react';
import {
    Box,
    Typography,
    TextField,
    Button,
    Link,
    Slide,
    Zoom // Import Zoom transition
} from '@mui/material';
import Header from '../components/Header';
import Footer from '../components/Footer';
import axios from 'axios'; // Import axios for API calls

const Login = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        phoneNumber: ''
    });
    const [isRegister, setIsRegister] = useState(false); // State to toggle between login and register
    const [errorMessage, setErrorMessage] = useState(''); // State to handle error messages

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage(''); // Clear previous error messages

        try {
            const baseURL = 'http://localhost:8080/api/auth'; // Replace with your backend's base URL

            if (isRegister) {
                // Registration API call
                const response = await axios.post(`${baseURL}/register`, {
                    firstName: formData.firstName,
                    lastName: formData.lastName,
                    email: formData.email,
                    password: formData.password,
                    phoneNumber: formData.phoneNumber
                });
                alert(response.data); // Show success message
                setIsRegister(false); // Switch to login form after successful registration
            } else {
                // Login API call
                const response = await axios.post(`${baseURL}/login`, {
                    email: formData.email,
                    password: formData.password
                });
                alert('Login successful!'); // Show success message
                console.log('User data:', response.data); // Handle user data
            }
        } catch (error) {
            setErrorMessage(error.response?.data || 'An error occurred. Please try again.');
        }
    };

    return (
        <Box sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
            <Header />
            <Box
                sx={{
                    display: 'flex',
                    flex: 1,
                    background: '#000',
                    color: '#fff',
                }}
            >
                {/* Left Section */}
                <Box
                    sx={{
                        flex: 1,
                        position: 'relative',
                        backgroundImage: 'url("https://static.vecteezy.com/system/resources/previews/036/081/715/non_2x/ai-generated-digital-technology-computer-engineer-working-on-a-computer-with-a-digital-communication-concept-free-photo.jpg")',
                        backgroundSize: 'cover',
                        backgroundPosition: 'center',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'center',
                        padding: 4,
                        color: 'rgba(158, 160, 47, 0.93)',
                        textAlign: 'center',
                        overflow: 'hidden',
                        boxShadow: '0 4px 20px rgba(168, 194, 24, 0.5)',
                        borderRadius: '16px',
                        transition: 'transform 0.3s ease, box-shadow 0.3s ease',
                        '&:hover': {
                            transform: 'scale(1.02)',
                            boxShadow: '0 8px 30px rgba(148, 32, 32, 0.7)',
                        },
                        '&::before': {
                            content: '""',
                            position: 'absolute',
                            top: 0,
                            left: 0,
                            width: '100%',
                            height: '100%',
                            zIndex: 1,
                            borderRadius: '16px',
                        },
                    }}
                >
                    {/* Gradient Overlay */}
                    <Box
                        sx={{
                            position: 'absolute',
                            top: 0,
                            left: 0,
                            width: '100%',
                            height: '100%',
                            background: 'linear-gradient(135deg, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.3))',
                            zIndex: 1,
                        }}
                    />
                    {/* Content */}
                    <Box sx={{ position: 'relative', zIndex: 2 }}>
                        <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2 }}>
                            Join Our Community
                        </Typography>
                        <Typography variant="body1" sx={{ mb: 3, maxWidth: '300px', mx: 'auto' }}>
                            Discover endless possibilities and connect with like-minded individuals.
                        </Typography>
                        <Button
                            variant="contained"
                            onClick={() => setIsRegister(true)} // Toggle to register form
                            sx={{
                                background: 'linear-gradient(90deg, #ff7e5f, #feb47b)',
                                boxShadow: '0 4px 8px rgba(255, 126, 95, 0.5)',
                                color: '#fff',
                                padding: '10px 20px',
                                fontSize: '16px',
                                textAlign: 'bottom',
                                fontWeight: 'bold',
                                borderRadius: '8px',
                                '&:hover': {
                                    background: 'linear-gradient(90deg, #ff6a4d, #fd9a6e)',
                                    boxShadow: '0 4px 8px rgba(255, 126, 95, 0.5)',
                                },
                            }}
                        >
                            Join Now
                        </Button>
                    </Box>
                </Box>

                {/* Right Section */}
                <Box
                    sx={{
                        flex: 1,
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        background: 'linear-gradient(135deg,rgb(4, 51, 41),rgb(14, 79, 99),rgb(55, 9, 105),rgb(1, 16, 41))',
                        padding: 4,
                    }}
                >
                    <Box
                        sx={{
                            width: '100%',
                            maxWidth: '400px',
                            background: 'rgba(255, 255, 255, 0.1)',
                            borderRadius: '16px',
                            padding: 4,
                            boxShadow: '0 8px 16px rgba(197, 185, 13, 0.5)',
                            transition: 'transform 0.3s ease, box-shadow 0.3s ease',
                            '&:hover': {
                                transform: 'scale(1.05)',
                                boxShadow: '0 12px 24px rgba(197, 185, 13, 0.7)',
                            },
                        }}
                    >
                        {errorMessage && (
                            <Typography
                                variant="body2"
                                sx={{ color: 'red', mb: 2, textAlign: 'center' }}
                            >
                                {errorMessage}
                            </Typography>
                        )}
                        <Zoom in={isRegister} timeout={500}>
                            <Box sx={{ display: isRegister ? 'block' : 'none' }}>
                                <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 1, textAlign: 'center' }}>
                                    Create an Account
                                </Typography>
                                <Typography variant="body1" sx={{ mb: 3, textAlign: 'center' }}>
                                    Fill in your details to register
                                </Typography>
                                <Box component="form" onSubmit={handleSubmit}>
                                    <TextField
                                        fullWidth
                                        label="First Name"
                                        name="firstName"
                                        value={formData.firstName}
                                        onChange={handleChange}
                                        margin="normal"
                                        InputProps={{
                                            style: { color: '#fff' },
                                        }}
                                        InputLabelProps={{
                                            style: { color: '#aaa' },
                                        }}
                                        sx={{
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
                                            '&:hover': {
                                                background: 'rgba(255, 255, 255, 0.2)',
                                            },
                                        }}
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="Last Name"
                                        name="lastName"
                                        value={formData.lastName}
                                        onChange={handleChange}
                                        margin="normal"
                                        InputProps={{
                                            style: { color: '#fff' },
                                        }}
                                        InputLabelProps={{
                                            style: { color: '#aaa' },
                                        }}
                                        sx={{
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
                                            '&:hover': {
                                                background: 'rgba(255, 255, 255, 0.2)',
                                            },
                                        }}
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="Phone Number"
                                        name="phoneNumber"
                                        type="tel"
                                        value={formData.phoneNumber}
                                        onChange={handleChange}
                                        margin="normal"
                                        InputProps={{
                                            style: { color: '#fff' },
                                        }}
                                        InputLabelProps={{
                                            style: { color: '#aaa' },
                                        }}
                                        sx={{
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
                                            '&:hover': {
                                                background: 'rgba(255, 255, 255, 0.2)',
                                            },
                                        }}
                                        required
                                    />
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
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
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
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
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
                                        sx={{
                                            background: 'linear-gradient(90deg, #000, #333)',
                                            color: '#fff',
                                            padding: '12px',
                                            fontSize: '16px',
                                            borderRadius: '8px',
                                            transition: 'background 0.3s ease, transform 0.3s ease',
                                            '&:hover': {
                                                background: 'linear-gradient(90deg, #111, #444)',
                                                transform: 'scale(1.03)',
                                            },
                                        }}
                                    >
                                        Register
                                    </Button>
                                </Box>
                                <Link
                                    href="#"
                                    underline="hover"
                                    onClick={() => setIsRegister(false)} // Switch back to login form
                                    sx={{
                                        color: '#fff',
                                        display: 'block',
                                        mt: 3,
                                        textAlign: 'center',
                                        transition: 'color 0.3s ease',
                                        '&:hover': {
                                            color: '#ffd700',
                                        },
                                    }}
                                >
                                    Already have an account? Sign in
                                </Link>
                            </Box>
                        </Zoom>
                        <Zoom in={!isRegister} timeout={500}>
                            <Box sx={{ display: !isRegister ? 'block' : 'none' }}>
                                <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 1, textAlign: 'center', color: 'rgba(168, 160, 51, 0.82)' }}>
                                    Welcome Back!
                                </Typography>
                                <Typography variant="body1" sx={{ mb: 3, textAlign: 'center', color: 'rgba(51, 168, 152, 0.82)' }}>
                                    Enter your email and password
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
                                            style: { color: 'rgba(184, 175, 57, 0.82)' },
                                        }}
                                        InputLabelProps={{
                                            style: { color: '#aaa' },
                                        }}
                                        sx={{
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
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
                                            style: { color: 'rgba(184, 175, 57, 0.82)' },
                                        }}
                                        InputLabelProps={{
                                            style: { color: '#aaa' },
                                        }}
                                        sx={{
                                            background: 'rgba(255, 255, 255, 0.1)',
                                            borderRadius: '8px',
                                            mb: 2,
                                            transition: 'background 0.3s ease',
                                            '&:hover': {
                                                background: 'rgba(255, 255, 255, 0.2)',
                                            },
                                        }}
                                        required
                                    />
                                    <Link
                                        href="#"
                                        underline="hover"
                                        sx={{
                                            color: 'rgba(184, 175, 57, 0.82)',
                                            display: 'block',
                                            mb: 3,
                                            textAlign: 'center',
                                            transition: 'color 0.3s ease',
                                            '&:hover': {
                                                color: '#ffd700',
                                            },
                                        }}
                                    >
                                        Forgot Password?
                                    </Link>
                                    <Button
                                        type="submit"
                                        variant="contained"
                                        fullWidth
                                        sx={{
                                            background: 'linear-gradient(90deg, rgba(155, 137, 58, 0.29), rgba(199, 224, 53, 0.1))',
                                            color: '#fff',
                                            padding: '12px',
                                            fontSize: '16px',
                                            borderRadius: '8px',
                                            transition: 'background 0.3s ease, transform 0.3s ease',
                                            '&:hover': {
                                                background: 'linear-gradient(90deg, #111, #444)',
                                                transform: 'scale(1.03)',
                                            },
                                        }}
                                    >
                                        Sign in
                                    </Button>
                                </Box>
                                <Link
                                    href="#"
                                    underline="hover"
                                    onClick={() => setIsRegister(true)} // Switch to register form
                                    sx={{
                                        color: 'rgba(134, 136, 26, 0.8)',
                                        display: 'block',
                                        mt: 3,
                                        textAlign: 'center',
                                        transition: 'color 0.3s ease',
                                        '&:hover': {
                                            color: '#ffd700',
                                        },
                                    }}
                                >
                                    Not registered? Register Now
                                </Link>
                            </Box>
                        </Zoom>
                    </Box>
                </Box>
            </Box>
            <Footer />
        </Box>
    );
};

export default Login;