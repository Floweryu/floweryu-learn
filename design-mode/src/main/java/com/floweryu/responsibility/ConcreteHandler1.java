package com.floweryu.responsibility;

/**
 * @author Floweryu
 * @date 2021/11/28 21:28
 */
public class ConcreteHandler1 extends Handler{
    
    @Override
    public void handleRequest(int request) {
        if (request > 0 && request < 10) {
            System.out.println("0~10 处理请求");
        } else if (successor != null) {
            super.successor.handleRequest(request);
        }
    }
}
