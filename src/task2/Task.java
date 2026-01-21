package task2;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final ScheduledExecutorService scheduler;
    private final int NUMBER_OF_REPETITIONS;
    private int counter;

    public Task(ScheduledExecutorService scheduler, int NUMBER_OF_REPETITIONS) {
        this.scheduler = scheduler;
        this.NUMBER_OF_REPETITIONS = NUMBER_OF_REPETITIONS;
    }

    @Override
    public void run() {
        if (counter < NUMBER_OF_REPETITIONS) {
            System.out.println("Processing one time task...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("One time task completed\n");
            ++counter;
            scheduler.schedule(this, 0L, TimeUnit.SECONDS);
        } else {
            scheduler.shutdown();
        }
    }
}
