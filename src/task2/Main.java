package task2;

import java.util.concurrent.*;

// 12.2
// Write a Java program to implement a timeout mechanism for asynchronous tasks
// using Future.get() with a timeout parameter.
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        FutureTask<String> futureTaskQuick = new FutureTask<>(task, "task finished");
        FutureTask<String> futureTaskLong = new FutureTask<>(task, "task finished");

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(futureTaskQuick);
        executorService.execute(futureTaskLong);


        try {
            futureTaskQuick.get(3000, TimeUnit.MILLISECONDS);
            System.out.println("Quick task finished");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("Small timeout finished with exception");
        }

        try {
            futureTaskLong.get(5000, TimeUnit.MILLISECONDS);
            System.out.println("Long task finished");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("Long timeout finished with exception");
        }

        executorService.shutdown();
    }
}
