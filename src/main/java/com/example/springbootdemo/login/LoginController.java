package com.example.springbootdemo.login;

import com.example.springbootdemo.util.ConfigHelper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LoginController {
    @Autowired
    private ConfigHelper configHelper;
    @Autowired
    private HttpServletRequest request;
    AtomicInteger i = new AtomicInteger(0);
    @GetMapping("/hello/aaa/bbb")
    public String hello(){
        request.getRemoteAddr();
        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }
        System.out.println(i.incrementAndGet());
        return request.getRemoteAddr();
    }


    @PostMapping("/quit")
    public String quit(){
        try {
            ServletInputStream inputStream = request.getInputStream();
            System.out.println( IOUtils.toString(inputStream));
        }catch (Exception e){

        }


        return "quit";
    }
}
