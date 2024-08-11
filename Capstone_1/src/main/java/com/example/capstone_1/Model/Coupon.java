package com.example.capstone_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Min(5)
    @Column(columnDefinition = "double not null")
    private double percent;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int productId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int merchantId;
}
