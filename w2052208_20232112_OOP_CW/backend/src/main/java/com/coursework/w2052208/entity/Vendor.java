package com.coursework.w2052208.entity;

import com.coursework.w2052208.service.LogService;
import com.coursework.w2052208.util.Constants;
import com.coursework.w2052208.util.TicketPool;

import java.util.logging.Logger;

// Represents a vendor responsible for adding tickets to the ticket pool.
public class Vendor implements Runnable {
    private final Logger logger = Logger.getLogger(Vendor.class.getName());
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int totalTickets;
    private final LogService logService;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int totalTickets, LogService logService) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTickets = totalTickets;
        this.logService = logService;
    }

    @Override
    public void run() {
        while (Constants.lastTicket < totalTickets) {
            synchronized (ticketPool) {
                Constants.lastTicket += 1;
                String ticket = "Ticket " + Constants.lastTicket;
                String threadName = Thread.currentThread().getName();
                String action = "added";
                ticketPool.addTicket(ticket);
                logger.info("Vendor [" + threadName + "] added ticket: " + ticket);
                logService.addLog(new Log(threadName, action, ticket));
            }

            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
