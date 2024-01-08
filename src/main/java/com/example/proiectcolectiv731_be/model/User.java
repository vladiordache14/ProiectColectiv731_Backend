package com.example.proiectcolectiv731_be.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {


    private enum ERole {
        BUYER,
        SELLER,
        ADMIN
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Username must not be empty!")
    @Size(max = 30, message = "Username must not exceed 30 characters!")
    private String username;


    @Column(name = "password")
    @NotEmpty(message = "Password must not be empty!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
            message = "Password must contain at least one letter, one number, and one special character!")
    @Size(max = 30, message = "Password must not exceed 30 characters!")

    private String password;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Email must not be empty!")
    @Email(message = "Invalid email format!")
    private String email;


    @Column(name = "role")

    @NotEmpty(message = "Role must not be empty!")
    private ERole role;

    @Column(name = "address")

    @Size(max = 100, message = "Address must be below 100 characters!")
    private String address;

    @Column(name = "phone")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")

    private String phoneNumber;

    @Column(name = "lockedUntil")
    private Date lockedUntil;

    @Column(name = "tries")

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Advert> advertSet;

    @Column(name = "tries")
    private int numberOfTries;

    public void setPassword(String newPass){
        this.password=newPass;
    }


    public void createNewUserFromDto(UserDto userDto) {
        this.setUsername(userDto.getUsername());
        this.setPassword(userDto.getPassword());
        this.setEmail(userDto.getEmail());

        this.setRole(ERole.valueOf(userDto.getRole()));
        this.setAddress(userDto.getAddress());
        this.setPhoneNumber(userDto.getPhoneNumber());
    }
}

