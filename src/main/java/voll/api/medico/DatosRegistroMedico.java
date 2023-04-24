package voll.api.medico;
import voll.api.direccion.DatosDireccion;

public record DatosRegistroMedico(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion DatosDireccion) {
    
}
