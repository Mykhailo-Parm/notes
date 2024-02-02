package com.mykhailoparm.Notes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.GeneratorStrategy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
}
