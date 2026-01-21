package task3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

// 1.3
// Write a Java program to implement multiple threads that increment a shared counter
// and use a CountDownLatch to wait for all threads to finish.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        int numberOfIncrements = 73;

        AtomicInteger counter = new AtomicInteger(0);
        Thread[] threads = new Thread[numberOfThreads];
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        Runnable runnable = () -> {
            for (int i = 0; i < numberOfIncrements; i++) {
                counter.incrementAndGet();
            }
            countDownLatch.countDown();
        };

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        countDownLatch.await();
        System.out.println(counter.get());

    }
}
