package task3;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// 13.3
// Write a Java program to implement a dynamic interval task scheduler using ScheduledExecutorService
// that adjusts its delay based on task output.
public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.submit(new Task(scheduler, new Random()));
    }
}
