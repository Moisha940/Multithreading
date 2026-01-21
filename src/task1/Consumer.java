package task1;

import java.util.Queue;

public class Consumer implements Runnable {
    private final Queue<String> queue;
    private final Integer SIZE_TO_START;

    public Consumer(Queue<String> queue, Integer SIZE_TO_START) {
        this.queue = queue;
        this.SIZE_TO_START = SIZE_TO_START;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() <= SIZE_TO_START) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Consumed " + queue.poll() + " by " + Thread.currentThread().getName() + ". Queue size: " + queue.size());
                queue.notifyAll();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
