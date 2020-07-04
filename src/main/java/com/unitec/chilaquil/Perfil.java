
package com.unitec.chilaquil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Perfil {
    
    @Id
    private String id;
    
    private String nombre;
    private String patrerno;
    private String email;
    private String celular;
    private int edad;

    @Override
    public String toString() {
        return "Perfil{" + "id=" + id + ", nombre=" + nombre + ", patrerno=" + patrerno + ", email=" + email + ", celular=" + celular + ", edad=" + edad + '}';
    }
    
    
    public Perfil() {
       
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPatrerno() {
        return patrerno;
    }

    public void setPatrerno(String patrerno) {
        this.patrerno = patrerno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
