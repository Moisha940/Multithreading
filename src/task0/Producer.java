package task0;

import java.util.Queue;

public class Producer implements Runnable {
    private final Queue<String> queue;
    private final Integer MAX_QUEUE_SIZE;

    public Producer(Queue<String> queue, Integer maxQueueSize) {
        this.queue = queue;
        this.MAX_QUEUE_SIZE = maxQueueSize;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (queue) {
                while (queue.size() == MAX_QUEUE_SIZE) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.add("task № " + ++i);
                System.out.println("Produced task № " + i);
                queue.notifyAll();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
