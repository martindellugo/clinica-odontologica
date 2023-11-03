package com.example.proyecto_integrador_final.service;

import com.example.proyecto_integrador_final.entity.Domicilio;
import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.repository.PacienteRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(JUnit4.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void testCrearPaciente (){
        LocalDate fechaDeIngreso = LocalDate.of(2023, 5, 5);
        Domicilio domicilio= new Domicilio("ecuador",25,"cordoba","cordoba");
        Paciente paciente= new Paciente("Martin","dellugo","10101",fechaDeIngreso,domicilio,"martin@gmail.com");


        pacienteRepository.save(paciente);

        Assertions.assertTrue( paciente != null);
    }

    @Test
    public void testEliminarPaciente() throws ResourceNotFoundExeption {

        LocalDate fechaDeIngreso = LocalDate.of(2023, 5, 5);
        Domicilio domicilio= new Domicilio("ecuador",25,"cordoba","cordoba");
        Paciente paciente= new Paciente("Martin","dellugo","10101",fechaDeIngreso,domicilio,"martin@gmail.com");



        pacienteRepository.save(paciente);
        pacienteRepository.deleteById(1L);

        Assertions.assertTrue(paciente != null);
    }

    @Test
    public void testActualizarPaciente()  {

        LocalDate fechaDeIngreso = LocalDate.of(2023, 5, 5);
        Domicilio domicilio= new Domicilio("ecuador",25,"cordoba","cordoba");
        Paciente paciente= new Paciente("Martin","dellugo","10101",fechaDeIngreso,domicilio,"martin@gmail.com");

        pacienteRepository.save(paciente);
        paciente.setApellido("Lopez");
        pacienteRepository.save(paciente);

        String respEsp= "Lopez";
        String apellidoActualizado= paciente.getApellido();
        Assertions.assertEquals(respEsp,apellidoActualizado);
    }

    @Test
    public void testBuscarPaciente()  {

        LocalDate fechaDeIngreso = LocalDate.of(2023, 5, 5);
        Domicilio domicilio= new Domicilio("ecuador",25,"cordoba","cordoba");
        Paciente paciente= new Paciente("Martin","dellugo","10101",fechaDeIngreso,domicilio,"martin@gmail.com");

        LocalDate fechaDeIngreso2 = LocalDate.of(2023, 4, 4);
        Domicilio domicilio2= new Domicilio("balcarce",25,"cordoba","cordoba");
        Paciente paciente2= new Paciente("Eugenio","mendez","20253",fechaDeIngreso2,domicilio2,"eugenio@gmail.com");

        pacienteRepository.save(paciente);
        pacienteRepository.save(paciente2);
        pacienteRepository.findById(1L);

        String nombrePaciente= paciente.getNombre();
        String nombrePacienteo2= paciente2.getNombre();

        Assertions.assertTrue(nombrePaciente != nombrePacienteo2);
    }

    @Test
    public void buscarPacientes() {

        LocalDate fechaDeIngreso = LocalDate.of(2023, 5, 5);
        Domicilio domicilio= new Domicilio("ecuador",25,"cordoba","cordoba");
        Paciente paciente= new Paciente("Martin","dellugo","10101",fechaDeIngreso,domicilio,"martin@gmail.com");

        LocalDate fechaDeIngreso2 = LocalDate.of(2023, 1, 10);
        Domicilio domicilio2= new Domicilio("ecuador",25,"tucuman","tucuman");
        Paciente paciente2= new Paciente("Martin","dellugo","10101",fechaDeIngreso2,domicilio2,"martu@gmail.com");

        pacienteRepository.save(paciente);
        pacienteRepository.save(paciente2);

        List<Paciente> pacientes = pacienteRepository.findAll();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }
}