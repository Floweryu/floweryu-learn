package com.floweryu.example.aop;

/**
 * @author Floweryu
 * @date 2022/11/23 16:35
 */
public class UserServiceImpl implements UserService{
    
    @Override
    public void add() {
        System.out.println("----------------add--------------");
    }
}
