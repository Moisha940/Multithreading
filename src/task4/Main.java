package task4;


import java.util.concurrent.CountDownLatch;

// 6.4
// Write a Java program to simulate a race condition where CountDownLatch signals the start
// and finish of multiple threads.
public class Main {
    private static final int NUM_THREADS = 100;
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
        startLatch.countDown();
        finishLatch.await();
        System.out.println("Все потоки закончили работу");

    }
}