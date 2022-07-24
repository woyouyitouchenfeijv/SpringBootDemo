package com.example.springbootdemo.login;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface LoginService {

    //获取本地用户
    public String getLocalLogin(HttpServletRequest request);
    //登录本地
    public void localLogin(String id, HttpServletResponse response);
}
