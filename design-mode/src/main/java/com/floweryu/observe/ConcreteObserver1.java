package com.floweryu.observe;

/**
 * @author Floweryu
 * @date 2021/11/21 15:39
 */
public class ConcreteObserver1 implements Observer {

    @Override
    public void update() {
        System.out.println("观察者1收到信息，并进行处理");
    }
}
