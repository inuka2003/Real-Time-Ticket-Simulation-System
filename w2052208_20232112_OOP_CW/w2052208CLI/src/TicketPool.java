import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maximumTicketCapacity;
    private final int totalTickets;
    private final Queue<Ticket> ticketQueue;
    private int ticketCounter;

    public TicketPool(int maximumTicketCapacity, int totalTickets) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketQueue = new LinkedList<>();
        this.ticketCounter = 0;
    }

    // Synchronized method for adding tickets
    public synchronized void addTicket() {
        while (ticketQueue.size() >= maximumTicketCapacity || ticketCounter > totalTickets) {
            System.out.println(Thread.currentThread().getName() + " cannot add ticket: Ticket pool is full.");
            try {
                wait(); // Wait if the pool is full or all tickets are added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        Ticket ticket = new Ticket(ticketCounter++);
        ticketQueue.add(ticket);
        System.out.println(Thread.currentThread().getName() + " added " + ticket + "Current Ticket Pool Count: " + ticketQueue.size());
        notifyAll(); // Notify customers waiting to buy tickets
    }

    // Synchronized method for buying tickets
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " cannot get ticket: Ticket pool is empty.");
            try {
                wait(); // Wait if no tickets are available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Ticket ticket = ticketQueue.poll();
        System.out.println(Thread.currentThread().getName() + " bought " + ticket + "Current Ticket Pool Count: " + ticketQueue.size());
        notifyAll(); // Notify vendors that space is available
        return ticket;
    }

    // Method to get the current count of available tickets
    public synchronized int getTicketCount() {
        return ticketQueue.size();
    }
}
