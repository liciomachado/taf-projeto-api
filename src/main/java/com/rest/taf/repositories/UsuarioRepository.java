package com.rest.taf.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.taf.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}
