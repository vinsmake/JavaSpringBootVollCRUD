package voll.api.controller;

import java.net.URI;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import voll.api.medico.DatosListadoMedico;
import voll.api.medico.DatosRegistroMedico;
import voll.api.medico.DatosRespuestaMedico;
import voll.api.medico.MedicoRepository;
import voll.api.medico.Medico;
import voll.api.direccion.*;
import voll.api.medico.DatosActualizarMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    //this ask spring boot to implements MedicoRepository
    @Autowired
    private MedicoRepository medicoRepository;
    

    //This is what we do with the POST request
    //@RequestBody says that the data recibed have to be mapped as a DatosRegistroMedico class
    //then, we sabe a new Medico with the data recived
    //with UriComponentsBuilder we can get the server url
    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> postMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
        medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(), medico.getEspecialidad(), 
        new Direccion (
            medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())
        );
        //we use uriComponentsBuilder to get the url of the server and build it
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }


    //gives the list to the GET request
    //we decide what to give to the get request in DatosListadoMedico
    //As we use Page, we dont get a list, we get a page. 
    //pagedefaul decides which are the default parameters.
    @GetMapping
    public Page<DatosListadoMedico> getMedicos(@PageableDefault(size = 2) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);

        //findByActivoTrue it's a spring boot nomenglature
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }


    //this is what we do with the PUT request
    @PutMapping
    //with transactional we finish the transaction, otherwise the put will not be saved
    @Transactional
        //ResponseEntity makes that the server answers the .noContent when use the mapping. We get a "200 ok" as an anwer
        //we return a new DatosRespuestaMedico because returning medico is a bad idea
    public ResponseEntity putMedico(@RequestBody @Valid DatosActualizarMedico DatosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(DatosActualizarMedico.id());
        medico.actualizarDatos(DatosActualizarMedico);
        //we get an answer with the updated info of the medic
        return ResponseEntity.ok(new DatosRespuestaMedico(
            medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(), medico.getEspecialidad(), 
            new Direccion (
                medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())
        ));
    }

    //this is what we do with the DEL request
    //logic delete, we desactive a medic.
    @DeleteMapping("/{id}")
    @Transactional
    //ResponseEntity makes that the server answers the .noContent when use the mapping. We get a "204 no content" as an anwer
    public ResponseEntity delMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
    //    medicoRepository.delete(medico);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
}
