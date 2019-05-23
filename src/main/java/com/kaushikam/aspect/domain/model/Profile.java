package com.kaushikam.aspect.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    private Long id;

    private String name;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Profile(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
