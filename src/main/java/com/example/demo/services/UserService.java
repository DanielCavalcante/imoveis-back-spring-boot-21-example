package com.example.demo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.UserDetailDTO;
import com.example.demo.dtos.UserListDTO;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserListDTO> findAll() {
        return repository.findAll().stream().map(user -> new UserListDTO(user.getId(), user.getFullname(), user.getEmail(), user.getPhone())).toList();
    }

    public UserDetailDTO findOne(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return new UserDetailDTO(user.getId(), user.getFullname(), user.getEmail(), user.getPhone(), user.getPhoto());
    }

    public UserDetailDTO create(UserDTO dto) {
        var entity = new User();
        entity.setFullname(dto.fullname());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setPhoto(dto.photo());
        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setConfirmPassword(passwordEncoder.encode(dto.confirmPassword()));

        var saved = repository.save(entity);

        return new UserDetailDTO(
            saved.getId(),
            saved.getFullname(),
            saved.getPhone(),
            saved.getEmail(),
            saved.getPhoto()
        );
    }

    public UserDetailDTO edit(Long id, UserDTO dto) {
        var entity = new User();
        entity.setFullname(dto.fullname());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setPhoto(dto.photo());
        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setConfirmPassword(passwordEncoder.encode(dto.confirmPassword()));

        var saved = repository.save(entity);

        return new UserDetailDTO(
            saved.getId(),
            saved.getFullname(),
            saved.getPhone(),
            saved.getEmail(),
            saved.getPhoto()
        );
    }

    public void delete(Long id) {
        
    }

}
