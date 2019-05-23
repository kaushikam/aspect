package com.kaushikam.aspect.domain.model;


import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = CascadeType.ALL, optional = false)
    private Profile profile;

    public String getName() {
        return this.profile.getName();
    }

    public User() { }

    public User(Profile profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
