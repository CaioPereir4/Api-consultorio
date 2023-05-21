package med.voll.Api.domain.medico.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.Api.domain.endereco.DadosEndereco;


public record atualizarMedicoDto(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
