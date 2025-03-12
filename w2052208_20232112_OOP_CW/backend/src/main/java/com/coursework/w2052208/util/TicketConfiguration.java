package com.coursework.w2052208.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Data
public class TicketConfiguration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numCustomers;
    private int numVendors;

    public void saveConfiguration(String filePath, TicketConfiguration config) {
        try {
            String json = new ObjectMapper().writeValueAsString(config);

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to serialize configuration", e);
        }
    }
    public static TicketConfiguration loadConfiguration(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Configuration file not found: " + filePath);
            return null;
        }

        try (FileReader reader = new FileReader(filePath)) {
            return new ObjectMapper().readValue(reader, TicketConfiguration.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
