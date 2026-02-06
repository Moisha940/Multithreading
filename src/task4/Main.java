package task4;


import java.util.concurrent.Semaphore;

// 4.4
// Write a Java program to test semaphore fairness by
// simulating high contention among threads and logging their access order.
public class Main {
    public static final int NUMBER_OF_THREADS = 5;
    public static final int NUMBER_OF_PERMITS = 2;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(NUMBER_OF_PERMITS, true);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(semaphore));
            threads[i].setName("Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
            Thread.sleep(100);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n============ Demonstration completed. ============");
    }
}
