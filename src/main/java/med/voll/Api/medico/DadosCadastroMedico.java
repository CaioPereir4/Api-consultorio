package med.voll.Api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.Api.endereco.DadosEndereco;

public record DadosCadastroMedico(
                                 @NotBlank String nome,
                                 @NotNull String telefone,
                                 @NotBlank @Email String email,
                                  @NotNull   @Pattern(regexp = "\\d{4,6}") String crm,
                                 @NotNull Especialidade especialidade,

                                  @NotNull @Valid DadosEndereco endereco) {
}
