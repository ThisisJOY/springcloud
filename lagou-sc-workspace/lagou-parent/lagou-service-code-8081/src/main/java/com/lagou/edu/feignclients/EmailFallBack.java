package com.lagou.edu.feignclients;


import org.springframework.stereotype.Component;

/**
 * 降级回退逻辑需要定义一个类，实现FeignClient接口，实现接口中的方法
 */
@Component  // 别忘了这个注解，还应该被扫描到
public class EmailFallBack implements EmailFeignClient {
    @Override
    public String sendEmail(String email, String code) {
        return "-1";
    }
}
