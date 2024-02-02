package com.mykhailoparm.Notes.services;

import com.mykhailoparm.Notes.domain.entities.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    public NoteEntity save(NoteEntity noteEntity);
    List<NoteEntity> findAll();
    Optional<NoteEntity> findOne(Long id);
    boolean isExists(Long id);
    NoteEntity partialUpdate(Long id, NoteEntity noteEntity);
    void delete(Long id);
}
