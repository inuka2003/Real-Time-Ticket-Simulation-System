package com.coursework.w2052208.entity;

public class Log {
    private String threadName;
    private String action;
    private String ticketNumber;

    // Represents a log entry for tracking actions performed by threads.
    public Log(String threadName, String action, String ticketNumber) {
        this.threadName = threadName;
        this.action = action;
        this.ticketNumber = ticketNumber;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {

        this.ticketNumber = ticketNumber;
    }
}
