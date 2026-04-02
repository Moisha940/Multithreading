package task4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 12.4
// Write a Java program to handle exceptions in asynchronous tasks
// by wrapping Callable tasks and processing Future exceptions.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CallableWrapper<Integer> callableWrapper = new CallableWrapper<>(new CallableImpl<>());
        Future<Integer> future = executorService.submit(callableWrapper);

        try {
            future.get();
        } catch (ExecutionException e) {
            System.out.println("Main " + e.getCause());
        } finally {
            executorService.shutdown();
        }
    }
}
