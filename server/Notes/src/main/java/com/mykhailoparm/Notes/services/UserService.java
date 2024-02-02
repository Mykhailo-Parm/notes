package com.mykhailoparm.Notes.services;

import com.mykhailoparm.Notes.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserEntity save(UserEntity userEntity);
    List<UserEntity> findAll();
    Optional<UserEntity> findOne(Long id);
    boolean isExists(Long id);
    UserEntity partialUpdate(Long id, UserEntity userEntity);
    void delete(Long id);
}
