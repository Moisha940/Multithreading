package task3;


import java.util.concurrent.CountDownLatch;

// 6.3
// Write a Java program to use CountDownLatch to measure the total execution time of concurrent threads.
public class Main {
    private static final int NUM_THREADS = 10;
    private static final CountDownLatch startLatch = new CountDownLatch(1);
    private static final CountDownLatch finishLatch = new CountDownLatch(NUM_THREADS);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(startLatch, finishLatch));
            threads[i].start();
            Thread.sleep(100);
        }

        System.out.println("Все потоки запущенны и готовы приступить к выполнению задания");
        long start = System.currentTimeMillis();
        startLatch.countDown();
        finishLatch.await();
        long finish = System.currentTimeMillis() - start;
        System.out.println("Все потоки выполнили работу за " + finish + " ms");
    }
}