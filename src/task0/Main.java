package task0;

import java.util.LinkedList;
import java.util.Queue;

// 2.0
// Write a Java program to create a producer-consumer scenario
// using the wait() and notify() methods for thread synchronization.
public class Main {
    public static final Integer MAX_QUEUE_SIZE = 10;
    public static final Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue, MAX_QUEUE_SIZE);

        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);

        consumerThread.setName("Consumer Thread");
        producerThread.setName("Producer Thread");

        consumerThread.start();
        producerThread.start();
    }
}