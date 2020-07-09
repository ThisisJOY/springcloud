package com.lagou.edu.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "lagou_auth_code")
public class LagouCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String code;

    @Column(name = "createtime")
    private ZonedDateTime createTime;

    @Column(name = "expiretime")
    private ZonedDateTime expireTime;
}
