package com.example.proyecto_integrador_final.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping()
    public class SesionController {

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.invalidate(); // Invalida la sesión actual
        return "redirect:/login?logout"; // Redirige a la página de inicio de sesión con un mensaje de cierre de sesión exitoso
    }
    }

