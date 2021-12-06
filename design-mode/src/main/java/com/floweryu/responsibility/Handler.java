package com.floweryu.responsibility;

/**
 * @author Floweryu
 * @date 2021/11/28 21:26
 */
public abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }
    
    public abstract void handleRequest(int request);
}
    