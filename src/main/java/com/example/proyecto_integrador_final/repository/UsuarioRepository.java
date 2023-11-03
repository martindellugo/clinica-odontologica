package com.example.proyecto_integrador_final.repository;


import com.example.proyecto_integrador_final.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String correo);
}
