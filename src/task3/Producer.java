package task3;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
    private final Queue<String> queue;
    private final Integer BATCH_SIZE;
    private final AtomicBoolean consumingFlag;

    public Producer(Queue<String> queue, Integer batchSize, AtomicBoolean flag) {
        this.queue = queue;
        this.BATCH_SIZE = batchSize;
        this.consumingFlag = flag;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (queue) {
                while (consumingFlag.get()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("Produced task: " + ++i);
                queue.offer("task " + i);

                if (queue.size() == BATCH_SIZE) {
                    consumingFlag.set(true);
                    queue.notify();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
