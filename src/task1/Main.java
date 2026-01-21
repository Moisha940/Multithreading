package task1;

// 1.1
// Write a Java program to create multiple threads that increment a shared counter using
// AtomicInteger for thread-safe operations.
public class Main {
    public static void main(String[] args) {
        int numOfThreads = 8;
        int numOfIncrements = 7;

        Counter counter = new Counter();
        Thread[] threads = new Thread[numOfThreads];

        Runnable runnable = () -> {
            for (int i = 0; i < numOfIncrements; i++) {
                counter.increment();
            }
        };

        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }


        try {
            for (int i = 0; i < numOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.get());
    }
}
