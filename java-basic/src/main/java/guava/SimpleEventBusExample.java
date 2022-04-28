package guava;

import com.google.common.eventbus.EventBus;

/**
 * @author zhangjunfeng
 * @date 2022/4/28 15:54
 */
public class SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        //注册Listener
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        //向订阅者发送消息
        eventBus.post("Simple Event");
    }
}
