package voll.api.medico;

import jakarta.validation.constraints.NotNull;
import voll.api.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
    
}
