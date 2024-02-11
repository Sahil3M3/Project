import React from 'react'
import Layout from '../components/Layout/Layout'
import { Box, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material'
import SupportAgentIcon from '@mui/icons-material/SupportAgent';
import EmailIcon from '@mui/icons-material/Email';
import CallIcon from '@mui/icons-material/Call';

const Contact = () => {
  return (
    <Layout>
    <Box sx={{my:10 ,ml:10,"& h4 ":{fontWeight:'bold',mb:2}}}>
      <Typography variant='h4'>
        Contact Our Office
      </Typography>
      <p>  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.

      </p>
    </Box>
    <Box sx={{m:3}}>
      <TableContainer component={Paper}>
        <Table aria-label="contact table">
          <TableHead>
            <TableRow>
              <TableCell sx={{bgcolor:'black',color:'white',}}align='center'>
Contact Details
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>
                <SupportAgentIcon sx={{color:'red',pt:1}}/>8999172773 (Toll-Free)
              </TableCell>
            </TableRow>
            <TableRow>
            <TableCell>
                <EmailIcon sx={{color:'red',pt:1}}/> helpp@sahil.com
              </TableCell>
            </TableRow>
            <TableRow>
            <TableCell>
                <CallIcon sx={{color:'red',pt:1}}/> 8999172773
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
</Layout>
  )
}

export default Contact