package med.voll.Api.domain.paciente.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.Api.domain.endereco.DadosEndereco;

public record PacienteDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String cpf,
        @NotBlank String telefone,
        @NotNull @Valid DadosEndereco endereco) {


}
