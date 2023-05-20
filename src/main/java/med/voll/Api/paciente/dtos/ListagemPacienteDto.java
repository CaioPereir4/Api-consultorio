package med.voll.Api.paciente.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.Api.paciente.Paciente;

public record ListagemPacienteDto(
                                @NotNull Long id,
                                @NotBlank String nome,
                                @NotBlank @Email String email,
                                @NotBlank  String cpf) {
    public ListagemPacienteDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
