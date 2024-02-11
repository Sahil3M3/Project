
import React from 'react';

import {BrowserRouter,Routes,Route} from 'react-router-dom'
import  Home  from './page/Home';
import About  from './page/About';
import Contact from './page/Contact';
import Services   from './page/Services';
import Pagenotfound from './page/Pagenotfound'

function App() {
  return (
    <div>
<BrowserRouter>
<Routes>
  <Route path="/"element={<Home/>} />
  <Route path='/about'element={<About/>}/>
  <Route path='/contact'element={<Contact/>}/>
  <Route path='/services'element={<Services/>}/>
  <Route path='*'element={<Pagenotfound/>}/>
</Routes>
</BrowserRouter>
    </div>
  );
};

export default App;
