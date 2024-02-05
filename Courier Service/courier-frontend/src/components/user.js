import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

export default function User() {
  const [username, setUserName] = useState('Sahil');
  const [password, setPassword] = useState('Sahil@123');
  const [role, setRole] = useState("ADMIN");

  const handleClick = (e) => {
    e.preventDefault();
    const user = { username, password, role };
    console.log(user);

    fetch("http://localhost:8080/user/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    }).then(() => {
      console.log("New User added");
    });
  };

  return (
    <div>
      <Box
        component="form"
        sx={{
          '& > :not(style)': { m: 1, width: '25ch' },
          textAlign: 'center', // Center content horizontally
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="outlined-basic"
          label="username"
          variant="outlined"
          value={username}
          onChange={(e) => setUserName(e.target.value)}
        />
        <TextField
          id="outlined-basic"
          label="password"
          variant="outlined"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <TextField
          id="outlined-basic"
          label="role"
          variant="outlined"
          value={role}
          onChange={(e) => setRole(e.target.value.toUpperCase())}
        />
      </Box>

      <Stack spacing={2} direction="row" justifyContent="center">
        <Button variant="contained" onClick={handleClick}>
          Submit
        </Button>
      </Stack>
    </div>
  );
}
