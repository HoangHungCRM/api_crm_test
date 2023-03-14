package com.resdii.crm.user.domain;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "acl_roles")
@Entity
public class Role {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

//    public Role(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public Role(String name) {
//        super();
//        this.name = name;
//    }
//    public Role() { // TODO Auto-generated constructor stub }
//    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "acl_roles_actions", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id"))
    private Collection<ActionRole> actions;

    public Collection<ActionRole> getActions() {
        return actions;
    }


    public void setActions(Collection<ActionRole> actions) {
        this.actions = actions;
    }

    public Role() {
    }

    public Role(String name, Collection<ActionRole> actions) {
        this.name = name;
        this.actions = actions;
    }
}
