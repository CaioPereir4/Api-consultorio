package med.voll.Api.domain.paciente.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.Api.domain.endereco.DadosEndereco;

public record atualizarPacienteDto(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
