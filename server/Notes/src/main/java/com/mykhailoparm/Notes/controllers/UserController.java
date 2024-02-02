package com.mykhailoparm.Notes.controllers;

import com.mykhailoparm.Notes.domain.DTO.UserDTO;
import com.mykhailoparm.Notes.domain.entities.UserEntity;
import com.mykhailoparm.Notes.mappers.Mapper;
import com.mykhailoparm.Notes.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;
    private Mapper<UserEntity, UserDTO> userMapper;
    public UserController(UserService userService, Mapper<UserEntity, UserDTO> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService
                .findAll()
                .stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return userService
                .findOne(id)
                .map(userEntity -> new ResponseEntity<>(
                        userMapper.mapToDTO(userEntity),
                        HttpStatus.OK
                ))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userMapper.mapFromDTO(userDTO);
        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapToDTO(savedUserEntity), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> fullUpdateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        if (!userService.isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userDTO.setId(id);
        UserEntity userEntity = userMapper.mapFromDTO(userDTO);
        UserEntity savedUserEntity = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapToDTO(savedUserEntity), HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        if (!userService.isExists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userDTO.setId(id);
        UserEntity userEntity = userMapper.mapFromDTO(userDTO);
        UserEntity updatedEntity = userService.partialUpdate(id, userEntity);
        return new ResponseEntity<>(userMapper.mapToDTO(updatedEntity), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
