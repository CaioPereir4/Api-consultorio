package med.voll.Api.medico.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import med.voll.Api.endereco.DadosEndereco;
import med.voll.Api.endereco.Endereco;


public record atualizarMedicoDto(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
