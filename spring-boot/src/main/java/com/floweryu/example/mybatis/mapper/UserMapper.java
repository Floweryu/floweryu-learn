package com.floweryu.example.mybatis.mapper;

import com.floweryu.example.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/12/30 23:23
 */
@Repository
@Mapper
public interface UserMapper {
    
    int insert(User user);
    
    List<User> list();
}
