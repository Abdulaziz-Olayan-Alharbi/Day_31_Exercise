package com.example.capstone_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 6, max = 25)
    @Column(columnDefinition = "varchar(25) not null")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{9,}$" , message = "password must be at least 9 characters long, at least one letter, at least one digit" )
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @Email
    @Column(columnDefinition = "varchar(20) not null")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(Admin|Customer)$")
    @Column(columnDefinition = "varchar(20) not null")
    private String role;
    @NotNull
    @Positive
    @Column(columnDefinition = "varchar(20) not null")
    private double balance;
}
