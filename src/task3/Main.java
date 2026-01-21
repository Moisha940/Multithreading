package task3;

import java.util.concurrent.locks.ReentrantLock;

// 3.3
// Write a Java program to simulate nested locking
// with ReentrantLock where multiple locks are acquired in sequence.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1000;

        CommonResource cr = new CommonResource();
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();
        ReentrantLock lockC = new ReentrantLock();
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new CustomThread(cr, lockA, lockB, lockC));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cr.getX());
    }
}
