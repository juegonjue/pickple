package com.se.pickple_api_server.v1.common.domain.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Component
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AccountGenereteEntity extends BaseEntity {

    @CreatedBy
    private Long createdAccountBy;

    @LastModifiedBy
    private Long lastModifiedAccountBy;
}
