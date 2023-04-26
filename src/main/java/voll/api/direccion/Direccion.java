package voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Extends-like
@Embeddable
//lombok generates getters when compiles
@Getter
//creates a constructor without arguments(default)
@NoArgsConstructor
//creates a constructor with all the arguments
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DatosDireccion direccion){
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
    }




}
