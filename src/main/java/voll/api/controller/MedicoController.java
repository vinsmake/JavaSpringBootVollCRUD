package voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voll.api.medico.DatosRegistroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    //this mapps a datosRegistroMedico object
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegistroMedico) {
        
        System.out.println("El request ha llegado");
        System.out.println(datosRegistroMedico);
    }
}
