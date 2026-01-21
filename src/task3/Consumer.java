package task3;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {
    public final Queue<String> queue;
    public final AtomicBoolean consumingFlag;

    public Consumer(Queue<String> queue, AtomicBoolean consumingFlag) {
        this.queue = queue;
        this.consumingFlag = consumingFlag;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (!consumingFlag.get()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("Consumed " + queue.poll());

                if (queue.isEmpty()) {
                    consumingFlag.set(false);
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
