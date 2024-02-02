package com.mykhailoparm.Notes.domain.DTO;

import com.mykhailoparm.Notes.domain.entities.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDTO {
    private Long id;
    private UserEntity user;
    private String title;
    private String content;
    private LocalDate dateOfCreation;
    private String status;
}
