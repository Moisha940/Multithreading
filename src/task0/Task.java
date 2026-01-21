package task0;

import java.time.LocalDateTime;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Task started at " + LocalDateTime.now());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Task finished at " + LocalDateTime.now());
            System.out.println("\n");
        }
    }
}
