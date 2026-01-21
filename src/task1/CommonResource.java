package task1;

public class CommonResource {
    int x;

    public void increment() {
        System.out.println(Thread.currentThread().getName() + " increment");
        x++;
    }

    public int getX() {
        return x;
    }
}
