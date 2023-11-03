package com.example.proyecto_integrador_final.controller;

import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




    @RestController
    @RequestMapping("/pacientes")
    public class PacienteController {
        //quien representaria al modelo?
        private PacienteService pacienteService;

        @Autowired
        public PacienteController(PacienteService pacienteService) {
            this.pacienteService = pacienteService;
        }



        @GetMapping("/{id}")
        public ResponseEntity <Paciente> buscarPaciente(@PathVariable Long id){
            Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
            if(pacienteBuscado.isPresent()){
                //que si existe el paciente con ese id
                return ResponseEntity.ok(pacienteBuscado.get());
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping
        public ResponseEntity<List<Paciente>> buscarPacientes(){
            return ResponseEntity.ok(pacienteService.buscarPacientes());
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundExeption{
           // Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(id);
            //if(pacienteBuscado.isPresent()){
                //que si existe ese paciente con ese id
                pacienteService.eliminarPaciente(id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("se elimino correctamente el paciente con el id"+id);
            //}
            //else{
              //  throw new ResourceNotFoundExeption("No se encontró el paciente a eliminar"+id);
                //no existe para eliminar
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el paciente a eliminar"+id);
            //}
        }

        @PostMapping //nos permitia poder crear o registrar un nuevo paciente.
        public ResponseEntity <Paciente> registrarPaciente(@RequestBody Paciente paciente){
            //chequear si el correo ya esta utilizado
            Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(paciente.getEmail());
            if(pacienteBuscado.isPresent()){
                //si existe ese paciente con ese email
                return ResponseEntity.badRequest().build();
            }
            else {
                return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
            }
        }
        @PutMapping
        public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
            //es preguntar si existe
            Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(paciente.getId());

            if(pacienteBuscado.isPresent()) {
                pacienteService.actualizarPaciente(paciente);
                return ResponseEntity.ok("Paciente actualizado" + " -" + paciente.getNombre());
            }
            else{
                return  ResponseEntity.badRequest().body("no se pudo actualizar el paciente "+paciente.getId()+" -"+paciente.getNombre());
            }

        }
        @GetMapping("/buscar/correo/{email}")
        public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email){
            Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
            if(pacienteBuscado.isPresent()){
                return ResponseEntity.ok(pacienteBuscado.get());
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
    }


