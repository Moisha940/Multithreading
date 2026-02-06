package task3;


// 4.3
// Write a Java program to create a resource pool
// where a semaphore manages the available resource count concurrently.
public class Main {
    public static final int NUMBER_OF_THREADS = 10;
    public static final int NUMBER_OF_PERMITS = 2;
    public static void main(String[] args) throws InterruptedException {
        ResourcePool resourcePool = new ResourcePool(NUMBER_OF_PERMITS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(resourcePool));
            threads[i].setName("Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n============ Demonstration completed. ============");
    }
}
