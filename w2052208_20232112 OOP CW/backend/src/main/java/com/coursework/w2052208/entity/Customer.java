package com.coursework.w2052208.entity;

import com.coursework.w2052208.service.LogService;
import com.coursework.w2052208.util.TicketPool;

import java.util.logging.Logger;

public class Customer implements Runnable {
    private final Logger logger = Logger.getLogger(Customer.class.getName());
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final LogService logService;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, LogService logService) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.logService = logService;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ticketPool) {
                String ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    String threadName = Thread.currentThread().getName();
                    String action = "retrieved";
                    logger.info("Customer [" + threadName + "] retrieved ticket: " + ticket);   // Logs the ticket retrieval action.
                    logService.addLog(new Log(threadName, action, ticket));    // Records the action in the log service.
                }
            }

            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
