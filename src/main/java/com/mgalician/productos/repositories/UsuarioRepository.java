package com.mgalician.productos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgalician.productos.models.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}