package task2;

// 4.2
// Write a Java program to simulate a fixed-size thread pool using semaphores
// to control the number of active threads.
public class Main {
    private static final int NUMBER_OF_THREADS = 5;
    private static final int NUMBER_OF_PERMITS = 2;

    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(NUMBER_OF_THREADS, NUMBER_OF_PERMITS);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.addTask(() -> {
                System.out.println(Thread.currentThread().getName() + " is processing task " + finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}