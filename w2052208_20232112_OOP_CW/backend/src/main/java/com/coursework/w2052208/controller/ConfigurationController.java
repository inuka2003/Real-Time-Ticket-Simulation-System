package com.coursework.w2052208.controller;

import com.coursework.w2052208.service.ConfigurationService;
import com.coursework.w2052208.util.TicketConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/get") // Endpoint to fetch the current configuration.
    public TicketConfiguration getConfiguration() {
        return configurationService.getConfiguration();
    }

    @PostMapping("/update") // Endpoint to update the configuration with provided values.
    public String updateConfiguration(@RequestBody TicketConfiguration request) {
        // Basic validation
        if (request.getTotalTickets() <= 0 ||
                request.getTicketReleaseRate() <= 0 ||
                request.getCustomerRetrievalRate() <= 0 ||
                request.getMaxTicketCapacity() <= 0||
                request.getNumCustomers() <=0 ||
                request.getNumVendors() <= 0) {


            throw new IllegalArgumentException("Configuration values must be greater than 0.");
        }
        if (request.getMaxTicketCapacity() > request.getTotalTickets()) {
            throw new IllegalArgumentException("Max Ticket Capacity cannot exceed Total Tickets.");
        }

        configurationService.updateConfiguration(
                request.getTotalTickets(),
                request.getTicketReleaseRate(),
                request.getCustomerRetrievalRate(),
                request.getMaxTicketCapacity(),
                request.getNumCustomers(),
                request.getNumVendors()
        );
        return "Configuration updated successfully!";
    }
}
