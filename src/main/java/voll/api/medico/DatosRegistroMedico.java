package voll.api.medico;
import voll.api.direccion.*;


public record DatosRegistroMedico(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion direccion) {
    
}
