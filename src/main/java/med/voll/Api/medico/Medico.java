package med.voll.Api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.Api.endereco.Endereco;
import med.voll.Api.medico.dtos.DadosCadastroMedico;
import med.voll.Api.medico.dtos.Especialidade;
import med.voll.Api.medico.dtos.atualizarMedicoDto;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String telefone;


    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)

    @Column(nullable = false, length = 70)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;



    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacoes(atualizarMedicoDto dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        else if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        else if(dados.endereco() != null){
            this.endereco = new Endereco(dados.endereco());
        }

    }

    public void inativar() {
        this.ativo =false;
    }
}
