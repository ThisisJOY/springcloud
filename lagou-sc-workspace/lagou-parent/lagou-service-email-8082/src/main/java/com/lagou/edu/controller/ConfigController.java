package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类用于模拟，我们要使用共享的那些配置信息做一些事情
 */
@RestController
@RequestMapping("/mailconfig")
@RefreshScope
public class ConfigController {

    // 和取本地配置信息一样
    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private String mailPort;

    // 内存级别的配置信息
    // 数据库，redis配置信息
    @GetMapping("/viewconfig")
    public String viewconfig() {
        return " mailHost=>" + mailHost + " mailPort=>" + mailPort;
    }
}
