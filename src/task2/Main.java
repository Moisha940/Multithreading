package task2;

// 2.2
// Write a Java program to simulate a bounded buffer where producers and consumers
// coordinate using wait() and notify(), ensuring no buffer overflow.
public class Main {
    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        Thread producerThread = new Thread(new Producer(boundedBuffer));
        Thread consumerThread = new Thread(new Consumer(boundedBuffer));
        consumerThread.setName("Consumer");
        producerThread.setName("Producer");

        consumerThread.start();
        producerThread.start();

    }
}