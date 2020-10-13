package com.example.fined187.JPA_SHOP.domain.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity extends com.example.jpashop.domain.entity.BaseDataEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createUser;

    @LastModifiedBy
    private String updateUser;

}
