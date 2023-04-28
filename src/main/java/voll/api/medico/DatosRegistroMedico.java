package voll.api.medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import voll.api.direccion.*;


public record DatosRegistroMedico(
    //with validation dependency we can validate the data, NotBlank is NotData
    @NotBlank String nombre,

    @NotBlank @Email String email,
    @NotBlank String telefono,
    //it need to be a number of 4 to 10 digits
    @NotBlank @Pattern(regexp = "\\d{4,10}") String documento, 

    @NotNull Especialidad especialidad, 
    //we use NotNull when it's an object
    @NotNull @Valid DatosDireccion direccion) {
    
}
