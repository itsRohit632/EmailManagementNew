import React, { useState } from 'react';
import {
    Box,
    Typography,
    Button,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    TextField,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
} from '@mui/material';

const ManageUsers = () => {
    const [users, setUsers] = useState([
        { id: 1, name: 'John Doe', email: 'john.doe@example.com', role: 'User' },
        { id: 2, name: 'Jane Smith', email: 'jane.smith@example.com', role: 'Admin' },
    ]);
    const [openDialog, setOpenDialog] = useState(false);
    const [newUser, setNewUser] = useState({ name: '', email: '', role: '' });

    const handleOpenDialog = () => setOpenDialog(true);
    const handleCloseDialog = () => {
        setOpenDialog(false);
        setNewUser({ name: '', email: '', role: '' });
    };

    const handleAddUser = () => {
        setUsers([...users, { id: users.length + 1, ...newUser }]);
        handleCloseDialog();
    };

    const handleRemoveUser = (id) => {
        setUsers(users.filter((user) => user.id !== id));
    };

    return (
        <Box sx={{ p: 3 }}>
            <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 3 }}>
                Manage Users
            </Typography>
            <Button
                variant="contained"
                onClick={handleOpenDialog}
                sx={{
                    background: 'linear-gradient(90deg, #4caf50, #81c784)',
                    color: '#fff',
                    textTransform: 'none',
                    mb: 3,
                    '&:hover': { background: 'linear-gradient(90deg, #388e3c, #66bb6a)' },
                }}
            >
                Add User
            </Button>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Role</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {users.map((user) => (
                            <TableRow key={user.id}>
                                <TableCell>{user.id}</TableCell>
                                <TableCell>{user.name}</TableCell>
                                <TableCell>{user.email}</TableCell>
                                <TableCell>{user.role}</TableCell>
                                <TableCell>
                                    <Button
                                        variant="outlined"
                                        color="error"
                                        onClick={() => handleRemoveUser(user.id)}
                                    >
                                        Remove
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            {/* Add User Dialog */}
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>Add New User</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        label="Name"
                        fullWidth
                        value={newUser.name}
                        onChange={(e) => setNewUser({ ...newUser, name: e.target.value })}
                    />
                    <TextField
                        margin="dense"
                        label="Email"
                        fullWidth
                        value={newUser.email}
                        onChange={(e) => setNewUser({ ...newUser, email: e.target.value })}
                    />
                    <TextField
                        margin="dense"
                        label="Role"
                        fullWidth
                        value={newUser.role}
                        onChange={(e) => setNewUser({ ...newUser, role: e.target.value })}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog} color="secondary">
                        Cancel
                    </Button>
                    <Button onClick={handleAddUser} variant="contained" color="primary">
                        Add
                    </Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};

export default ManageUsers;
