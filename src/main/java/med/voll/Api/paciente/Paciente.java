package med.voll.Api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.Api.endereco.Endereco;
import med.voll.Api.paciente.dtos.PacienteDto;
import med.voll.Api.paciente.dtos.atualizarPacienteDto;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private boolean ativo;
    
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDto pacienteDto) {
        this.ativo = true;
        this.nome = pacienteDto.nome();
        this.email = pacienteDto.email();
        this.cpf = pacienteDto.cpf();
        this.telefone = pacienteDto.telefone();
        this.endereco = new Endereco(pacienteDto.endereco());
    }

    public void atualizarPaciente(atualizarPacienteDto atualPaciente) {
        if(atualPaciente.nome() != null){
            this.nome = atualPaciente.nome();
        }
        else if(atualPaciente.telefone() != null){
            this.telefone = atualPaciente.telefone();
        }else if( atualPaciente.endereco() != null){
            this.endereco = new Endereco(atualPaciente.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
