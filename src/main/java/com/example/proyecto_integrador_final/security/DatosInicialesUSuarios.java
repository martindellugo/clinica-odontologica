package com.example.proyecto_integrador_final.security;


import com.example.proyecto_integrador_final.entity.Usuario;
import com.example.proyecto_integrador_final.entity.UsuarioRole;
import com.example.proyecto_integrador_final.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUSuarios implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passsSinCifrar = "digital";
        String passCifrado = cifrador.encode(passsSinCifrar);
        Usuario usuarioAInsertar = new Usuario("user", "user", "user@gmail.com", passCifrado, UsuarioRole.ROLE_USER);

        usuarioRepository.save(usuarioAInsertar);
        //creamos un usuario ADMIN
        String passinCrifrar2 = "house";
        String passCifrado2 = cifrador.encode(passinCrifrar2);
        Usuario usuarioAInsertar2 = new Usuario("admin", "admin", "admin@gmail.com", passCifrado2, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);
    }

    }
