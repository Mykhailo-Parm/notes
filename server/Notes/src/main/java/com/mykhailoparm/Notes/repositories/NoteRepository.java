package com.mykhailoparm.Notes.repositories;

import com.mykhailoparm.Notes.domain.entities.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Long> {
}
