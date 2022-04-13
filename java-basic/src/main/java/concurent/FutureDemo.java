package concurent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zhangjunfeng
 * @date 2022/4/12 20:46
 */
public class FutureDemo {
    public static final int SLEEP_TIME = 3000;
    
    static class HotWaterThread implements Callable<Boolean> {
        @Override
        public Boolean call() {
            try {
                System.out.println("烧水中");
                Thread.sleep(SLEEP_TIME);
                System.out.println("水烧开了");
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("烧水运行结束");
            return true;
        }
    }

    static class WashTeaThread implements Callable<Boolean> {

        @Override
        public Boolean call() {
            try {
                System.out.println("洗茶中");
                Thread.sleep(SLEEP_TIME);
                System.out.println("茶洗完了");
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            System.out.println("洗茶运行结束");
            return true;
        }
    }

    public static void main(String[] args) {
        FutureTask<Boolean> hotWater = new FutureTask<>(new HotWaterThread());
        FutureTask<Boolean> washTea = new FutureTask<>(new WashTeaThread());
        
        Thread hotWaterThread = new Thread(hotWater, "烧水-thread");
        Thread washTeaThread = new Thread(washTea, "洗茶-thread");
        
        hotWaterThread.start();
        washTeaThread.start();
        Thread.currentThread().setName("主线程");
        try {
            if (hotWater.get() && washTea.get()) {
                System.out.println("可以泡茶了---");
            } else if (hotWater.get()) {
                System.out.println("水烧开了---");
            } else if (washTea.get()) {
                System.out.println("茶洗好了---");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}
