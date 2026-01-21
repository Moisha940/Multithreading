package task1;

import java.util.concurrent.locks.ReentrantLock;

// 3.1

// Write a Java program to use ReentrantLock’s tryLock() method
// to safely access a shared resource without deadlocking.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 10;

        CommonResource cr = new CommonResource();
        ReentrantLock lock = new ReentrantLock();
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new CustomThread(cr, lock));
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
