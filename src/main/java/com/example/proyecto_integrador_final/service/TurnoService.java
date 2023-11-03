package com.example.proyecto_integrador_final.service;

import com.example.proyecto_integrador_final.DTO.TurnoDTO;
import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.entity.Turno;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService {

    private static final Logger logger= Logger.getLogger(TurnoService.class);
    @Autowired
    private TurnoRepository turnoRepository;
    /*
    @Autowired
    public TurnoService() {
        this.turnoRepository = turnoRepository;
    }

     */

    //tenemos que generar todos los metodos.

    public List<TurnoDTO> listarTurnos(){
        List<Turno> turnosEncontrados= turnoRepository.findAll();
        //recorrer la lista para ir convirtiendo cada elemento
        List<TurnoDTO> respuesta=new ArrayList<>();
        for (Turno turno:turnosEncontrados) {
            respuesta.add(turnoAturnoDTO(turno));
        }
        return respuesta;
    }


    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return Optional.of(turnoAturnoDTO(turnoBuscado.get()));
        } else {
            return Optional.empty();
        }
    }



    public void eliminarTurno(Long id) throws ResourceNotFoundExeption {
        Optional<TurnoDTO> turnoAEliminar= buscarTurno(id);
        if(turnoAEliminar.isPresent()){
            turnoRepository.deleteById(id);
            logger.info("Se elimino un turno con id: "+ id);
        }
        else{
            logger.error("No se pudo eliminar un turno con id: "+ id);
            throw new ResourceNotFoundExeption("No se puede eliminar el turno con ID:"+id+".- ya que no existe en nuestra BDD");
        }
    }



    public void actualizarTurno(TurnoDTO turnodto) {
       turnoAturnoDTO(turnoRepository.save(turnoDTOaTurno(turnodto)));
       logger.info("Se actualizo un turno con id: " + turnodto.getId());
    }

    public TurnoDTO guardarTurno(TurnoDTO turnodto) {
        Turno turnoGuardado = turnoRepository.save(turnoDTOaTurno(turnodto));
        logger.info("Se registro un turno en la base de datos con id: "+ turnodto.getId());
        return turnoAturnoDTO(turnoGuardado);
    }



    private TurnoDTO turnoAturnoDTO(Turno turno){
        //convertir turno a un turnoDTO
        TurnoDTO respuesta= new TurnoDTO();
        //cargar la info de un turno a un turnoDTO
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setNombrePaciente(turno.getPaciente().getNombre());
        respuesta.setApellidoPaciente(turno.getPaciente().getApellido());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setApellidoOdontologo(turno.getOdontologo().getApellido());
        respuesta.setFecha(turno.getFecha());
        //devolverlos
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnodto){
        //convertir un dto a un turno
        Turno respuesta= new Turno();
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        //carga
        odontologo.setId(turnodto.getOdontologoId());
        odontologo.setApellido(turnodto.getApellidoOdontologo());
        paciente.setId(turnodto.getPacienteId());
        paciente.setNombre(turnodto.getNombrePaciente());
        paciente.setApellido(turnodto.getApellidoPaciente());
        respuesta.setFecha(turnodto.getFecha());
        respuesta.setId(turnodto.getId());
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        //salida
        return respuesta;
    }


}

  /*
    public Set<TurnoDTO> listarTurnos() {
         List<Turno> turnos= turnoRepository.findAll();
         Set<TurnoDTO> turnodto= new HashSet<>();
         for(Turno turno : turnos){
             turnodto.add(convertirTurnoaTurnoDTO(turno));
         }
         return turnodto;
    }

     */
//public Optional<Turno> buscarTurnosPorEmail (String email){
//    return turnoRepository.findByEmail(email);
//}

 /*public void eliminarTurno(Long id) {
       turnoRepository.deleteById(id);
    }

     */
 /*public TurnoDTO guardarTurno(TurnoDTO turnodto) {
       return turnoAturnoDTO(turnoRepository.save(turnoDTOaTurno(turnodto)));
    }

     */

