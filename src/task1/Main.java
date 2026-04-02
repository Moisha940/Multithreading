package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 12.1
// Write a Java program to submit multiple Callable tasks to an ExecutorService
// and retrieve their results using Future.get().
public class Main {
    public static void main(String[] args) {
        int NUMBER_OF_TASKS = 5;
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_TASKS);
        List<Future<Integer>> futures = new ArrayList<>();
        List<Callable<Integer>> callables = generateTasks(NUMBER_OF_TASKS);

        try {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                futures.add(service.submit(callables.get(i)));
            }

            int ans = 0;
            for (Future<Integer> future : futures) {
                ans += future.get();
            }
            System.out.println("Сумма первых " + NUMBER_OF_TASKS + " факториалов = " + ans);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            System.err.println("Ошибка при выполнении задач: " + e.getMessage());
        } finally {
            service.shutdown();
        }
    }

    private static List<Callable<Integer>> generateTasks(int numberOfTasks) {
        List<Callable<Integer>> callables = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            callables.add(new CallableImpl(i + 1));
        }
        return callables;
    }
}
