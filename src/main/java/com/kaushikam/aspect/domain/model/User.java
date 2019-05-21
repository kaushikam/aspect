package com.kaushikam.aspect.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
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
}
