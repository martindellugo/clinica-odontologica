package com.example.proyecto_integrador_final.service;


import com.example.proyecto_integrador_final.entity.Odontologo;

import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.repository.OdontologoRepository;
import org.junit.Assert;
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
public class OdontologoServiceTest {

        @Autowired
        private OdontologoService odontologoService;
        @Autowired
        private OdontologoRepository odontologoRepository;

        @Test
        public void testCrearOdontologo (){

            Odontologo odontologo= new Odontologo("2002","Juan","Perez");
            Odontologo odontologo1= new Odontologo("1001","martin","Fernanadez");


            odontologoRepository.save(odontologo);
            odontologoRepository.save(odontologo1);

            Assertions.assertTrue(odontologo != null);

        }
        @Test
        public void testEliminarOdontologo() throws ResourceNotFoundExeption {

            Odontologo odontologo= new Odontologo("2002","Juan","Perez");
            Odontologo odontologo1= new Odontologo("1001","martin","Fernanadez");


            odontologoRepository.save(odontologo);
            odontologoRepository.deleteById(1L);

            Assertions.assertTrue(odontologo != null);
        }

    @Test
    public void testActualizarOdontologo()  {

        Odontologo odontologo= new Odontologo("2002","Juan","Perez");
        Odontologo odontologo1= new Odontologo("1001","martin","Fernanadez");

        odontologoRepository.save(odontologo);
        odontologo.setMatricula("2002");
        odontologoRepository.save(odontologo);
        String respEsp= "2002";
        String matricula= odontologo.getMatricula();

        Assertions.assertEquals(respEsp,matricula);

    }

    @Test
    public void testBuscarOdontologo()  {

        Odontologo odontologo= new Odontologo("2002","Juan","Perez");
        Odontologo odontologo1= new Odontologo("1001","martin","Fernanadez");

        odontologoRepository.save(odontologo);
        odontologoRepository.save(odontologo1);

        odontologoRepository.findById(1L);
        String nombreOdontologo= odontologo.getNombre();
        String nombreOdontologo2= odontologo1.getNombre();

        Assertions.assertTrue(nombreOdontologo != nombreOdontologo2);
    }

    @Test
    public void buscarOdontologos() {

        Odontologo odontologo= new Odontologo("2002","Juan","Perez");
        Odontologo odontologo1= new Odontologo("1001","martin","Fernanadez");


        odontologoRepository.save(odontologo);
        odontologoRepository.save(odontologo1);

        List<Odontologo> odontologos = odontologoRepository.findAll();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() == 2);
        System.out.println(odontologos);
    }

    }

