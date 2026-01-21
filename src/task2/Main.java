package task2;

// 1.2
// Write a Java program to create multiple threads that increment a shared counter
// using synchronized blocks and then print the final value.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 10;
        int numberOfIncrements = 723;

        Thread[] threads = new Thread[numberOfThreads];
        Counter counter = new Counter();

        Runnable r = () -> {
            for (int i = 0; i < numberOfIncrements; i++) {
                counter.increment();
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }

        try {
          for (int i = 0; i < numberOfThreads; i++) {
              threads[i].join();
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getCount());
    }
}
