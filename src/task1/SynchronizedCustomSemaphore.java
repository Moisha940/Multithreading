package task1;

public class SynchronizedCustomSemaphore implements CustomSemaphore {
    private final Object lock = new Object();
    private int numberOfPermits;

    public SynchronizedCustomSemaphore(int numberOfPermits) {
        this.numberOfPermits = numberOfPermits;
    }

    @Override
    public void acquire() {
        synchronized (lock) {
            while (numberOfPermits == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            --numberOfPermits;
        }
    }

    @Override
    public void release() {
        synchronized (lock) {
            ++numberOfPermits;
            lock.notifyAll();
        }
    }
}
