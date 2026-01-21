package task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger count;

    public void increment() {
        this.count.getAndIncrement();
    }

    public int get() {
        return count.get();
    }
}
