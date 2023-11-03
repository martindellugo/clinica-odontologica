package com.example.proyecto_integrador_final.entity;

import javax.persistence.*;


    @Entity
    @Table(name="domicilios")
    public class Domicilio {
        //atributos de la clase.
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column
        private String calle;
        @Column
        private Integer numero;
        @Column
        private  String localidad;
        @Column
        private String provincia;
        //generamos dos constructores debido  a que en uno se nos solicitará que sea autoincremental y en el otro lo voy a recuperar de la bdd.
        public Domicilio(String calle, Integer numero, String localidad, String provincia) {
            this.calle = calle;
            this.numero = numero;
            this.localidad = localidad;
            this.provincia = provincia;
        }

        public Domicilio(Long id, String calle, Integer numero, String localidad, String provincia) {
            this.id = id;
            this.calle = calle;
            this.numero = numero;
            this.localidad = localidad;
            this.provincia = provincia;
        }
        public Domicilio(){

        }
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public Integer getNumero() {
            return numero;
        }

        public void setNumero(Integer numero) {
            this.numero = numero;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }
    }


