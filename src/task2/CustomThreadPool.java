package task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> blockingQueueForTasks = new LinkedBlockingQueue<>();
    private final Thread[] threads;
    private final Semaphore semaphore;

    public CustomThreadPool(int threadPoolSize, int numberOfActiveThreads) {
        this.semaphore = new Semaphore(numberOfActiveThreads);

        this.threads = new Thread[threadPoolSize];
        for (int i = 0; i < threadPoolSize; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    public void addTask(Runnable task) {
        try {
            blockingQueueForTasks.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    final class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    semaphore.acquire();
                    Runnable task = blockingQueueForTasks.take();
                    task.run();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }
        }
    }
}
