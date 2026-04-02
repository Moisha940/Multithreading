package task3;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 12.3
// Write a Java program to chain multiple Callable tasks and combine their results
// using Future and CompletableFuture.
public class Main {
    public static void main(String[] args) {
        int n = 123;
        int numberOfThreads = 10;
        List<Callable<Integer>> callables = generateCallable(n, numberOfThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try {
            List<Future<Integer>> futures = executor.invokeAll(callables);
            List<CompletableFuture<Integer>> cfList = futures.stream()
                    .map(future -> CompletableFuture.supplyAsync(
                            () -> {
                                try {
                                    return future.get();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            },
                            executor)
                    )
                    .toList();

            CompletableFuture<Integer> finalResult = CompletableFuture
                    .allOf(cfList.toArray(new CompletableFuture[0]))
                    .thenApply(v -> cfList.stream()
                            .map(CompletableFuture::join)
                            .reduce(0, Integer::sum)
                    );

            finalResult.thenAccept(result -> System.out.println("Result from callables " + result));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
        check(n);
    }

    private static List<Callable<Integer>> generateCallable(int n, int numberOfThreads) {
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * n / numberOfThreads;
            int finish = (i + 1) * n / numberOfThreads;
            callables.add(new CallableImpl(start, finish));
        }
        return callables;
    }

    private static void check(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        System.out.println("Correct answer " + sum);
    }
}
