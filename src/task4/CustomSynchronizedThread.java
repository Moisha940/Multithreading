package task4;

public class CustomSynchronizedThread implements Runnable {
    private final CommonResource commonResource;
    private final Object lock;

    public CustomSynchronizedThread(CommonResource commonResource, Object lock) {
        this.commonResource = commonResource;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 1_000_000; i++) {
                commonResource.increment();
            }
        }
    }
}
