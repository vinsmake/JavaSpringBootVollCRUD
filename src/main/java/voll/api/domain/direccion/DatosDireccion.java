package voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

//creates a normal class but when we run the code
public record DatosDireccion(
    @NotBlank String calle, 
    
    @NotBlank String distrito, 
    
    @NotBlank String ciudad, 
    
    @NotBlank String numero, 
    
    @NotBlank String complemento) {
    
}
