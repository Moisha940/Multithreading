package task3;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<Integer> {
    private final int start;
    private final int finish;

    public CallableImpl(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " " + start + " " + finish);
        int sum = 0;
        for (int i = start; i < finish; i++) {
            sum += i;
        }
        return sum;
    }
}
