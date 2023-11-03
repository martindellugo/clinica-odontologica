package com.example.proyecto_integrador_final.service;

import com.example.proyecto_integrador_final.entity.Paciente;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger logger= Logger.getLogger(PacienteService.class);
    @Autowired
    private PacienteRepository pacienteRepository;
    /*
    @Autowired
    public PacienteService() {
        this.pacienteRepository = pacienteRepository;
    }

     */
    public Paciente guardarPaciente(Paciente paciente){
        logger.info("se registro un paciente con nombre: "+ paciente.getNombre() + " " + paciente.getApellido());
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public void eliminarPaciente(Long id) throws ResourceNotFoundExeption {
        Optional<Paciente> odontologoAEliminar= buscarPaciente(id);
        if(odontologoAEliminar.isPresent()){
            pacienteRepository.deleteById(id);
            logger.info("Se elimino un paciente con id: " + id);
        }
        else{
            logger.error("No se pudo eliminar el paciente con id: " + id);
            throw new ResourceNotFoundExeption("No se puede eliminar el paciente con ID:"+id+".- ya que no existe en nuestra BDD");
        }
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
        logger.info("Se actualizo en la base de datos el paciente de nombre: " + paciente.getNombre() + " " + paciente.getApellido());
    }
    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente>buscarPacientePorCorreo(String correo){
        return pacienteRepository.findByEmail(correo); //lo creo en el repository, no lo tengo
    }

}

  /*
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

     */
