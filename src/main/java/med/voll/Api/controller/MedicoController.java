package med.voll.Api.controller;


import jakarta.validation.Valid;
import med.voll.Api.domain.medico.Medico;
import med.voll.Api.domain.medico.MedicoRepository;
import med.voll.Api.domain.medico.*;
import med.voll.Api.domain.medico.dtos.DadosCadastroMedico;
import med.voll.Api.domain.medico.dtos.DetalhamentoMedicoDto;
import med.voll.Api.domain.medico.dtos.ListagemMedicoDto;
import med.voll.Api.domain.medico.dtos.atualizarMedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedicoDto(medico));
    }
    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDto>> mostrarMedicos(@PageableDefault(page = 0, size = 10, sort = "nome") Pageable paginacao ){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedicoById(@PathVariable(value = "id") Long id){
            var medico = repository.getReferenceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new DetalhamentoMedicoDto(medico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity atualizarMedico(@RequestBody @Valid atualizarMedicoDto dados){
        var medico = repository.getById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetalhamentoMedicoDto(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletarMedico(@PathVariable(value = "id") Long id){
        var medico = this.repository.getById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }
}
