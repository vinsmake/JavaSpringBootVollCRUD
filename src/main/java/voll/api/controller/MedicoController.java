package voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voll.api.medico.DatosRegistroMedico;
import voll.api.medico.MedicoRepository;
import voll.api.medico.Medico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    //this ask spring boot to implements MedicoRepository
    @Autowired
    private MedicoRepository medicoRepository;
    
    //this mapps a datosRegistroMedico object
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegistroMedico) {
        medicoRepository.save(new Medico(datosRegistroMedico));
    }
}
