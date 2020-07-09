package com.lagou.edu.feignclients;

import org.springframework.stereotype.Component;

@Component
public class CodeFallBack implements CodeFeignClient {

    @Override
    public String validateCode(String email, String code) {
        return "1";
    }
}
