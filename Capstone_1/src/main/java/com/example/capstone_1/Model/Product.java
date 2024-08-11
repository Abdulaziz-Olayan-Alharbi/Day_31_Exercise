package com.example.capstone_1.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 4, max = 40)
    @Column(columnDefinition = "varchar(40) not null")
    private String name;
    @NotNull
    @Column(columnDefinition = "int not null")
    private double price;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int categoryId;
}
