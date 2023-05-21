package med.voll.Api.domain.medico.dtos;

import med.voll.Api.domain.medico.Medico;

public record ListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ListagemMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
