package com.lagou.edu.service;

import com.lagou.edu.dao.TokenDao;
import com.lagou.edu.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenDao tokenDao;

    public String getEmail(String token) {
        Token byToken = tokenDao.findByToken(token);
        if (byToken != null) {
            return byToken.getEmail();
        }
        return null;
    }

    public String createToken() {
        return UUID.randomUUID().toString();
    }

    public void saveToken(String email, String token) {
        Token tokenEntity = new Token();
        tokenEntity.setEmail(email);
        tokenEntity.setToken(token);
        tokenDao.save(tokenEntity);
    }
}
