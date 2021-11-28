package com.floweryu.responsibility;

/**
 * @author Floweryu
 * @date 2021/11/28 21:32
 */
public class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("10~20 处理请求");
        } else if (successor != null) {
            super.successor.handleRequest(request);
        }
    }
}
