package com.compasso.demo_park_api.web.controller;

import com.compasso.demo_park_api.entity.User;
import com.compasso.demo_park_api.service.UserService;
import com.compasso.demo_park_api.web.dto.UserCreateDTO;
import com.compasso.demo_park_api.web.dto.UserPasswordDto;
import com.compasso.demo_park_api.web.dto.UserResponseDto;
import com.compasso.demo_park_api.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDTO createDTO){
        User user1 =  userService.save(UserMapper.toUser(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        User user1 =  userService.searchById(id);
        return ResponseEntity.ok(UserMapper.toDto(user1));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto dto){
        User user1 =  userService.editPassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity <List<UserResponseDto>> getAll(){
        List<User> user1 =  userService.searchAll();
        return ResponseEntity.ok(UserMapper.toListDto(user1));
    }
}
