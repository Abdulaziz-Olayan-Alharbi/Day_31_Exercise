package com.example.capstone_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 250)
    @Column(columnDefinition = "varchar(20) not null")
    private String text;
    @Min(1)
    @Max(10)
    @Column(columnDefinition = "double not null")
    private double score;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int userId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private int productId;

}
