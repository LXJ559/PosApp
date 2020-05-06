package com.example.demo.util;

import java.util.Base64;
import java.util.UUID;


public class TokenUtil {

    public String tokenStorage(String username){
        String msg = Base64.getEncoder().encodeToString(username.getBytes());
        //生成token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return msg + "_" + token;
    }

}
