package task0;

// 1.0
// Write a Java program to create and start multiple threads that increment a shared counter variable concurrently.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 5;
        int numberOfIncrements = 7;
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < numberOfIncrements; i++) {
                counter.increment();
            }
        };

        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        try {
            for (Thread thread: threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("task0.Counter value: " + counter.getCount());
    }
}