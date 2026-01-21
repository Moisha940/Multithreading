package task0;

import java.util.concurrent.locks.ReentrantLock;

// 3.0
// Write a Java program that uses the ReentrantLock class to synchronize access
// to a shared resource among multiple threads.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1000;

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