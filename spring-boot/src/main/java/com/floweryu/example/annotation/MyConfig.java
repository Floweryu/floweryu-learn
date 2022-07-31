package com.floweryu.example.annotation;

import com.floweryu.example.annotation.bean.Car;
import com.floweryu.example.annotation.bean.Pet;
import com.floweryu.example.annotation.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Floweryu
 * @date 2022/1/3 19:29
 * 这是一个配置类
 */
@Configuration
@EnableConfigurationProperties(Car.class)
@ConditionalOnMissingBean(name = "Tom")
@ConditionalOnProperty(prefix = "study", name = "enabled", havingValue = "true")
@Import({User.class})
public class MyConfig {

    @Bean("Tom")
    public Pet petBean() {
        return new Pet("cat", 18);
    }

    /**
     * 向容器中添加组件，以方法名作为组件名
     */
    @Bean("Dogger")
    public User user1() {
        User user2 = new User("zhanglei", 18);
        // User组件依赖了Pet组件
        user2.setPet(petBean());
        return user2;
    }
}
