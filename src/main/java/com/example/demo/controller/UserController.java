package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-04-28 17:26:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/register")
    public JSONObject register(@RequestBody Map param){
        String username = (String)param.get("username");
        User oldUser = userService.findByName(username);
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        if (!StringUtils.isEmpty(oldUser)){
            jsonObject.put("message","该用户已被注册，请直接登录!");
        }else {
            String password = (String)param.get("password");
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUsername(username);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            userService.insert(user);
            jsonObject.put("message","注册成功");
        }
        return  jsonObject;
    }

    @PostMapping("/login")
    public JSONObject login(@RequestBody Map param,HttpServletRequest request){
        String username = (String)param.get("username");
        String password = (String)param.get("password");
        JSONObject jsonObject = new JSONObject();
        User oldUser = userService.findByName(username);
        if(StringUtils.isEmpty(oldUser)){
            jsonObject.put("status","please register");
        }
        else if (!StringUtils.isEmpty(oldUser) && DigestUtils.md5DigestAsHex(password.getBytes()).equals(oldUser.getPassword())){
            //登陆成功
            jsonObject.put("status","success");
            String token = TokenUtil.tokenStorage(username);
            redisTemplate.opsForValue().set("msg",token);
            redisTemplate.expire("msg",60, TimeUnit.SECONDS);

            jsonObject.put("token",token);
        }else{
            //登陆失败
            jsonObject.put("status","fail");
        }

        return  jsonObject;
    }

}