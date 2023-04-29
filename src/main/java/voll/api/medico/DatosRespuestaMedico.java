package voll.api.medico;


import voll.api.direccion.DatosDireccion;
import voll.api.direccion.Direccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, Especialidad especialidad, Direccion direccion) {



    }
    

