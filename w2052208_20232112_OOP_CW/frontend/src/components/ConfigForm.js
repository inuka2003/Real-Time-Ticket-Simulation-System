// ConfigForm.js
import React, { useState } from 'react';

function ConfigForm() {
  const [config, setConfig] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
    numCustomers: 0,
    numVendors: 0,
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setConfig({
      ...config,
      [name]: value,
    });
  };

  const handleGetConfig = async () => {
    const response = await fetch('/config/get');
    if (response.ok) {
      const data = await response.json();
      setConfig(data);
    } else {
      alert('Failed to fetch configuration');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch('/config/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(config),
    });

    if (response.ok) {
      alert('Configuration updated successfully');
    } else {
      alert('Failed to update configuration');
    }
  };

  return (
    <div className="config-form">
      <h2>Configuration</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <div className="form-group">
            <label>Total Tickets:</label>
            <input
              type="number"
              name="totalTickets"
              value={config.totalTickets}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Ticket Release Rate:</label>
            <input
              type="number"
              name="ticketReleaseRate"
              value={config.ticketReleaseRate}
              onChange={handleInputChange}
            />
          </div>
        </div>
        
        <div className="form-row">
          <div className="form-group">
            <label>Customer Retrieval Rate:</label>
            <input
              type="number"
              name="customerRetrievalRate"
              value={config.customerRetrievalRate}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Max Ticket Capacity:</label>
            <input
              type="number"
              name="maxTicketCapacity"
              value={config.maxTicketCapacity}
              onChange={handleInputChange}
            />
          </div>
        </div>
        
        <div className="form-row">
          <div className="form-group">
            <label>Number of Customers:</label>
            <input
              type="number"
              name="numCustomers"
              value={config.numCustomers}
              onChange={handleInputChange}
            />
          </div>
          <div className="form-group">
            <label>Number of Vendors:</label>
            <input
              type="number"
              name="numVendors"
              value={config.numVendors}
              onChange={handleInputChange}
            />
          </div>
        </div>
        
        <div className="button-group">
          <button type="button" className="secondary" onClick={handleGetConfig}>Get Configuration</button>
          <button type="submit" className="primary">Update Configuration</button>
        </div>
      </form>
    </div>
  );
}

export default ConfigForm;