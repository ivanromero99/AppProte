package com.example.appprote.model;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.appprote.R;



public class Perro {

    private String id;
    private int edad;
    private String nombre;
    private String raza;
    private String sexo;
    private String descripcion;
    private String modulo;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModulo(){ return modulo; }

    public void setModulo(String modulo) { this.modulo = modulo; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {

        return "\n" + getNombre().toUpperCase() + "\n\n" + getRaza() + "\n" + getSexo() + "\n" + "Módulo " + getModulo() + "\n";
    }
}
