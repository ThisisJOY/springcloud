package com.lagou.edu.dao;

import com.lagou.edu.pojo.LagouCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeDao extends JpaRepository<LagouCode, Integer> {
    List<LagouCode> findByEmailOrderByCreateTimeDesc(String email);
}
