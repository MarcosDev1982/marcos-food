package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByEmail(String email);



}
