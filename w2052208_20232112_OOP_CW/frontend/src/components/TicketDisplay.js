// TicketDisplay.js
import React, { useState, useEffect } from 'react';

function TicketDisplay() {
  const [availableTickets, setAvailableTickets] = useState(0);

  const fetchAvailableTickets = async () => {
    const response = await fetch('/tickets/available');
    const data = await response.text();
    setAvailableTickets(parseInt(data.replace('Available tickets: ', '')));
  };

  useEffect(() => {
    const interval = setInterval(fetchAvailableTickets, 500);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="ticket-display">
      <h3>Available Tickets</h3>
      <div className="ticket-count">{availableTickets}</div>
    </div>
  );
}

export default TicketDisplay;