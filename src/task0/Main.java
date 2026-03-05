package task0;


import java.util.concurrent.CountDownLatch;

// 6.0
// Write a Java program that uses the CountDownLatch class
// to synchronize the start and finish of multiple threads.
public class Main {
    private static final int NUM_THREADS = 3;
    private static final CountDownLatch startLatch = new CountDownLatch(1);
    private static final CountDownLatch finishLatch = new CountDownLatch(NUM_THREADS);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(startLatch, finishLatch));
            threads[i].start();
            Thread.sleep(100);
        }

        startLatch.countDown();
        System.out.println("Все потоки готовы начать работу");
        finishLatch.await();
        System.out.println("Все потоки выполнили задание");

    }
}