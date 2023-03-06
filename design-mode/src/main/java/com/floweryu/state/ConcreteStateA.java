package com.floweryu.state;

/**
 * @author zhangjunfeng
 * @date 2023/3/3 17:36
 */
public class ConcreteStateA extends State{
    @Override
    public void handle1() {
        System.out.println("aaaaaaaaaaaaaaa");
    }  //本状态下必须要处理的事情

    @Override
    public void handle2() {
        super.context.setCurrentState(Context.contreteStateB);  //切换到状态B        
        super.context.handle1();  //执行状态B的任务
    }
}
