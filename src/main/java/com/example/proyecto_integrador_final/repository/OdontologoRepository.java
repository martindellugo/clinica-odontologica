package com.example.proyecto_integrador_final.repository;

import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {

    //Optional<Odontologo> findByEmail(String email);
}
