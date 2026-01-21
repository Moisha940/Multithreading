package task2;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBuffer {
    public static int taskNumber = 0;
    public static final Integer MAX_QUEUE_SIZE = 10;
    public static final Queue<String> queue = new LinkedList<>();

    public synchronized void produce() {
        while (queue.size() >= MAX_QUEUE_SIZE) {
            System.out.println("Queue is full");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Produced task " + taskNumber);
        queue.offer("task " + taskNumber);
        taskNumber++;
        notify();
    }

    public synchronized void consume() {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumed task " + queue.poll());
        notify();
    }
}

