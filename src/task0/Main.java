package task0;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 12.0
// Write a Java program to demonstrate the usage of the Callable and Future interfaces
// for executing tasks asynchronously and obtaining their results.
public class Main {
    public static int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) throws Exception {
        int n = 1_000_000;
        int[] arr = generateArray(n);

        int max = findMaxParallel(arr, 8);
        System.out.println("Глобальный максимум: " + max);
    }

    public static int findMaxParallel(int[] arr, int numThreads) throws Exception {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> futures = new ArrayList<>();

        int chunkSize = (arr.length + numThreads - 1) / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, arr.length);

            if (start >= end) break;

            futures.add(executor.submit(new MaxFinderTask(arr, start, end)));
        }

        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            int localMax = future.get();
            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }

        executor.shutdown();
        return globalMax;
    }

    public static int[] generateArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1_000);
        }
        return arr;
    }
}
