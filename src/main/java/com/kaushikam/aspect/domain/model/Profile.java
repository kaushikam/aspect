package com.kaushikam.aspect.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    private Long id;

    private String name;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
