package com.moto.moto.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand; // Marca comercial
    private String model;
    private Long userId;

}
