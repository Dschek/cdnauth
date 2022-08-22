package com.cdn.auth.auth.db.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Embeddable
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name="password")
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Token> token;
}
