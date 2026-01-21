package task0;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 13.0
// Write a Java program that uses the ScheduledExecutorService interface
// to schedule tasks for execution at a specified time or with a fixed delay.
public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(new Task(), 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new Task(), 2L, 3L, TimeUnit.SECONDS);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
