package task0;

import java.util.concurrent.Callable;

public class MaxFinderTask implements Callable<Integer> {
    private final int[] arr;
    private final int start;
    private final int end;

    public MaxFinderTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int localMax = arr[start];
        for (int i = start + 1; i < end; i++) {
            if (arr[i] > localMax) {
                localMax = arr[i];
            }
        }
        return localMax;
    }
}
