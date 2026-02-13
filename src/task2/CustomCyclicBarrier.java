package task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomCyclicBarrier {
    private final int numberOfAwaits;
    private final Runnable finalTask;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition overcameBarrier = lock.newCondition();
    private int countArrive = 0;
    private Generation generation = new Generation();

    static class Generation {
        boolean broken = false;
    }

    public CustomCyclicBarrier(int numberOfAwaits) {
        this.numberOfAwaits = numberOfAwaits;
        this.finalTask = null;
    }

    public CustomCyclicBarrier(int numberOfAwaits, Runnable finalTask) {
        this.finalTask = finalTask;
        this.numberOfAwaits = numberOfAwaits;
    }

    public void await() throws InterruptedException, BrokenBarrierException {
        try {
            lock.lock();
            Generation g = generation;
            if (g.broken) {
                throw new BrokenBarrierException("Barrier is broken");
            }

            ++countArrive;

            if (countArrive == numberOfAwaits) {
                if (finalTask != null) {
                    try {
                        finalTask.run();
                    } catch (RuntimeException e) {
                        g.broken = true;
                        overcameBarrier.signalAll();
                        throw e;
                    }
                }
                overcameBarrier.signalAll();
                newGeneration();
                return;
            }

            while (true) {
                try {
                    overcameBarrier.await();
                } catch (InterruptedException e) {
                    g.broken = true;
                    overcameBarrier.signalAll();
                    throw new BrokenBarrierException("Interrupted while waiting");
                }

                if (g.broken) {
                    throw new BrokenBarrierException("Barrier is broken");
                }

                if (g != generation) {
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void await(long time, TimeUnit timeUnit) throws TimeoutException, BrokenBarrierException {
        try {
            lock.lock();
            Generation g = generation;

            if (g.broken) {
                throw new BrokenBarrierException("Barrier is broken");
            }

            ++countArrive;

            if (countArrive == numberOfAwaits) {
                if (finalTask != null) {
                    try {
                        finalTask.run();
                    } catch (RuntimeException e) {
                        g.broken = true;
                        overcameBarrier.signalAll();
                        throw new BrokenBarrierException("Interrupted while waiting");
                    }
                }
                overcameBarrier.signalAll();
                newGeneration();
                return;
            }

            while (true) {
                try {
                    if (!overcameBarrier.await(time, timeUnit)) {
                        throw new TimeoutException("Timed out waiting at barrier");
                    }
                } catch (InterruptedException e) {
                    g.broken = true;
                    overcameBarrier.signalAll();
                    throw new BrokenBarrierException("Interrupted while waiting");
                } catch (TimeoutException e) {
                    g.broken = true;
                    overcameBarrier.signalAll();
                    throw new TimeoutException(e.getMessage());
                }
                if (g.broken) {
                    throw new BrokenBarrierException("Barrier is broken");
                }

                if (g != generation) {
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }


    private void newGeneration() {
        countArrive = 0;
        generation = new Generation();
    }
}
