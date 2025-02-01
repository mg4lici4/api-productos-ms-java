package com.mgalician.productos.services;

import com.mgalician.productos.models.entities.UsuarioEntity;
import com.mgalician.productos.repositories.UsuarioRepository;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return a Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                usuarioEntity.getUsername(),
                usuarioEntity.getPassword(), // Ensure this is already encoded with BCrypt
                new ArrayList<>() // No authorities/roles for now
        );
    }
}