import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Create from "./pages/Create";
import Edit from "./pages/Edit";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/create" element={<Create/>}/>
              <Route path="/edit/:id" element={<Edit/>}/>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
