package concurent;

/**
 * @author zhangjunfeng
 * @date 2022/4/12 20:28
 */
public class JoinDemo {
    
    public static final int SLEEP_TIME = 3000;
    
    static class HotWaterThread extends Thread {
        HotWaterThread() {
            super("烧水-thread");
        }
        
        @Override
        public void run() {
            try {
                System.out.println("烧水中");
                Thread.sleep(SLEEP_TIME);
                System.out.println("水烧开了");
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("烧水运行结束");
        }
    }
    
    static class WashTeaThread extends Thread {
        WashTeaThread() {
            super("洗茶-thread");
        }

        @Override
        public void run() {
            try {
                System.out.println("洗茶中");
                Thread.sleep(SLEEP_TIME);
                System.out.println("茶洗完了");
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("洗茶运行结束");
        }
    }

    public static void main(String[] args) {
        HotWaterThread hotWaterThread = new HotWaterThread();
        WashTeaThread washTeaThread = new WashTeaThread();
        hotWaterThread.start();
        washTeaThread.start();
        try {
            hotWaterThread.join();
            washTeaThread.join();
            Thread.currentThread().setName("主线程");
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}
