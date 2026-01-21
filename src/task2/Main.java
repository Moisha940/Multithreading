package task2;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 13.2
// Write a Java program to schedule a one-time task with a delay using ScheduledExecutorService
// and reschedule it on completion.
public class Main {
    public static void main(String[] args) {
        long initDelay = 2;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(new Task(scheduler,  3), initDelay, TimeUnit.SECONDS);
    }
}