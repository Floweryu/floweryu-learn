package concurent.virtual;

/**
 * @Author zhangjunfeng
 * @Date 2025/5/13 22:40
 */
public class VirtualThread {

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> System.out.println("Hello from virtual thread!");
        Thread.startVirtualThread(runnable);
        Thread.sleep(3000);
    }
}
