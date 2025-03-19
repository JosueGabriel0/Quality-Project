import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import Calculator from './components/Calculator/calculator'

function App() {

  return (
    <div>
      <Routes>
        <Route path="/" element={<Calculator />} />
      </Routes>
    </div>
  )
}

export default App
