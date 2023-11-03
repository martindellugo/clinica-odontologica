package com.example.proyecto_integrador_final.controller;

import com.example.proyecto_integrador_final.DTO.TurnoDTO;
import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.entity.Turno;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.service.OdontologoService;
import com.example.proyecto_integrador_final.service.PacienteService;
import com.example.proyecto_integrador_final.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTurnos(){
        //ahora mandamos todo dentro de response
        return ResponseEntity.ok(turnoService.listarTurnos());
    }


    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnodto){
        ResponseEntity<TurnoDTO> respuesta;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(turnodto.getPacienteId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(turnodto.getOdontologoId());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            respuesta= ResponseEntity.ok(turnoService.guardarTurno(turnodto));
        }
        else {
            respuesta= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundExeption {
       // Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurno(id);
       // if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("se elimino el turno con id "+id);
       // }
        //else {
          //  throw new ResourceNotFoundExeption("No se encontró el turno a eliminar"+id);
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se logro eliminar +" +
                 //   "el turno con id " +id+ "de la base de daros");
       // }
    }

    

    @PutMapping()
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnodto){
        //podemos encontrarlo con el id del turno
        //con el paciente o odontologo
        Optional<TurnoDTO> turnoBuscado=turnoService.buscarTurno(turnodto.getId());
        if(turnoBuscado.isPresent()) {
            //el turno existe. lo que deberiamos hacer es verificar el resto
            //OdontologoService odontologoService = new OdontologoService();
            //PacienteService pacienteService = new PacienteService();
            Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(turnodto.getPacienteId());
            Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(turnodto.getOdontologoId());
            if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
                turnoService.actualizarTurno(turnodto);
                return ResponseEntity.ok("Se actualizó el turno con id: " + turnodto.getId());

            } else {
                return ResponseEntity.badRequest().body("No se pudo actualizar el turno con id:" + turnodto.getId() + "ya que hay un error en el paciente u odontologo");

            }
        }
        else{
            return ResponseEntity.badRequest().body("no encontramos el turno que usted quiere modificar"+turnodto.getId());


        }
    }
}

 /*
    @GetMapping
    public ResponseEntity<Set<TurnoDTO>> buscarTodosLosTurnos(){
        //ahora mandamos todo dentro de response
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

     */
