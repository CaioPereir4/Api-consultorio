package med.voll.Api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.Api.endereco.DadosEndereco;
import med.voll.Api.endereco.Endereco;

public record PacienteDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String cpf,
        @NotBlank String telefone,
        @NotNull @Valid DadosEndereco endereco) {

}
