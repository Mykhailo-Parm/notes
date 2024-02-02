package com.mykhailoparm.Notes.mappers.impl;

import com.mykhailoparm.Notes.domain.DTO.NoteDTO;
import com.mykhailoparm.Notes.domain.entities.NoteEntity;
import com.mykhailoparm.Notes.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NoteMapperImpl implements Mapper<NoteEntity, NoteDTO> {
    private ModelMapper modelMapper;
    public NoteMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public NoteDTO mapToDTO(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDTO.class);
    }

    @Override
    public NoteEntity mapFromDTO(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, NoteEntity.class);
    }
}
