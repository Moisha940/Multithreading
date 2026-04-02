package task4;

import java.util.concurrent.Callable;


// Без класса обертки, исключение не будет выброшено, если не будет future.get(); в main
// А так оно будет выбрасываться не зависимо от наличия future.get(); в main
public class CallableWrapper<T> implements Callable<T> {
    private final Callable<T> originalCallable;

    public CallableWrapper(Callable<T> originalCallable) {
        this.originalCallable = originalCallable;
    }

    @Override
    public T call() throws Exception {
        try {
            return originalCallable.call();
        } catch (Throwable e) {
            System.out.println("CallableWrapper: " + e.getMessage());
            throw e;
        }
    }
}
