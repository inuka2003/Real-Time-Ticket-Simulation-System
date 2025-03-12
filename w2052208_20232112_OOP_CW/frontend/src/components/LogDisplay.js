// LogDisplay.js
import React, { useState, useEffect } from 'react';

const LogDisplay = () => {
  const [logs, setLogs] = useState([]);

  const fetchLogs = async () => {
    try {
      const response = await fetch('/tickets/logs');
      const data = await response.json();
      if (Array.isArray(data)) {
        setLogs(data);
      } else {
        console.error('Fetched data is not an array:', data);
      }
    } catch (error) {
      console.error('Error fetching logs:', error);
    }
  };

  useEffect(() => {
    fetchLogs();
    const interval = setInterval(fetchLogs, 1000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="log-container">
      <h2 className="log-title">System Logs</h2>
      <ul className="log-list">
        {logs.map((log, index) => (
          <li key={index} className={`log-item ${log.action === 'retrieved' ? 'log-item-customer' : 'log-item-vendor'}`}>
            <span className="log-item-icon">
              {log.action === 'retrieved' ? '👤' : '🏪'}
            </span>
            <span className="log-item-text">
              {log.action === 'retrieved' ? 'Customer' : 'Vendor'} [{log.threadName}] {log.action} ticket: {log.ticketNumber}
            </span>
            <span className="log-item-time">
              {new Date().toLocaleTimeString()}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;