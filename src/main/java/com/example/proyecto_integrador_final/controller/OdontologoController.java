package com.example.proyecto_integrador_final.controller;

import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {



    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent())
            return ResponseEntity.ok(odontologoBuscado.get());

        else
            return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){

        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundExeption {
        odontologoService.eliminarOdontologo(id);

        return ResponseEntity.ok("Se elimino al odontologo correctamente con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        //es preguntar si existe
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(odontologo.getId());

        if(odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);

            return ResponseEntity.ok("Odontologo actualizado" + " -" + odontologo.getNombre());
        }
        else{
            return  ResponseEntity.badRequest().body("no se pudo actualizar el odontologo "+odontologo.getId()+" -"+odontologo.getNombre());
        }

    }


}

 /*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundExeption {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            //que si existe ese paciente con ese id
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("se elimino correctamente el paciente con el id"+id);
        }
        else{
            throw new ResourceNotFoundExeption("No se encontró el odontologo a eliminar"+id);
            //no existe para eliminar
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el paciente a eliminar"+id);
        }
    }
   */
