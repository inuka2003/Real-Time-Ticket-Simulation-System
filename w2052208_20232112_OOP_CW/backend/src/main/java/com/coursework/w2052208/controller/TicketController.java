package com.coursework.w2052208.controller;

import com.coursework.w2052208.service.TicketService;
import com.coursework.w2052208.service.LogService;
import com.coursework.w2052208.entity.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final LogService logService;

    public TicketController(TicketService ticketService, LogService logService) {
        this.ticketService = ticketService;
        this.logService = logService;
    }

    @PostMapping("/start") // Endpoint to start the ticket simulation.
    public String startSimulation() {
        ticketService.startVendors();

        ticketService.startCustomers();

        return String.format(
                "New vendor releasing %d tickets at a rate of %d tickets per iteration and a customer retrieving %d tickets per operation added to the simulation.",
                0, 0, 0);
    }

    @PostMapping("/stop") // Endpoint to stop the ticket simulation.
    public String stopSimulation() {
        ticketService.stopSimulation();
        return "Simulation stopped.";
    }

    @GetMapping("/available") // Endpoint to fetch the number of available tickets.
    public String getAvailableTickets() {
        return "Available tickets: " + ticketService.getAvailableTickets();
    }

    @GetMapping("/logs") // Endpoint to retrieve simulation logs.
    public List<Log> getLogs() {
        return logService.getLogs();
    }
}
