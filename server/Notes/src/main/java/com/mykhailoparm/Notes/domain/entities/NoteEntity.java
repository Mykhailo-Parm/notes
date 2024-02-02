package com.mykhailoparm.Notes.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String title;
    private String content;

    @Column(name = "date_created")
    private LocalDate dateOfCreation;

    private String status;
}
