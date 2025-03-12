import java.io.*;
import java.util.Scanner;

public class Main {
    private static Configuration configuration = new Configuration();
    private static Thread[] vendorThreads;
    private static Thread[] customerThreads;
    private static TicketPool ticketPool;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nTicket Simulation - Command Panel:");
            System.out.println("1. Get Configuration");
            System.out.println("2. Update Configuration");
            System.out.println("3. Start Simulation");
            System.out.println("4. Stop Simulation");
            System.out.println("5. View Available Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    getConfigurationFromFile();
                    break;

                case 2:
                    updateConfiguration(scanner);
                    break;

                case 3:
                    startSimulation();
                    break;

                case 4:
                    stopSimulation();
                    break;

                case 5:
                    viewAvailableTickets();
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }

    private static void startSimulation() {
        ticketPool = new TicketPool(configuration.getMaxTicketCapacity(), configuration.getTotalTickets());

        // Create Vendor threads
        vendorThreads = new Thread[configuration.getNumVendors()];
        for (int i = 0; i < vendorThreads.length; i++) {
            Vendor vendor = new Vendor(ticketPool, configuration.getTicketReleaseRate());
            vendorThreads[i] = new Thread(vendor, "Vendor ID-" + i);
            vendorThreads[i].start();
        }

        // Create Customer threads
        customerThreads = new Thread[configuration.getNumCustomers()];
        for (int i = 0; i < customerThreads.length; i++) {
            Customer customer = new Customer(ticketPool, configuration.getCustomerRetrievalRate());
            customerThreads[i] = new Thread(customer, "Customer ID-" + i);
            customerThreads[i].start();
        }
    }


    private static void stopSimulation() {
        for (Thread vendorThread : vendorThreads) {
            if (vendorThread != null) {
                vendorThread.interrupt();
            }
        }
        for (Thread customerThread : customerThreads) {
            if (customerThread != null) {
                customerThread.interrupt();
            }
        }
        System.out.println("Simulation stopped.");
    }

    private static void getConfigurationFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            configuration.setTotalTickets(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            configuration.setTicketReleaseRate(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            configuration.setCustomerRetrievalRate(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            configuration.setMaxTicketCapacity(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            configuration.setNumVendors(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            configuration.setNumCustomers(Integer.parseInt(reader.readLine().split(":")[1].trim()));
            System.out.println("Configuration loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    private static void updateConfiguration(Scanner scanner) {
        System.out.println("\nUpdate Configuration:");

        // Validate Total Tickets
        int totalTickets;
        while (true) {
            System.out.print("Enter new Total Tickets (greater than 0): ");
            if (scanner.hasNextInt()) {
                totalTickets = scanner.nextInt();
                if (totalTickets > 0) {
                    break; // Exit the loop if the input is valid
                } else {
                    System.out.println("Invalid input! Total Tickets must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setTotalTickets(totalTickets);

        // Validate Ticket Release Rate
        int ticketReleaseRate;
        while (true) {
            System.out.print("Enter new Ticket Release Rate (seconds, greater than 0): ");
            if (scanner.hasNextInt()) {
                ticketReleaseRate = scanner.nextInt();
                if (ticketReleaseRate > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Ticket Release Rate must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setTicketReleaseRate(ticketReleaseRate);

        // Validate Customer Retrieval Rate
        int customerRetrievalRate;
        while (true) {
            System.out.print("Enter new Customer Retrieval Rate (seconds, greater than 0): ");
            if (scanner.hasNextInt()) {
                customerRetrievalRate = scanner.nextInt();
                if (customerRetrievalRate > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Customer Retrieval Rate must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setCustomerRetrievalRate(customerRetrievalRate);

        // Validate Max Ticket Capacity
        int maxTicketCapacity;
        while (true) {
            System.out.print("Enter new Max Ticket Capacity (greater than 0): ");
            if (scanner.hasNextInt()) {
                maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Max Ticket Capacity must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setMaxTicketCapacity(maxTicketCapacity);

        // Validate Number of Vendors
        int numVendors;
        while (true) {
            System.out.print("Enter new Number of Vendors (greater than 0): ");
            if (scanner.hasNextInt()) {
                numVendors = scanner.nextInt();
                if (numVendors > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Number of Vendors must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setNumVendors(numVendors);

        // Validate Number of Customers
        int numCustomers;
        while (true) {
            System.out.print("Enter new Number of Customers (greater than 0): ");
            if (scanner.hasNextInt()) {
                numCustomers = scanner.nextInt();
                if (numCustomers > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Number of Customers must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }
        configuration.setNumCustomers(numCustomers);

        saveConfigurationToFile();
    }



    private static void saveConfigurationToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
            writer.write("Total Tickets: " + configuration.getTotalTickets() + "\n");
            writer.write("Ticket Release Rate: " + configuration.getTicketReleaseRate() + "\n");
            writer.write("Customer Retrieval Rate: " + configuration.getCustomerRetrievalRate() + "\n");
            writer.write("Max Ticket Capacity: " + configuration.getMaxTicketCapacity() + "\n");
            writer.write("Number of Vendors: " + configuration.getNumVendors() + "\n");
            writer.write("Number of Customers: " + configuration.getNumCustomers() + "\n");
            System.out.println("Configuration saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static void viewAvailableTickets() {
        System.out.println("Available tickets: " + ticketPool.getTicketCount());
    }
}
