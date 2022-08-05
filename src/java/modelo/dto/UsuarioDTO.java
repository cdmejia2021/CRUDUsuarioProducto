/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

//librerias y clases importadas
import java.io.Serializable;     //libreria para hacer que los objetos de tipo DTO se puedan transportar entre paquetes
import java.util.Date;           //libreria utilizada para manipular variables de tipo Date
import java.util.Objects;        //libreria utilizada para la comparación de objetos en el equals

/**
 *
 * @author Usuario
 */
public class UsuarioDTO implements Serializable {

    //atributos
    private int id_us;
    private String nombre1_us;
    private String nombre2_us;
    private String apellido1_us;
    private String apellido2_us;
    private String nIdentificacion_us;
    private String correo_us;
    private String clave_us;
    private String sexo_us;
    private Date fechaNac_us;
    private boolean estado_us;

    //constructores
    public UsuarioDTO(int id_us, String nombre1_us, String nombre2_us, String apellido1_us, String apellido2_us,
            String nIdentificacion_us, String correo_us, String clave_us, String sexo_us, Date fechaNac_us,
            boolean estado_us) {
        this.id_us = id_us;
        this.nombre1_us = nombre1_us;
        this.nombre2_us = nombre2_us;
        this.apellido1_us = apellido1_us;
        this.apellido2_us = apellido2_us;
        this.nIdentificacion_us = nIdentificacion_us;
        this.correo_us = correo_us;
        this.clave_us = clave_us;
        this.sexo_us = sexo_us;
        this.fechaNac_us = fechaNac_us;
        this.estado_us = estado_us;
    }

    public UsuarioDTO(String nombre1_us, String nombre2_us, String apellido1_us, String apellido2_us,
            String nIdentificacion_us, String correo_us, String clave_us, String sexo_us, Date fechaNac_us,
            boolean estado_us) {
        this.nombre1_us = nombre1_us;
        this.nombre2_us = nombre2_us;
        this.apellido1_us = apellido1_us;
        this.apellido2_us = apellido2_us;
        this.nIdentificacion_us = nIdentificacion_us;
        this.correo_us = correo_us;
        this.clave_us = clave_us;
        this.sexo_us = sexo_us;
        this.fechaNac_us = fechaNac_us;
        this.estado_us = estado_us;
    }

    public UsuarioDTO() {
    }

    //setters y getters de la clase
    public int getId_us() {
        return id_us;
    }

    public void setId_us(int id_us) {
        this.id_us = id_us;
    }

    public String getNombre1_us() {
        return nombre1_us;
    }

    public void setNombre1_us(String nombre1_us) {
        this.nombre1_us = nombre1_us;
    }

    public String getNombre2_us() {
        return nombre2_us;
    }

    public void setNombre2_us(String nombre2_us) {
        this.nombre2_us = nombre2_us;
    }

    public String getApellido1_us() {
        return apellido1_us;
    }

    public void setApellido1_us(String apellido1_us) {
        this.apellido1_us = apellido1_us;
    }

    public String getApellido2_us() {
        return apellido2_us;
    }

    public void setApellido2_us(String apellido2_us) {
        this.apellido2_us = apellido2_us;
    }

    public String getNIdentificacion_us() {
        return nIdentificacion_us;
    }

    public void setNIdentificacion_us(String nIdentificacion_us) {
        this.nIdentificacion_us = nIdentificacion_us;
    }

    public String getCorreo_us() {
        return correo_us;
    }

    public void setCorreo_us(String correo_us) {
        this.correo_us = correo_us;
    }

    public String getClave_us() {
        return clave_us;
    }

    public void setClave_us(String clave_us) {
        this.clave_us = clave_us;
    }

    public String getSexo_us() {
        return sexo_us;
    }

    public void setSexo_us(String sexo_us) {
        this.sexo_us = sexo_us;
    }

    public Date getFechaNac_us() {
        return fechaNac_us;
    }

    public void setFechaNac_us(Date fechaNac_us) {
        this.fechaNac_us = fechaNac_us;
    }

    public boolean isEstado_us() {
        return estado_us;
    }

    public void setEstado_us(boolean estado_us) {
        this.estado_us = estado_us;
    }

    //toString para imprimir directamente el objeto
    @Override
    public String toString() {
        return this.nombre1_us +" "+ this.nombre2_us + " "+this.apellido1_us +" "+this.apellido2_us;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;   //comparación atributo por atributo
        if (this.id_us != other.id_us) {
            return false;
        }
        if (!Objects.equals(this.nombre1_us, other.nombre1_us)) {
            return false;
        }
        if (!Objects.equals(this.nombre2_us, other.nombre2_us)) {
            return false;
        }
        if (!Objects.equals(this.apellido1_us, other.apellido1_us)) {
            return false;
        }
        if (!Objects.equals(this.apellido2_us, other.apellido2_us)) {
            return false;
        }
        if (!Objects.equals(this.nIdentificacion_us, other.nIdentificacion_us)) {
            return false;
        }
        if (!Objects.equals(this.correo_us, other.correo_us)) {
            return false;
        }
        if (!Objects.equals(this.clave_us, other.clave_us)) {
            return false;
        }
        if (!Objects.equals(this.sexo_us, other.sexo_us)) {
            return false;
        }
        if (!Objects.equals(this.fechaNac_us, other.fechaNac_us)) {
            return false;
        }
        if (!Objects.equals(this.estado_us, other.estado_us)) {
            return false;
        }
        return true;
    }
}// fin clase
