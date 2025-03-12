// App.js
import React from 'react';
import './App.css';
import ConfigForm from './ConfigForm';
import TicketDisplay from './TicketDisplay';
import ControlPanel from './ControlPanel';
import LogDisplay from './LogDisplay';

function App() {
  return (
    <div className="App">
      <h1>Ticket Simulation System</h1>
      <div className="dashboard-grid">
        <ConfigForm />
        <TicketDisplay />
        <ControlPanel />
        <LogDisplay />
      </div>
    </div>
  );
}

export default App;