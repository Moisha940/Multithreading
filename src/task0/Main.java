package task0;

import java.util.concurrent.ConcurrentHashMap;

//  8.0
//  Write a Java program demonstrating how to access a map concurrently using the ConcurrentHashMap class.
public class Main {

    private static final String SHARED_KEY = "counter";
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        int NUMBER_OF_THREADS = 10;
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(map, SHARED_KEY));
            threads[i].setName("T" + i);
            threads[i].start();
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("Размер карты: " + map.size());
        map.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}
