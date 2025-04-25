import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Contact from './pages/Contact';
import Login from './pages/Login';
import AdminLogin from './pages/AdminLogin'; // Import AdminLogin component
import AdminDashboard from './pages/AdminDashboard'; // Import AdminDashboard component
import ManageUsers from './pages/ManageUsers'; // Import ManageUsers page

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/contact" element={<Contact />} />
                <Route path="/login" element={<Login />} />
                <Route path="/admin-login" element={<AdminLogin />} /> {/* Admin login route */}
                <Route path="/admin-dashboard" element={<AdminDashboard />} /> {/* Admin dashboard route */}
                <Route path="/manage-users" element={<ManageUsers />} /> {/* Add route for Manage Users */}
                <Route path="/" element={<Contact />} /> {/* Default route */}
            </Routes>
        </Router>
    );
};

export default App;
