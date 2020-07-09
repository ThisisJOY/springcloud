package com.lagou.edu.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-service-email", fallback = EmailFallBack.class, path = "/email")
public interface EmailFeignClient {

    // Feign要做的事情就是，拼装url发起请求
    // 我们调用该方法就是调用本地接口方法，那么实际上做的是远程请求
    @GetMapping("/{email}/{code}")
    public String sendEmail(@PathVariable("email") String email, @PathVariable("code") String code);

}
