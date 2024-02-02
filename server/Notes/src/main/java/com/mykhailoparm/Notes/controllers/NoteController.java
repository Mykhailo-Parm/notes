package com.mykhailoparm.Notes.controllers;

import com.mykhailoparm.Notes.domain.DTO.NoteDTO;
import com.mykhailoparm.Notes.domain.entities.NoteEntity;
import com.mykhailoparm.Notes.domain.entities.UserEntity;
import com.mykhailoparm.Notes.mappers.Mapper;
import com.mykhailoparm.Notes.services.NoteService;
import com.mykhailoparm.Notes.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    private NoteService noteService;
    private UserService userService;
    private Mapper<NoteEntity, NoteDTO> noteMapper;
    public NoteController(NoteService noteService, UserService userService, Mapper<NoteEntity, NoteDTO> noteMapper) {
        this.noteService = noteService;
        this.userService = userService;
        this.noteMapper = noteMapper;
    }
    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService
                .findAll()
                .stream()
                .map(noteMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable("id") Long id) {
        return noteService
                .findOne(id)
                .map(noteEntity -> new ResponseEntity<>(
                        noteMapper.mapToDTO(noteEntity),
                        HttpStatus.OK
                )).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO) {
        UserEntity existingUser = userService.findOne(noteDTO.getUser().getId()).orElse(null);
        if (Objects.isNull(existingUser)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        NoteEntity noteEntity = noteMapper.mapFromDTO(noteDTO);
        noteEntity.setUser(existingUser);
        NoteEntity savedNoteEntity = noteService.save(noteEntity);
        return new ResponseEntity<>(noteMapper.mapToDTO(savedNoteEntity), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<NoteDTO> fullUpdateNote(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO) {
        UserEntity existingUser = userService.findOne(noteDTO.getUser().getId()).orElse(null);
        if (Objects.isNull(existingUser)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!noteService.isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        noteDTO.setId(id);
        NoteEntity noteEntity = noteMapper.mapFromDTO(noteDTO);
        noteEntity.setUser(existingUser);
        NoteEntity savedNoteEntity = noteService.save(noteEntity);
        return new ResponseEntity<>(
                noteMapper.mapToDTO(savedNoteEntity),
                HttpStatus.OK
        );
    }
    @PatchMapping("{id}")
    public ResponseEntity<NoteDTO> partialUpdateNote(@PathVariable("id") Long id, @RequestBody NoteDTO noteDTO) {
        if (!noteService.isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        noteDTO.setId(id);
        NoteEntity noteEntity = noteMapper.mapFromDTO(noteDTO);
        NoteEntity updatedNoteEntity = noteService.partialUpdate(id, noteEntity);
        return new ResponseEntity<>(noteMapper.mapToDTO(updatedNoteEntity),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
