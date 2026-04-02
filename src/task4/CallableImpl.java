package task4;

import java.util.concurrent.Callable;

public class CallableImpl<T> implements Callable<T> {

    @Override
    public T call() throws Exception {
        throw new Exception("Oh no, exception in CallableImpl!");
    }
}
