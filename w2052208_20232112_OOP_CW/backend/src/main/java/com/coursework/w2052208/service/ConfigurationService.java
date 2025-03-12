package com.coursework.w2052208.service;

import com.coursework.w2052208.util.TicketConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private final TicketConfiguration config;
    private static final String CONFIG_FILE_PATH = "config.json";

    public ConfigurationService(TicketConfiguration config) {
        this.config = config;
    }

    public TicketConfiguration getConfiguration() {
        return config;
    }

    public void updateConfiguration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int numCustomers, int numVendors) {
        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maxTicketCapacity <= 0 || numCustomers <= 0 || numVendors <= 0) {
            throw new IllegalArgumentException("Configuration values must be greater than 0.");
        }
        if (maxTicketCapacity > totalTickets) {
            throw new IllegalArgumentException("Max Ticket Capacity cannot exceed Total Tickets.");
        }

        config.setTotalTickets(totalTickets);
        config.setTicketReleaseRate(ticketReleaseRate);
        config.setCustomerRetrievalRate(customerRetrievalRate);
        config.setMaxTicketCapacity(maxTicketCapacity);
        config.setNumCustomers(numCustomers); // Set this
        config.setNumVendors(numVendors);

        config.saveConfiguration(CONFIG_FILE_PATH, config);
    }

    @Bean
    public String load() {
        // Load configuration from the JSON file
        TicketConfiguration config = TicketConfiguration.loadConfiguration(CONFIG_FILE_PATH);
        if (config != null) {
            System.out.println("Total Tickets: " + config.getTotalTickets());
            System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
            System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
            System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
            System.out.println("Num Customers: " + config.getNumCustomers());
            System.out.println("Num Vendors: " + config.getNumVendors());


            this.config.setTotalTickets(config.getTotalTickets());
            this.config.setTicketReleaseRate(config.getTicketReleaseRate());
            this.config.setCustomerRetrievalRate(config.getCustomerRetrievalRate());
            this.config.setMaxTicketCapacity(config.getMaxTicketCapacity());
            this.config.setNumCustomers(config.getNumCustomers());
            this.config.setNumVendors(config.getNumVendors());

        }

        return "";
    }
}
