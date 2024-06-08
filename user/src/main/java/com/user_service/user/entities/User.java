package com.user_service.user.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"users\"") //En H2 user es una palabra reservada. por eso toca especificar el nombre de la tabla
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}
