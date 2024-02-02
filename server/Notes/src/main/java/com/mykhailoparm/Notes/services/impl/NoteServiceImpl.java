package com.mykhailoparm.Notes.services.impl;

import com.mykhailoparm.Notes.domain.entities.NoteEntity;
import com.mykhailoparm.Notes.exceptions.NoteNotFoundException;
import com.mykhailoparm.Notes.repositories.NoteRepository;
import com.mykhailoparm.Notes.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    @Override
    public NoteEntity save(NoteEntity noteEntity) {
        return noteRepository.save(noteEntity);
    }

    @Override
    public List<NoteEntity> findAll() {
        return StreamSupport.stream(noteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NoteEntity> findOne(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public NoteEntity partialUpdate(Long id, NoteEntity noteEntity) {
        noteEntity.setId(id);

        return noteRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(noteEntity.getTitle()).ifPresent(existingNote::setTitle);
            Optional.ofNullable(noteEntity.getContent()).ifPresent(existingNote::setContent);
            Optional.ofNullable(noteEntity.getDateOfCreation()).ifPresent(existingNote::setDateOfCreation);
            Optional.ofNullable(noteEntity.getStatus()).ifPresent(existingNote::setStatus);
            Optional.ofNullable(noteEntity.getUser()).ifPresent(existingNote::setUser);
            return existingNote;
        }).orElseThrow(() -> new NoteNotFoundException("Note does not exist!"));
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
