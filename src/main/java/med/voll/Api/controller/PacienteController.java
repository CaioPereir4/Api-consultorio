package med.voll.Api.controller;

import jakarta.validation.Valid;
import med.voll.Api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void savePacientes(@RequestBody @Valid PacienteDto pacienteDto){
        this.pacienteRepository.save(new Paciente(pacienteDto));
    }

    @GetMapping
    public Page<ListagemPacienteDto> listPacientes(@PageableDefault(size = 10,sort = "nome") Pageable pageable){
        return this.pacienteRepository.findAll(pageable).map(ListagemPacienteDto::new);
    }

    @Transactional
    @PutMapping
    public void updatePaciente(@RequestBody atualizarPacienteDto atualPaciente){
        var paciente = this.pacienteRepository.getById(atualPaciente.id());
        paciente.atualizarPaciente(atualPaciente);

    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable(value = "id") Long id){
        var paciente = this.pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }

}
