package basic;

public class ConcurrentCounter {
    private int count;
    public ConcurrentCounter() {
        this.count = 0;
    }
    public void increase() {
        synchronized (this){
            count += 1;
        }
    }

    public int getCount() {
        return this.count;
    }
}
