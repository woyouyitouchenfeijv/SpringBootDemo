package com.example.springbootdemo.filter;

import com.example.springbootdemo.login.LoginService;
import com.example.springbootdemo.util.ConfigHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录过滤器
 */
@WebFilter(urlPatterns = "/hello", filterName = "LogInFilter")
public class LogInFilter implements Filter {
    @Autowired
    private ConfigHelper configHelper;
    @Autowired
    private LoginService loginService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String loginProof = null;
        //判断本地是否登录
        String localLogin = loginService.getLocalLogin(request);
        if(localLogin == null || localLogin.length() == 0){
            //已登录

        }else{/*
            //判断单点是否打开
            if(configHelper.isOpen()){
                //跳转到单点，并带上toUrl和ticket
                SsoUser ssoUser = CssSsoCore.doCore(request, response, ssoHelper);
                if(ssoUser != null){
                    //本地登录
                    loginService.localLogin(ssoHelper.getLocalUserBySsoUser(ssoUser),response);
                }
            }else{
                //跳自己的登录方法
                loginService.localLogin("userUUid",response);
            }*/
        }
        //未登录
        if(loginProof == null){
            try {
                returnJson(response,"未登录");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    @Override
    public void destroy() {

    }
}
