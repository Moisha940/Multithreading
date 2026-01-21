package task1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// 13.1
// Write a Java program to schedule a periodic task using ScheduledExecutorService
// and cancel it after a fixed duration.
public class Main {
    public static void main(String[] args) {
        long initialDelay = 0;
        long period = 500;
        long cancelDuration = 7;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(
                new Task(),
                initialDelay,
                period,
                TimeUnit.MILLISECONDS
        );

        scheduler.schedule(() -> {
            System.out.println("=== Отменяем периодическую задачу ===");
            boolean cancelled = future.cancel(false); // false = не прерывать уже запущенную задачу
            System.out.println("Задача отменена: " + cancelled);
        }, cancelDuration, TimeUnit.SECONDS);

        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Программа завершена.");
    }
}
