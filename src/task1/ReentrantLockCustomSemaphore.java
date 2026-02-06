package task1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCustomSemaphore implements CustomSemaphore {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    private int numberOfPermits;

    public ReentrantLockCustomSemaphore(int numberOfPermits) {
        this.numberOfPermits = numberOfPermits;
    }

    @Override
    public void acquire() {
        try {
            lock.lock();
            while (numberOfPermits == 0) {
                permitsAvailable.await();
            }
            --numberOfPermits;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void release() {
        try {
            lock.lock();
            ++numberOfPermits;
            permitsAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
