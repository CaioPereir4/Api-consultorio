package med.voll.Api.paciente.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.Api.endereco.DadosEndereco;

public record atualizarPacienteDto(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
