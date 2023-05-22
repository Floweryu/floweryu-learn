package concurent;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/05/22
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        final Future<Object> submit = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(new Date().toLocaleString() + "--开始执行");
                Thread.sleep(5000);
                System.out.println(new Date().toLocaleString() + "--执行完成");
                return "OK";
            }
        });
        Thread.sleep(4000);
        try {
            final Object o = submit.get(2, TimeUnit.SECONDS);
            System.out.println(o);
        } catch (TimeoutException e) {
            System.out.println(new Date().toLocaleString() + "--超时了");
        }
        service.shutdown();
    }
}
