package com.pragmatic.blogs.services;

import com.pragmatic.blogs.dtos.UserDTO;
import com.pragmatic.blogs.entities.Role;
import com.pragmatic.blogs.entities.User;
import com.pragmatic.blogs.repositories.RoleRepository;
import com.pragmatic.blogs.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // later: encode with Spring Security

        // Assign default role
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(List.of(roleUser));

        User saved = userRepository.save(user);

        return mapToDTO(saved);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream().map(Role::getName).toList());
        return dto;
    }
}

