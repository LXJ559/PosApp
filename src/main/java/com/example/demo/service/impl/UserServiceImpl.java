package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-04-28 17:26:08
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;


    @Override
    public User findByName(String username) {

        return userRepository.findByName(username);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userRepository.save(user);
        return user;
    }



}