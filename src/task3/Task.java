package task3;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    private final ScheduledExecutorService scheduler;
    private final Random random;

    public Task(ScheduledExecutorService scheduler, Random random) {
        this.scheduler = scheduler;
        this.random = random;
    }

    @Override
    public void run() {
        int number = random.nextInt(100);
        if (number % 2 == 0) {
            System.out.println(number + " is even");
            scheduler.schedule(this, 1L, TimeUnit.SECONDS);
        } else {
            System.out.println(number + " is odd");
            scheduler.schedule(this, 2L, TimeUnit.SECONDS);
        }
    }
}
