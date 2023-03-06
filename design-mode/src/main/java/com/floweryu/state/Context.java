package com.floweryu.state;

/**
 * @author zhangjunfeng
 * @date 2023/3/3 17:36
 */
public class Context {
    public final static ConcreteStateA contreteStateA = new ConcreteStateA();
    public final static ConcreteStateB contreteStateB = new ConcreteStateB();
    
    private State CurrentState;
    
    public State getCurrentState() {
        return CurrentState;
    }
    
    public void setCurrentState(State currentState) {
        this.CurrentState = currentState;
        this.CurrentState.setContext(this);
    }
    
    public void handle1() {
        this.CurrentState.handle1();
    }
    
    public void handle2() {
        this.CurrentState.handle2();
    }
}
