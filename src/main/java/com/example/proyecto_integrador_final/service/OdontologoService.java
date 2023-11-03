package com.example.proyecto_integrador_final.service;

import com.example.proyecto_integrador_final.entity.Odontologo;
import com.example.proyecto_integrador_final.exception.ResourceNotFoundExeption;
import com.example.proyecto_integrador_final.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {

    private static final Logger logger= Logger.getLogger(OdontologoService.class);
    @Autowired
    private OdontologoRepository odontologoRepository;

   /* @Autowired
    public OdontologoService() {
        this.odontologoRepository = odontologoRepository;
    }

    */



    public Odontologo guardarOdontologo(Odontologo odontologo) {
        Odontologo odontologoGuardado= odontologoRepository.save(odontologo);
        logger.info("Se registro un odontologo con nombre: "+ odontologo.getNombre() + " " + odontologo.getApellido());
        return odontologoGuardado;
    }

    public Optional<Odontologo> buscarOdontologo(Long id) {
      // logger.info(" Se encontro un odontologo con id: "+ id);
        return odontologoRepository.findById(id);
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundExeption {
        Optional<Odontologo> odontologoAEliminar= buscarOdontologo(id);
        if(odontologoAEliminar.isPresent()){
            odontologoRepository.deleteById(id);
            logger.info("Se elimino correctamente un odontologo con id: " + id);
        }
        else{
            logger.error("no se puede eliminar el odontologo con id: "+id);
            throw new ResourceNotFoundExeption("No se puede eliminar el odontologo con ID:"+id+".- ya que no existe en nuestra BDD");
        }
    }

    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
        logger.info(" Se actualizo un odontologo con id: "+ odontologo.getId());
    }

    public List<Odontologo> buscarOdontologos() {
        return odontologoRepository.findAll();
    }



}


    /*public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
   */

 /*
    public Optional<Odontologo> buscarOdontologoPorCorreo(String correo) {
        return odontologoRepository.findByEmail(correo); //lo creo en el repository, no lo tengo
    }
    */
