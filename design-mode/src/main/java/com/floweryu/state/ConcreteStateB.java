package com.floweryu.state;

/**
 * @author zhangjunfeng
 * @date 2023/3/3 17:37
 */
public class ConcreteStateB extends State{
    @Override
    public void handle1() {
        System.out.println("bbbbbbbbbbb");
    }  //本状态下必须要处理的事情，...
    
    @Override
    public void handle2() {
        super.context.setCurrentState(Context.contreteStateA);  //切换到状态A
        super.context.handle1();  //执行状态A的任务
    }
}
