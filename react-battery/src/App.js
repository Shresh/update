import { Navigate, BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AddBattery from './components/AddBattery';
import Index from './components/Index';
import Navbar from './components/Navbar';
import './styles/index.css';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path='/' element={<Navigate to={'/battery/list'} />} />
        <Route path='/battery/list' element={<Index />} />
        <Route path='/battery/add' element={<AddBattery/>} />
      </Routes>
    </Router>
  );
}

export default App;
