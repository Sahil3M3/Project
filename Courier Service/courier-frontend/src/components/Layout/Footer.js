import React from 'react'
import InstagramIcon from '@mui/icons-material/Instagram';
import XIcon from '@mui/icons-material/X';
import GitHubIcon from '@mui/icons-material/GitHub';
import YouTubeIcon from '@mui/icons-material/YouTube';
import { Box, Typography } from '@mui/material';
const Footer = () => {
  return (
  <>
  <Box sx={{textAlign:'center',bgcolor:'#1A1A19',color:'white',p:3}}>
    <Box sx={{my:3,"& svg":{fontSize:"60px",cursor:"pointer",mr:2,}, "& svg:hover":{color:'goldenrod',
        transform:'translatex(5px)',transition:'all 400ms'}}}>
        <InstagramIcon/><XIcon/><GitHubIcon/><YouTubeIcon/>
    </Box>
    <Typography variant='h5' sx={{'@media(max-width:600px}':{frotSize:"1rem"},}}>

        All Right Reserved &copy; STS Courier Services 
    </Typography>
  </Box>
  
  </>
    )
}

export default Footer