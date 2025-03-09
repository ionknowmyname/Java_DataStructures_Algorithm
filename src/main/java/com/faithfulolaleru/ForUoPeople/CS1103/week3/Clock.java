package com.faithfulolaleru.ForUoPeople.CS1103.week3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    private volatile LocalDateTime currentTime;
    private volatile boolean running;
    private final DateTimeFormatter formatter;
    private final Thread updateThread;
    private final Thread displayThread;

    public Clock() {
        this.currentTime = LocalDateTime.now();
        this.running = true;
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        // Initialize update thread with lower priority
        this.updateThread = new Thread(() -> {
            try {
                while (running) {
                    updateTime();
                    Thread.sleep(100); // Update every 100ms
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Update thread interrupted: " + e.getMessage());
            }
        }, "TimeUpdateThread");
        updateThread.setPriority(Thread.NORM_PRIORITY);

        // Initialize display thread with higher priority
        this.displayThread = new Thread(() -> {
            try {
                while (running) {
                    displayTime();
                    Thread.sleep(1000); // Display every second
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Display thread interrupted: " + e.getMessage());
            }
        }, "TimeDisplayThread");
        displayThread.setPriority(Thread.MAX_PRIORITY);
    }

    public void start() {
        System.out.println("Starting clock application...");
        System.out.println("Update thread priority: " + updateThread.getPriority());
        System.out.println("Display thread priority: " + displayThread.getPriority());

        updateThread.start();
        displayThread.start();
    }

    public void stop() {
        running = false;
        updateThread.interrupt();
        displayThread.interrupt();
    }

    private synchronized void updateTime() {
        currentTime = LocalDateTime.now();
    }

    private synchronized void displayTime() {
        // Clear the previous line and move cursor to beginning
        System.out.print("\r");
        // Display formatted time and date
        System.out.print("Current Time: " + currentTime.format(formatter));
    }
}
