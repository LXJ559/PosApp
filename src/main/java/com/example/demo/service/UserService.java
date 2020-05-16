package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-04-28 17:26:07
 */
public interface UserService {


   User findByName(String username);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);


}