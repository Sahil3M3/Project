import { AppBar, Box, Divider, Drawer, IconButton, Toolbar, Typography } from '@mui/material';
import React,{useState} from 'react'
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import { Link } from 'react-router-dom';
import '../../style/HeaderStyle.css';
import MenuIcon from '@mui/icons-material/Menu';
const Header = () => {
const [mobileOpen,setMobileOpen]=useState(false)

const handleDrawerToggle =()=>{
  setMobileOpen(!mobileOpen)
}

const drawer = (
  <Box onClick={handleDrawerToggle}sx={{textAlign:'center'}}>
 <Typography color={'goldenrod'} variant='h6' component='div' sx={{flexGrow:1,my:2 }}>
     
     STS Courier Sevice
     <LocalShippingIcon/>
    </Typography>
<Divider/>
<ul className='mobile-navigation'>
<li>
<Link to={"/"}>Home</Link>
</li>
<li>
<Link to={"/services"}>services</Link>
</li>
<li>
<Link to={"/about"}>About</Link>
</li>
<li>
<Link to={"/contact"}>Contact</Link>
</li>
</ul>

  </Box> )

  return (
    <>
    <Box>
      <AppBar component={'nav'} sx={{bgcolor:"black"}} >
        <Toolbar>
<IconButton color='inherit' aria-label="open drawer" edge='start' sx={{mr:2,display:{sm:"none"}}} 
  onClick={handleDrawerToggle}>
<MenuIcon/>
</IconButton>
        <Typography color={'goldenrod'} variant='h6' component='div' sx={{flexGrow:1}}>
     
         STS Courier Sevice
         <LocalShippingIcon/>
        </Typography>
        <Divider/>
<Box sx={{ display: { xs: 'none', sm:'block'}}}>
<ul className='navigation-menu'>
  <li>
    <Link to={"/"}>Home</Link>
  </li>
  <li>
    <Link to={"/Services"}>Services</Link>
  </li>
  <li>
    <Link to={"/about"}>About</Link>
  </li>
  <li>
    <Link to={"/contact"}>Contact</Link>
  </li>
</ul>
</Box>
        </Toolbar>
       
      </AppBar>
      <Box component="nav">
          <Drawer variant='temporary'open={mobileOpen} onClose={handleDrawerToggle}
          sx={{display:{xs:'block',sm:'none'},"& .MuiDrawer-paper":{boxSizing:"border-box",width:"240px"}}}>
            {drawer}
          </Drawer>
        </Box>
  <Toolbar/>
    </Box>
    </>
  )
};

export default Header;