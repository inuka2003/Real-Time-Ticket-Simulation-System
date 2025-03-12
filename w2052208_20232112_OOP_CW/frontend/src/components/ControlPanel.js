// ControlPanel.js
import React from 'react';

function ControlPanel() {
  const startSimulation = async () => {
    const response = await fetch('/tickets/start', {
      method: 'POST',
    });

    if (response.ok) {
      alert('Simulation Started');
    } else {
      alert('Failed to start simulation');
    }
  };

  const stopSimulation = async () => {
    const response = await fetch('/tickets/stop', {
      method: 'POST',
    });

    if (response.ok) {
      alert('Simulation Stopped');
    } else {
      alert('Failed to stop simulation');
    }
  };

  return (
    <div className="control-panel">
      <h2>Control Panel</h2>
      <div>
        <button className="success" onClick={startSimulation}>Start Simulation</button>
        <button className="danger" onClick={stopSimulation}>Stop Simulation</button>
      </div>
    </div>
  );
}

export default ControlPanel;
