package task1;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private final Queue<String> queue;
    private final Integer SIZE_TO_STOP;
    private final Integer SIZE_TO_START;
    private AtomicInteger taskNumber;

    public Producer(Queue<String> queue, Integer SIZE_TO_START, Integer SIZE_TO_STOP, AtomicInteger taskNumber) {
        this.queue = queue;
        this.SIZE_TO_START = SIZE_TO_START;
        this.SIZE_TO_STOP = SIZE_TO_STOP;
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 10 || (queue.size() >= SIZE_TO_STOP || queue.size() < SIZE_TO_START)) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add("task № " + taskNumber.incrementAndGet());
                System.out.println("Produced task № " + taskNumber.get() + " by " + Thread.currentThread().getName() + ". Queue size: " + queue.size());


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
