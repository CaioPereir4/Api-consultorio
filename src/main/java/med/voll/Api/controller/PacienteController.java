package med.voll.Api.controller;

import jakarta.validation.Valid;
import med.voll.Api.domain.paciente.Paciente;
import med.voll.Api.domain.paciente.PacienteRepository;
import med.voll.Api.domain.paciente.*;
import med.voll.Api.domain.paciente.dtos.DetalharPacienteDto;
import med.voll.Api.domain.paciente.dtos.ListagemPacienteDto;
import med.voll.Api.domain.paciente.dtos.PacienteDto;
import med.voll.Api.domain.paciente.dtos.atualizarPacienteDto;
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
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity savePacientes(@RequestBody @Valid PacienteDto pacienteDto, UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(pacienteDto);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalharPacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDto>> listPacientes(@PageableDefault(size = 10,sort = "nome") Pageable pageable){
        var paciente = this.pacienteRepository.findAll(pageable).map(ListagemPacienteDto::new);
        return ResponseEntity.status(HttpStatus.OK).body(paciente);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharPacienteDto> listPacienteById(@PathVariable(value = "id") Long id){
        var pacienteDetalhado = new DetalharPacienteDto(pacienteRepository.getReferenceById(id));
        return   ResponseEntity.status(HttpStatus.OK).body(pacienteDetalhado);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updatePaciente(@RequestBody atualizarPacienteDto atualPaciente){
        var paciente = this.pacienteRepository.getById(atualPaciente.id());
        paciente.atualizarPaciente(atualPaciente);
        return ResponseEntity.status(HttpStatus.OK).body(new DetalharPacienteDto(paciente));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable(value = "id") Long id){
        var paciente = this.pacienteRepository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }

}
