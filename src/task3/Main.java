package task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

// task 2.3

// Write a Java program to implement a producer-consumer system
// where the consumer processes items in batches after being notified by the producer.
public class Main {
    public static Queue<String> queue = new LinkedList<>();
    public static Integer BATCH_SIZE = 6;
    public static AtomicBoolean consumingFlag = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread consumerThread = new Thread(new Consumer(queue, consumingFlag));
        Thread producerThread = new Thread(new Producer(queue, BATCH_SIZE, consumingFlag));
        consumerThread.setName("Consumer");
        producerThread.setName("Producer");

        consumerThread.start();
        producerThread.start();
    }
}
