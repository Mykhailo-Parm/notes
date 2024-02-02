package com.mykhailoparm.Notes.repositories;

import com.mykhailoparm.Notes.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
