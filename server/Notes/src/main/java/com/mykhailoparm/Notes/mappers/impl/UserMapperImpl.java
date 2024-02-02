package com.mykhailoparm.Notes.mappers.impl;

import com.mykhailoparm.Notes.domain.DTO.UserDTO;
import com.mykhailoparm.Notes.domain.entities.UserEntity;
import com.mykhailoparm.Notes.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDTO> {
    private ModelMapper modelMapper;
    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDTO mapToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    @Override
    public UserEntity mapFromDTO(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
