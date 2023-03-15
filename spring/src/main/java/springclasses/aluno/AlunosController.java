package springclasses.aluno;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import springclasses.aluno.dto.AlunoRequestDto;
import springclasses.aluno.dto.AlunoResponseDto;

@RestController
@RequestMapping(path = {"/alunos"})
public class AlunosController {

    final private AlunoService servico;

    public AlunosController(AlunoService servico) {
        this.servico = servico;
    }

    @PostMapping
    public AlunoResponseDto criarAluno(
        @Valid @RequestBody final AlunoRequestDto request
        ) {
        return servico.gravarAluno(request);
    }

    @GetMapping
    @SecurityRequirements
    public List<AlunoResponseDto> listarAlunos(
        @RequestParam final Optional<String> prefixo
        ) {
        return servico.listarAlunos(prefixo);
    }

    @GetMapping("/{id}")
    public AlunoResponseDto buscarAluno(
        @PathVariable("id") final int id
    ) {
        return servico.buscarAluno(id);
    }

    @SecurityRequirements(@SecurityRequirement(name = "JWT"))
    @PutMapping("/{id}")
    public AlunoResponseDto atualizaraluno(
        @PathVariable final int id,
        @RequestBody final AlunoRequestDto request
    ) {
        return servico.atualizarAluno(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void apagarAluno(
        @PathVariable final int id
    ) {
        servico.apagarAluno(id);
    }
}
