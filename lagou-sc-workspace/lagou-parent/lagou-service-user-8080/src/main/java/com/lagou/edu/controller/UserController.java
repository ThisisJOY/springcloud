package com.lagou.edu.controller;

import com.lagou.edu.feignclients.CodeFeignClient;
import com.lagou.edu.pojo.User;
import com.lagou.edu.service.TokenService;
import com.lagou.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CodeFeignClient codeFeignClient;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("register/{email}/{password}/{code}")
    public String register(@PathVariable("email") String email, @PathVariable("password") String password,
            @PathVariable("code") String code, HttpServletResponse httpServletResponse) {

        if (isRegistered(email)) {
            return "-1"; // 已经注册过
        }

        // 校验验证码
        String validateCode = codeFeignClient.validateCode(email, code);
        if ("1".equals(validateCode) || "2".equals(validateCode)) {
            return validateCode;
        }

        // 保存用户名和密码
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.saveUser(user);
        // 注册成功，颁发token令牌，存入数据库并写入cookie，重定向到欢迎页
        String token = tokenService.createToken();
        tokenService.saveToken(email, token);
        Cookie tokenCookie = new Cookie("login_token", token);
        tokenCookie.setPath("/");
        httpServletResponse.addCookie(tokenCookie);

        Cookie emailCookie = new Cookie("login_email", email);
        emailCookie.setPath("/");
        httpServletResponse.addCookie(emailCookie);
        return "0";
    }

    @GetMapping("/login/{email}/{password}")
    public Boolean login(@PathVariable("email") String email, @PathVariable("password") String password,
            HttpServletResponse httpServletResponse) {

        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            //登录成功，颁发token令牌，存入数据库并写入cookie，重定向到欢迎页
            String token = tokenService.createToken();
            tokenService.saveToken(email, token);
            Cookie tokenCookie = new Cookie("login_token", token);
            tokenCookie.setPath("/");
            httpServletResponse.addCookie(tokenCookie);

            Cookie emailCookie = new Cookie("login_email", email);
            emailCookie.setPath("/");
            httpServletResponse.addCookie(emailCookie);
            return true;
        }
        return false;
    }

    @GetMapping("/isRegistered/{email}")
    public Boolean isRegistered(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);
        return user != null;
    }

    @GetMapping("/info/{token}")
    public String getEmail(@PathVariable("token") String token) {
        return tokenService.getEmail(token);
    }

}
