package task3;

public class CustomThread implements Runnable {
    private final ResourcePool pool;

    public CustomThread(ResourcePool semaphore) {
        this.pool = semaphore;
    }

    @Override
    public void run() {
        pool.connect();
    }
}
