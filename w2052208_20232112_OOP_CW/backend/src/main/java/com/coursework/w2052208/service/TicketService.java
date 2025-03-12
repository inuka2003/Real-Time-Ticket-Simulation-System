package com.coursework.w2052208.service;

import com.coursework.w2052208.entity.Customer;
import com.coursework.w2052208.entity.Vendor;
import com.coursework.w2052208.util.TicketPool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service class responsible for managing the ticket simulation, including vendors and customers.
@Service
public class TicketService {
    private final TicketPool ticketPool;
    private boolean isRunning;
    private final ConfigurationService configurationService;
    private final LogService logService; // Add LogService
    private final List<Thread> vendorThreads;
    private final List<Thread> customerThreads;

    public TicketService(TicketPool ticketPool, ConfigurationService configurationService, LogService logService) {
        this.ticketPool = ticketPool;
        this.configurationService = configurationService;
        this.logService = logService; // Initialize LogService
        vendorThreads = new ArrayList<>();
        customerThreads = new ArrayList<>();
        this.isRunning = false;
    }

    public void startVendors() {
        if (isRunning) {
            throw new IllegalStateException("Simulation is already running.");
        }

        int totalTickets = configurationService.getConfiguration().getTotalTickets();
        int ticketReleaseRate = configurationService.getConfiguration().getTicketReleaseRate();
        int numVendors = configurationService.getConfiguration().getNumVendors();
        if (totalTickets <= 0 || ticketReleaseRate <= 0 || numVendors <= 0) {
            throw new IllegalArgumentException("Invalid configuration for vendor. Please check the values.");
        }

        for (int i = 0; i < numVendors; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate, totalTickets, logService));
            vendorThread.start();
            vendorThreads.add(vendorThread);
        }

        System.out.println("Vendor started.");
    }

    public void startCustomers() {
        if (isRunning) {
            throw new IllegalStateException("Simulation is already running.");
        }

        int customerRetrievalRate = configurationService.getConfiguration().getCustomerRetrievalRate();
        int numCustomers = configurationService.getConfiguration().getNumCustomers();
        if (customerRetrievalRate <= 0 || numCustomers <= 0) {
            throw new IllegalArgumentException("Invalid configuration for customer. Please check the values.");
        }

        for (int i = 0; i < numCustomers; i++) {
            Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate, logService));
            customerThread.start();
            customerThreads.add(customerThread);
        }
        System.out.println("Customer started.");
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }

    public void stopSimulation() {
        if (isRunning) {
            throw new IllegalStateException("Simulation is not running.");
        }

        for (Thread vendorThread : vendorThreads) {
            if (vendorThread != null && vendorThread.isAlive()) {
                vendorThread.interrupt();
            }
        }

        for (Thread customerThread : customerThreads) {
            if (customerThread != null && customerThread.isAlive()) {
                customerThread.interrupt();
            }
        }

        isRunning = false;
        System.out.println("Simulation stopped.");
    }
}
