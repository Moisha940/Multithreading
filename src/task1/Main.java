package task1;

// 4.1
// Write a Java program to implement a semaphore that limits concurrent access to a resource among multiple threads.
public class Main {
    private static final int NUMBER_OF_THREADS = 5;
    private static final int NUMBER_OF_PERMITS = 2;

    public static void main(String[] args) throws InterruptedException {
        //CustomSemaphore semaphore = new SynchronizedCustomSemaphore(NUMBER_OF_PERMITS);
         CustomSemaphore semaphore = new SynchronizedCustomSemaphore(NUMBER_OF_PERMITS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(semaphore));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nDemonstration completed.");
    }
}