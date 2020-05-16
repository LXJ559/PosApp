package com.example.demo.util;

import java.util.Base64;
import java.util.UUID;


public class TokenUtil {

    public static String tokenStorage(String username){
        String msg = Base64.getEncoder().encodeToString(username.getBytes());
        //生成token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return msg + "_" + token;
    }

    public static String tokenDepart(String token){
        String token1 = token.substring(0, token.indexOf("_"));
        byte[] token_username = Base64.getDecoder().decode(token1);
        String username = new String(token_username);
        return username;
    }
}
