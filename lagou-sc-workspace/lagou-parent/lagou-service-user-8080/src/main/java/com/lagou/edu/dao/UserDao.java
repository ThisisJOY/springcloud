package com.lagou.edu.dao;

import com.lagou.edu.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
