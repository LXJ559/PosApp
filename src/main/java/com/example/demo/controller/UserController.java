package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.job.SendMailJob;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.*;
import java.util.*;
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

    @Resource
    private SendMailJob sendMailJob;

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
            String email = (String)param.get("email");
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUsername(username);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setEmail(email);
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

    @PostMapping("/getVCode")
    public JSONObject getVCode(@RequestBody Map param){
        String username = (String) param.get("username");
        User oldUser = userService.findByName(username);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(oldUser)) {
            jsonObject.put("message", "该用户还没有注册");
        }
        String email = (String) param.get("email");
        if (!email.equals(oldUser.getEmail())){
            jsonObject.put("error","该邮箱与注册邮箱不一致");
        }else {
            int vCode = (int) (Math.random()*900000+100000);
            redisTemplate.opsForValue().set("username",username);
            redisTemplate.opsForValue().set("VCode",String.valueOf(vCode));
            redisTemplate.expire("VCode",60*5,TimeUnit.SECONDS);
            sendMailJob.sendVCode(vCode,email);
            jsonObject.put("success","success");
        }
        return jsonObject;
    }

    @PostMapping("/checkVCode")
    public JSONObject checkVCode(@RequestBody Map param){
        String verificationCode = (String) param.get("VerificationCode");
        String username = (String) param.get("username");
        JSONObject jsonObject = new JSONObject();
        if ("".equals(redisTemplate.opsForValue().get("VCode"))||null==redisTemplate.opsForValue().get("VCode")){
            redisTemplate.expire("username",0,TimeUnit.SECONDS);
            jsonObject.put("message","验证码已失效");
        }else if (!verificationCode.equals(redisTemplate.opsForValue().get("VCode"))){
            jsonObject.put("message","验证码错误，请重新输入");
        }else if(!username.equals(redisTemplate.opsForValue().get("username"))){
            jsonObject.put("message","用户名错误，请重新输入");
        } else {
            redisTemplate.expire("VCode",0,TimeUnit.SECONDS);
            jsonObject.put("success","success");
        }
        return jsonObject;
    }

    @PostMapping("/reset")
    public JSONObject reset(@RequestBody Map param){
        String password = (String) param.get("password");
        User oldUser = userService.findByName((String) redisTemplate.opsForValue().get("username"));
        JSONObject jsonObject = new JSONObject();
        if (!StringUtils.isEmpty(oldUser)){
            oldUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            userService.insert(oldUser);
            redisTemplate.expire("username",0,TimeUnit.SECONDS);
            jsonObject.put("success","success");
        }else {
            jsonObject.put("fail","fail");
        }
        return jsonObject;
    }

}