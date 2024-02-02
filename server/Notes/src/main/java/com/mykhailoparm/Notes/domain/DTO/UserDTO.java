package com.mykhailoparm.Notes.domain.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
}
