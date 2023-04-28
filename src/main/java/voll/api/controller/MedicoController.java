package voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import voll.api.medico.DatosListadoMedico;
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
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

    //gives the list to the get request
    //we decide what to give to the get request in DatosListadoMedico
    //As we use Page, we dont get a list, we get a page. 
    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(Pageable paginacion){
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }
}
