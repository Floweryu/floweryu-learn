package com.floweryu.responsibility;

/**
 * @author Floweryu
 * @date 2021/11/28 21:34
 */
public class ConcreteHandler3 extends Handler {
    
    @Override
    public void handleRequest(int request) {
        if (request >= 20 && request < 30) {
            System.out.println("20~30 处理请求");
        } else if (successor != null) {
            super.successor.handleRequest(request);
        }
    }
}
