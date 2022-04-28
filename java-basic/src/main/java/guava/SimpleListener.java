package guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author zhangjunfeng
 * @date 2022/4/28 15:52
 */
public class SimpleListener {
    @Subscribe
    public void doAction(final String event){
        System.out.println("监听到消息: " + event);
    }
}
