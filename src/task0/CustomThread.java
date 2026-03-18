package task0;

import java.util.concurrent.ConcurrentHashMap;

public class CustomThread implements Runnable {
    private final ConcurrentHashMap<String, Integer> map;
    private final String key;

    public CustomThread(ConcurrentHashMap<String, Integer> map, String key) {
        this.map = map;
        this.key = key;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            map.compute(key, (k, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        }
    }
}
