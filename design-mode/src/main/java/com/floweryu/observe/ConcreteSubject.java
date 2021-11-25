package com.floweryu.observe;

/**
 * @author Floweryu
 * @date 2021/11/21 15:36
 */
public class ConcreteSubject extends Subject{
    @Override
    public void doSomething() {
        System.out.println("被观察者事件发生改变");
        this.notifyObserver();
    }
}
