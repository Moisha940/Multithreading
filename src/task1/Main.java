package task1;


import java.util.concurrent.CountDownLatch;

// 6.1
// Write a Java program to synchronize thread startup using CountDownLatch
// and ensure all threads begin processing simultaneously.
public class Main {
    private static final int NUM_THREADS = 10;
    private static final CountDownLatch startLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(startLatch));
            threads[i].start();
            Thread.sleep(100);
        }

        startLatch.countDown();
        System.out.println("Все потоки готовы начать работу");

    }
}