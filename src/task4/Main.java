package task4;


import java.util.concurrent.locks.ReentrantLock;

// 3.4

// Write a Java program to compare performance differences
// between synchronized blocks and ReentrantLock in concurrent access scenarios.
public class Main {
    private static final CommonResource cr = new CommonResource();
    private static final int numberOfThreads = 1000;
    private static final int numberOfTests = 100;
    private static long start;
    private static long end;

    public static void main(String[] args) {
        System.out.println("Start experiments for ReentrantLock...");
        start = System.nanoTime();
        lockTask();
        end = System.nanoTime();
        long averageTimeForLock = (end - start) / numberOfTests;
        System.out.println("ReentrantLock experiments were completed in " + averageTimeForLock);

        System.out.println("Start experiments for Synchronized block...");
        start = System.nanoTime();
        syncTask();
        end = System.nanoTime();
        long averageTimeForSynchronized = (end - start) / numberOfTests;
        System.out.println("Synchronized block experiments were completed in " + averageTimeForSynchronized);

        if (averageTimeForLock > averageTimeForSynchronized) {
            System.out.println("\n++++++++\nSync was faster: " + (averageTimeForLock - averageTimeForSynchronized));
        } else {
            System.out.println("\n++++++++\nlock was faster: " + (averageTimeForSynchronized - averageTimeForLock));
        }
    }

    private static void lockTask() {
        for (int j = 0; j < numberOfTests; j++) {
            ReentrantLock lock = new ReentrantLock();
            Thread[] threadsForReentrantLockTask = new Thread[numberOfThreads];

            for (int i = 0; i < numberOfThreads; i++) {
                threadsForReentrantLockTask[i] = new Thread(new CustomLockThread(cr, lock));
            }

            startThreads(threadsForReentrantLockTask);

            cr.setX(0);
        }
    }

    private static void syncTask() {
        for (int j = 0; j < numberOfTests; j++) {
            Thread[] threadsForSynchronizedTask = new Thread[numberOfThreads];
            Object monitor = new Object();

            for (int i = 0; i < numberOfThreads; i++) {
                threadsForSynchronizedTask[i] = new Thread(new CustomSynchronizedThread(cr, monitor));
            }
            startThreads(threadsForSynchronizedTask);
            cr.setX(0);
        }
    }

    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
