package voll.api.domain.medico;

import voll.api.domain.direccion.Direccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, Especialidad especialidad, Direccion direccion) {



    }
    

