package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserDetailDTO;
import com.example.demo.dtos.UserListDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserListDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDetailDTO findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public UserDetailDTO create(@RequestBody UserDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserDetailDTO update(
        @PathVariable Long id,
        @RequestBody UserDTO dto
    ) {
        return service.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}