package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//自定义拦截器类，对拦截的请求进行处理
public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws IOException {
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(token);
        if (null == redisTemplate.opsForValue().get("msg") || !token.equals(redisTemplate.opsForValue().get("msg"))){
            System.out.println("token验证失败");
            //由于跨域访问的时候，不能随意获取服务器响应头，所以在服务器编码的时候要加上下面的这行内容，然后再存入数据
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type,Last-Modified,device,token");
            response.setHeader("Access-Control-Expose-Headers","status");
            response.setHeader("status","fail");
            return false;
        }
        System.out.println("token验证成功");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type,Last-Modified,device,token");
        redisTemplate.expire("msg",60, TimeUnit.SECONDS);
        return true;
    }
}
