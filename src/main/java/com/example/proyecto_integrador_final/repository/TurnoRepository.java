package com.example.proyecto_integrador_final.repository;

import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {

    //Optional<Turno> findByEmail(String email);
}
