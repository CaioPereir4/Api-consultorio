package med.voll.Api.controller;


import jakarta.validation.Valid;
import med.voll.Api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }
    @GetMapping
    public Page<ListagemMedicoDto> mostrarMedicos( @PageableDefault(page = 0, size = 10, sort = "nome") Pageable paginacao ){
        return repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new);
    }

    @Transactional
    @PutMapping
    public void atualizarMedico(@RequestBody @Valid atualizarMedicoDto dados){
        var medico = this.repository.getById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarMedico(@PathVariable(value = "id") Long id){
        var medico = this.repository.getById(id);
        medico.inativar();

    }
}
