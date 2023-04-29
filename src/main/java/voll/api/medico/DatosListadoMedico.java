package voll.api.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {
    

    //uset to get only the necesary data
    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
