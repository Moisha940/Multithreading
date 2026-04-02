package task1;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<Integer> {
    public final int n;

    public CallableImpl(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return (int) result;
    }
}
