import { useState, useEffect } from 'react';
import {
    Box,
    Container,
    Grid,
    Typography,
    TextField,
    Button,
    Divider,
    Link,
    Paper,
    Stack,
    IconButton,
    Slide
} from '@mui/material';
import {
    LocationOn,
    Phone,
    Email,
    Facebook,
    Twitter,
    YouTube
} from '@mui/icons-material';
import Header from '../components/Header';
import Footer from '../components/Footer';
import contactBg from '../assets/contact_bg.jpg';
import contactUsImage from '../assets/contactus.jpg'; // Import the new image
import axios from 'axios'; // Import axios for API calls

const Contact = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        subject: '',
        message: ''
    });

    const [showForm, setShowForm] = useState(false);

    useEffect(() => {
        setShowForm(true); // Trigger the slide animation when the component mounts
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formDataToSend = new FormData();
        formDataToSend.append('email', formData.email);
        formDataToSend.append('referredBy', formData.referredBy);
        formDataToSend.append('firstName', formData.firstName);
        formDataToSend.append('lastName', formData.lastName);
        formDataToSend.append('dob', formData.dob);
        formDataToSend.append('eadType', formData.eadType);
        formDataToSend.append('eadStartDate', formData.eadStartDate);
        formDataToSend.append('hasPriorExperience', formData.hasPriorExperience);
        formDataToSend.append('priorExperienceDetails', formData.priorExperienceDetails || '');
        formDataToSend.append('programmingLanguage', formData.programmingLanguage);
        formDataToSend.append('fileType', formData.fileType);
        formDataToSend.append('uploadedFile', formData.uploadedFile);

        try {
            const response = await axios.post('http://localhost:8080/api/user-details/submit', formDataToSend, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
                withCredentials: true, // Include credentials in the request
            });
            alert(response.data); // Show success message
        } catch (error) {
            console.error('Error submitting form:', error);
            alert('Failed to submit the form. Please try again.');
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    return (
        <Box
            sx={{
                minHeight: '100vh',
                display: 'flex',
                flexDirection: 'column',
                backgroundImage: `url(${contactBg})`, // Set the background image
                backgroundSize: 'cover', // Ensure the image covers the entire area
                backgroundPosition: 'center', // Center the image
                backgroundRepeat: 'no-repeat', // Prevent the image from repeating
            }}
        >
            <Header />

            {/* Hero Section */}
            <Box
                component="section"
                sx={{
                    py: 4,
                    textAlign: 'center',
                    color: 'white',
                }}
            >
                <Container>
                    <Typography
                        variant="h4"
                        component="h3"
                        gutterBottom
                        sx={{
                            fontWeight: 700,
                            color: 'rgb(56, 30, 105)',
                            boxShadow: '0 4px 12px rgba(0, 0, 0, 0.5)',
                            backgroundColor: 'rgba(91, 173, 194, 0.8)', // Add background color
                            padding: '10px 20px', // Add padding for spacing
                            borderRadius: '8px', // Add rounded corners
                            display: 'inline-block' // Ensure the background wraps tightly around the text
                        }}
                    >
                        Contact Us
                    </Typography>
                    <Typography variant="body1" sx={{ maxWidth: '600px', margin: '0 auto', color: 'rgb(78, 41, 121)' }}>
                        Have questions or want to work with us? Fill out the form below or reach out to us directly.
                    </Typography>
                </Container>
            </Box>

            {/* Main Content */}
            <Container
                component="main"
                sx={{
                    py: 6,
                    flex: 1,
                    maxWidth: '100%',
                    px: 1,
                    display: 'flex', // Use flexbox for centering
                    justifyContent: 'center', // Center horizontally
                    alignItems: 'center', // Center vertically
                    minHeight: '100vh', // Ensure the container takes full viewport height
                }}
            >
                <Grid
                    container
                    spacing={4}
                    direction="column"
                    alignItems="center"
                    justifyContent="center" // Center contents vertically
                    sx={{ width: '100%' }}
                >
                    {/* Enquire Now Container */}
                    <Grid item xs={12} md={8}>
                        <Slide direction="left" in={showForm} mountOnEnter unmountOnExit>
                            <Paper
                                elevation={4}
                                sx={{
                                    p: 4,
                                    background: 'rgba(48, 121, 134, 0.43)',
                                    borderRadius: '16px',
                                    boxShadow: '0 8px 16px rgb(105, 30, 30)',
                                    maxWidth: '600px',
                                    margin: '0 auto'
                                }}
                            >
                                <Typography variant="h4" component="h2" gutterBottom sx={{ fontWeight: 600, color: 'rgb(105, 30, 30)', textAlign: 'center' }}>
                                    Enquire Now
                                </Typography>
                                <Typography variant="body1" color="text.secondary" paragraph>
                                    Please share the details below, and we will get back to you soon!
                                </Typography>

                                <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
                                    <TextField
                                        fullWidth
                                        label="Who referred you?"
                                        name="referredBy"
                                        value={formData.referredBy || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="First Name"
                                        name="firstName"
                                        value={formData.firstName || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="Last Name"
                                        name="lastName"
                                        value={formData.lastName || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="Date of Birth"
                                        name="dob"
                                        type="date"
                                        value={formData.dob || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        InputLabelProps={{ shrink: true }}
                                        required
                                    />
                                    <TextField
                                        fullWidth
                                        label="Email"
                                        name="email"
                                        type="email"
                                        value={formData.email || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        required
                                    />
                                    <TextField
                                        select
                                        fullWidth
                                        label="EAD Type"
                                        name="eadType"
                                        value={formData.eadType || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        SelectProps={{
                                            native: true,
                                        }}
                                        InputLabelProps={{
                                            shrink: true, // Ensures the label stays above the dropdown
                                        }}
                                        required
                                    >
                                        <option value="" disabled>
                                            Select an option
                                        </option>
                                        <option value="OPT EAD">OPT EAD</option>
                                        <option value="STEM OPT EAD (1st Year)">STEM OPT EAD (1st Year)</option>
                                        <option value="STEM OPT EAD (2nd Year)">STEM OPT EAD (2nd Year)</option>
                                        <option value="H4 EAD">H4 EAD</option>
                                        <option value="L2 EAD">L2 EAD</option>
                                        <option value="H1B">H1B</option>
                                        <option value="Green Card">Green Card</option>
                                        <option value="US Citizen">US Citizen</option>
                                    </TextField>
                                    <TextField
                                        fullWidth
                                        label="EAD Start Date"
                                        name="eadStartDate"
                                        type="date"
                                        value={formData.eadStartDate || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        InputLabelProps={{ shrink: true }}
                                        required
                                    />
                                    <TextField
                                        select
                                        fullWidth
                                        label="Programming Language of Interest"
                                        name="programmingLanguage"
                                        value={formData.programmingLanguage || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        SelectProps={{
                                            native: true,
                                        }}
                                        InputLabelProps={{
                                            shrink: true, // Ensures the label stays above the dropdown
                                        }}
                                        required
                                    >
                                        <option value="" disabled>
                                            Select an option
                                        </option>
                                        <option value="Java Full Stack Developer">Java Full Stack Developer</option>
                                        <option value="Data Engineer">Data Engineer</option>
                                        <option value="Data Scientist">Data Scientist</option>
                                        <option value="Python Full Stack Developer">Python Full Stack Developer</option>
                                        <option value="Business Analyst">Business Analyst</option>
                                    </TextField>
                                    <TextField
                                        fullWidth
                                        label="Do you have prior experience?"
                                        name="hasPriorExperience"
                                        select
                                        value={formData.hasPriorExperience || ''}
                                        onChange={handleChange}
                                        margin="normal"
                                        SelectProps={{
                                            native: true,
                                        }}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        required
                                    >
                                        <option value="" disabled>
                                            Select an option
                                        </option>
                                        <option value="true">Yes</option>
                                        <option value="false">No</option>
                                    </TextField>
                                    {formData.hasPriorExperience === 'true' && (
                                        <TextField
                                            fullWidth
                                            label="Details of Prior Experience (if any)"
                                            name="priorExperienceDetails"
                                            value={formData.priorExperienceDetails || ''}
                                            onChange={handleChange}
                                            margin="normal"
                                            multiline
                                            rows={2}
                                        />
                                    )}
                                    <Typography variant="body2" color="text.secondary" sx={{ mt: 2 }}>
                                        Attach your file below (Resume, EAD Card, or ID Proof):
                                    </Typography>
                                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 2 }}>
                                        <TextField
                                            select
                                            fullWidth
                                            label="File Type"
                                            name="fileType"
                                            value={formData.fileType || ''}
                                            onChange={handleChange}
                                            margin="normal"
                                            SelectProps={{
                                                native: true,
                                            }}
                                            InputLabelProps={{
                                                shrink: true,
                                            }}
                                            required
                                        >
                                            <option value="" disabled>
                                                Select File Type
                                            </option>
                                            <option value="Resume">Resume</option>
                                            <option value="EAD Card">EAD Card</option>
                                            <option value="ID Proof">ID Proof</option>
                                        </TextField>
                                        <Button
                                            variant="outlined"
                                            component="label"
                                        >
                                            Upload File
                                            <input
                                                type="file"
                                                name="uploadedFile"
                                                accept=".pdf,.doc,.docx"
                                                hidden
                                                onChange={(e) => {
                                                    const file = e.target.files[0];
                                                    setFormData(prev => ({ ...prev, uploadedFile: file }));
                                                }}
                                            />
                                        </Button>
                                        {formData.uploadedFile && (
                                            <Typography variant="body2" color="text.secondary">
                                                Selected File: {formData.uploadedFile.name}
                                            </Typography>
                                        )}
                                    </Box>
                                    <Button
                                        type="submit"
                                        variant="contained"
                                        size="large"
                                        sx={{
                                            mt: 3,
                                            background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                                            color: 'white',
                                            '&:hover': {
                                                background: 'linear-gradient(135deg, #764ba2 0%, #667eea 100%)'
                                            }
                                        }}
                                    >
                                        Submit
                                    </Button>
                                </Box>
                            </Paper>
                        </Slide>
                    </Grid>

                    {/* Contact Details Section */}
                    <Box
                        component="section"
                        sx={{
                            py: 6,
                            background: 'linear-gradient(135deg,rgba(112, 180, 185, 0.47) 0%,rgba(102, 138, 187, 0.42) 100%)',
                            color: '#333',
                            boxShadow: '0 8px 16px rgb(105, 30, 30)',
                            width: '100vw', // Use viewport width
                            marginLeft: 'calc(-50vw + 50%)', // Center the element when using 100vw
                            borderRadius: 0,

                        }}
                    >
                        <Grid container alignItems="center" justifyContent="center" spacing={4} sx={{
                            width: '100%',
                            margin: 0,
                            maxWidth: '100%', // Ensure no max-width restriction
                            px: 4,
                        }}>
                            {/* Left Column: Image */}
                            <Grid item xs={12} md={6} sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                                <Box
                                    component="img"
                                    src={contactUsImage} // Use the new image
                                    alt="Contact Us"
                                    sx={{
                                        width: '90%', // Adjust width for better responsiveness
                                        height: 'auto', // Maintain aspect ratio
                                        maxHeight: '400px', // Limit the height for consistency
                                        objectFit: 'cover', // Maintain aspect ratio and cover the container
                                        borderRadius: '8px',
                                        boxShadow: '0 4px 12px rgba(75, 12, 12, 0.75)'
                                    }}
                                />
                            </Grid>

                            {/* Right Column: Contact Details */}
                            <Grid item xs={12} md={6}>
                                <Box
                                    sx={{
                                        p: 4,
                                        background: 'rgba(88, 113, 117, 0.9)', // Slightly transparent white background
                                        borderRadius: '8px', // Add slight rounding for aesthetics
                                        boxShadow: '0 4px 12px rgba(75, 12, 12, 0.75)', // Add subtle shadow for better visibility
                                        display: 'flex',
                                        flexDirection: 'column',
                                        justifyContent: 'space-between',
                                        height: 'Auto', // Stretch to match the height of the image
                                    }}
                                >
                                    <Typography variant="h4" component="h2" gutterBottom sx={{ fontWeight: 600 }}>
                                        Contact Details
                                    </Typography>
                                    <Typography variant="body1" color="text.secondary" paragraph>
                                        Ready to work together? Fill the above form and send it to us.
                                    </Typography>

                                    <Stack spacing={3} sx={{ mt: 3 }}>
                                        <Box sx={{ display: 'flex', alignItems: 'flex-start' }}>
                                            <LocationOn color="primary" sx={{ mr: 2, mt: 0.5 }} />
                                            <Box>
                                                <Typography variant="h6" component="h4">
                                                    Address
                                                </Typography>
                                                <Typography variant="body1" color="text.secondary">
                                                    201 E Academy St, Fuquay Varina, North Carolina, 27526
                                                </Typography>
                                            </Box>
                                        </Box>

                                        <Box sx={{ display: 'flex', alignItems: 'flex-start' }}>
                                            <Phone color="primary" sx={{ mr: 2, mt: 0.5 }} />
                                            <Box>
                                                <Typography variant="h6" component="h4">
                                                    Phone
                                                </Typography>
                                                <Typography variant="body1" color="text.secondary">
                                                    +1 910-403-8482
                                                </Typography>
                                            </Box>
                                        </Box>

                                        <Box sx={{ display: 'flex', alignItems: 'flex-start' }}>
                                            <Email color="primary" sx={{ mr: 2, mt: 0.5 }} />
                                            <Box>
                                                <Typography variant="h6" component="h4">
                                                    Email
                                                </Typography>
                                                <Link href="mailto:dj@stemsolllc.com" color="primary">
                                                    dj@stemsolllc.com
                                                </Link>
                                            </Box>
                                        </Box>
                                    </Stack>

                                    <Divider sx={{ my: 4 }} />

                                    <Box sx={{ display: 'flex', gap: 2 }}>
                                        <IconButton
                                            color="primary"
                                            href="#"
                                            aria-label="Facebook"
                                            sx={{ bgcolor: 'primary.light', '&:hover': { bgcolor: 'primary.main', color: 'white' } }}
                                        >
                                            <Facebook />
                                        </IconButton>
                                        <IconButton
                                            color="primary"
                                            href="#"
                                            aria-label="Twitter"
                                            sx={{ bgcolor: 'primary.light', '&:hover': { bgcolor: 'primary.main', color: 'white' } }}
                                        >
                                            <Twitter />
                                        </IconButton>
                                        <IconButton
                                            color="primary"
                                            href="#"
                                            aria-label="YouTube"
                                            sx={{ bgcolor: 'primary.light', '&:hover': { bgcolor: 'primary.main', color: 'white' } }}
                                        >
                                            <YouTube />
                                        </IconButton>
                                    </Box>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                </Grid>
            </Container>

            <Footer />
        </Box>
    );
};

export default Contact;