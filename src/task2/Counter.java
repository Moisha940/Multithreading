package task2;

public class Counter {
    private int count;
    private final Object monitor = new Object();

    public void increment() {
        synchronized (monitor) {
            count++;
        }
    }

    public int getCount() {
        synchronized (monitor) {
            return count;
        }
    }
}