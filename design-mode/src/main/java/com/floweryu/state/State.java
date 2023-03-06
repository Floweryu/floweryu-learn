package com.floweryu.state;

/**
 * @author zhangjunfeng
 * @date 2023/3/3 17:36
 */
public abstract class State {
    Context context;
    public void setContext(Context context) {
        this.context = context;
    }
    public abstract void handle1();
    public abstract void handle2();
}
