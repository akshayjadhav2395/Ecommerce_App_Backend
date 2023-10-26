package com.myEcom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{5,29}$", message = "Invalid username")
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String about;
    private String gender;
    private String address;
    private Date createAt;
    private String phoneNumber;
    private boolean active;
}
