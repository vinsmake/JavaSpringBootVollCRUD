package voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import voll.api.domain.direccion.*;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
    
}
