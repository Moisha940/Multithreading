package task3;


import java.util.concurrent.locks.ReentrantLock;

public class CustomThread implements Runnable {
    private final CommonResource commonResource;
    private final ReentrantLock lockA;
    private final ReentrantLock lockB;
    private final ReentrantLock lockC;

    public CustomThread(CommonResource commonResource, ReentrantLock lockA, ReentrantLock lockB, ReentrantLock lockC) {
        this.commonResource = commonResource;
        this.lockA = lockA;
        this.lockB = lockB;
        this.lockC = lockC;
    }

    @Override
    public void run() {
        try {
            lockA.lock();
            try {
                lockB.lock();
                try {
                    lockC.lock();
                    for (int i = 0; i < 1000; i++) {
                        commonResource.increment();
                    }
                } finally {
                    lockC.unlock();
                }
            } finally {
                lockB.unlock();
            }
        } finally {
            lockA.unlock();
        }
    }
}
