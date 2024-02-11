import React from 'react'
import Layout from '../components/Layout/Layout'
import { Link } from 'react-router-dom'
import Banner from '../images/Banner.jpg'
import '../style/HomeStyle.css'
const Home = () => {
  return (
    <Layout>
        <div className='home' style={{backgroundImage:`url(${Banner})`}}> 
    <div className='headerContainer'>
       <h1>STS Courier services</h1>
       <p>Best Courier service In India</p>
       <Link to='/services'>
       <button> Send Now</button>
        </Link>
        </div>
</div>
    </Layout>
  )
}

export default Home