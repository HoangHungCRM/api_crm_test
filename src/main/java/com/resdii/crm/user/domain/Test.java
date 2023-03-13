package com.resdii.crm.user.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "tests")
public class Test extends BaseEntity{
    private @Column(name = "value") String value;
}
