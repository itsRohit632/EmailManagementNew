import { useState } from 'react';
import {
    Box,
    Container,
    Typography,
    TextField,
    Button,
    Paper
} from '@mui/material';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Login = () => {
    const [formData, setFormData] = useState({ email: '', password: '' });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Login Data:', formData);
        // Add authentication logic here
    };

    return (
        <Box sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
            <Header />
            <Container component="main" sx={{ py: 6, flex: 1 }}>
                <Paper
                    elevation={4}
                    sx={{
                        p: 4,
                        maxWidth: '400px',
                        margin: '0 auto',
                        borderRadius: '16px',
                        boxShadow: '0 8px 16px rgba(0, 0, 0, 0.2)'
                    }}
                >
                    <Typography variant="h4" component="h2" gutterBottom>
                        Login
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <TextField
                            fullWidth
                            label="Email"
                            name="email"
                            type="email"
                            value={formData.email}
                            onChange={handleChange}
                            margin="normal"
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
                            required
                        />
                        <Button
                            type="submit"
                            variant="contained"
                            fullWidth
                            sx={{ mt: 3 }}
                        >
                            Login
                        </Button>
                    </Box>
                </Paper>
            </Container>
            <Footer />
        </Box>
    );
};

export default Login;
