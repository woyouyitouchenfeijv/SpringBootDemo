package com.example.springbootdemo.login;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServiceImpl implements LoginService {
    @Override
    public String getLocalLogin(HttpServletRequest request) {
        String result = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("loginDemo".equals(cookie.getName())){
                result = cookie.getValue();
            }
        }
        return result;
    }

    @Override
    public void localLogin(String id, HttpServletResponse response) {
        //CookieUtil.SetCookies("loginDemo",id,0,response);
    }


}
