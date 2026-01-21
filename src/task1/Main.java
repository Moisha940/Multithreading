package task1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

// 2.1
// Write a Java program to implement a producer-consumer scenario
// with multiple producers and consumers using wait() and notifyAll().

// P.S. I added extra logic. Producer and consumers start their work depending on the queue size.
public class Main {
    public static final Queue<String> queue = new LinkedList<>();
    public static AtomicInteger taskNumber = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Producer producer_1 = new Producer(queue, 0, 10, taskNumber); // works always
        Producer producer_2 = new Producer(queue, 0, 3, taskNumber); // works when queue size [0, 3]
        Producer producer_3 = new Producer(queue, 0, 6, taskNumber); // works when queue size [0, 6]

        Consumer consumer_1 = new Consumer(queue, 0); // works always
        Consumer consumer_2 = new Consumer(queue, 4); // works when queue size is more than 4
        Consumer consumer_3 = new Consumer(queue, 7); // works when queue size is more than 7


        Thread consumerThread_1 = new Thread(consumer_1);
        Thread consumerThread_2 = new Thread(consumer_2);
        Thread consumerThread_3 = new Thread(consumer_3);
        Thread producerThread_1 = new Thread(producer_1);
        Thread producerThread_2 = new Thread(producer_2);
        Thread producerThread_3 = new Thread(producer_3);


        consumerThread_1.setName("Consumer-1");
        consumerThread_2.setName("Consumer-2");
        consumerThread_3.setName("Consumer-3");
        producerThread_1.setName("Producer-1");
        producerThread_2.setName("Producer-2");
        producerThread_3.setName("Producer-3");

        consumerThread_1.start();
        consumerThread_2.start();
        consumerThread_3.start();
        producerThread_1.start();
        producerThread_2.start();
        producerThread_3.start();
    }
}
