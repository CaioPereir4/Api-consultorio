package med.voll.Api.medico.dtos;

import med.voll.Api.medico.Medico;

public record ListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ListagemMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
