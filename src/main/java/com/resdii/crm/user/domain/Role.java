package com.resdii.crm.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "acl_roles")
@Entity
public class Role {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(String name) {
        super();
        this.name = name;
    }
    public Role() { // TODO Auto-generated constructor stub }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
