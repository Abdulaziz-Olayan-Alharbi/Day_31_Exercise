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
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int productId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int merchantId;
    @NotNull
    @Min(11)
    @Column(columnDefinition = "int not null")
    private int stock;
}
