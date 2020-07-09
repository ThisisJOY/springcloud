package com.lagou.edu.service;

import com.lagou.edu.dao.CodeDao;
import com.lagou.edu.feignclients.EmailFeignClient;
import com.lagou.edu.pojo.LagouCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class CodeService {

    @Autowired
    private EmailFeignClient emailFeignClient;

    @Autowired
    private CodeDao codeDao;

    // 生成验证码入库并发送到对应邮箱，成功true，失败false
    public String createCode(String email) {
        String randomCode = randomCode();
        LagouCode lagouCode = new LagouCode();
        lagouCode.setCode(randomCode);
        lagouCode.setEmail(email);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime after10Mins = now.plus(Duration.of(10, ChronoUnit.MINUTES));
        lagouCode.setCreateTime(now);
        lagouCode.setExpireTime(after10Mins);
        codeDao.save(lagouCode);
        return emailFeignClient.sendEmail(email, randomCode);
    }

    // 校验验证码是否正确，0正确1错误2超时
    public String validateCode(String email, String code) {
        List<LagouCode> lagouCodes = codeDao.findByEmailOrderByCreateTimeDesc(email);
        LagouCode latest = lagouCodes.get(0);
        if (latest != null && latest.getCode() != null && latest.getCode().equals(code)) {
            return ZonedDateTime.now().isBefore(latest.getExpireTime()) ? "0" : "2";
        }
        return "1";
    }

    private static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
