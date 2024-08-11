package com.example.capstone_1.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int userId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int productId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int merchantId;
    @NotEmpty
    @Pattern(regexp = "^(Received|Shipping|Shipped |Cancelled)$")
    @Column(columnDefinition = "int not null")
    private String status = "Received";
    @Column(columnDefinition = "double not null")
    private double price;

}
