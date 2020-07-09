package com.lagou.edu.dao;

import com.lagou.edu.pojo.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDao extends JpaRepository<Token, Integer> {
    Token findByToken(String token);
}
