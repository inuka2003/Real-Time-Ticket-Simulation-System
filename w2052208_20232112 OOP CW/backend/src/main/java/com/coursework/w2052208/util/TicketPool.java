package com.coursework.w2052208.util;

import org.springframework.stereotype.Component;


@Component
public class TicketPool {
    private final TicketConfiguration config;

    public TicketPool(TicketConfiguration config) {
        this.config = config;
    }


    public synchronized void addTicket(String ticket) {
        while (Constants.ticketList.size() >= config.getMaxTicketCapacity()) {
            try {
                System.out.println("Ticket pool is full. Vendor waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        Constants.ticketList.add(ticket);
        notifyAll();


        System.out.println("Ticket added. Current ticket pool size: " + Constants.ticketList.size());
    }

    // Method to remove tickets from the pool
    public synchronized String removeTicket() {
        while (Constants.ticketList.isEmpty()) {
            try {
                System.out.println("Ticket pool is empty. Customer waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        String ticket = Constants.ticketList.poll();
        notifyAll();

        // Print the current size of the ticket pool
        System.out.println("Ticket retrieved. Current ticket pool size: " + Constants.ticketList.size());
        return ticket;
    }

    // Method to get the current available tickets in the pool
    public synchronized int getAvailableTickets() {
        return Constants.ticketList.size();
    }
}
