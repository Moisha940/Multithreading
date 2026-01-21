package task2;


import java.util.concurrent.locks.ReentrantLock;

// 3.2

// Write a Java program to implement a shared resource access mechanism
// using ReentrantLock with fairness enabled.
public class Main {
    public final static int numberOfThreads = 10;

    public static void main(String[] args) {
        CommonResource cr = new CommonResource();
        ReentrantLock lock = new ReentrantLock(true);
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new CustomThread(cr, lock));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].setName(String.valueOf(i));
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nResult: " + cr.getX());
    }
}
