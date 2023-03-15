package springclasses.aluno;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import springclasses.aluno.dto.AlunoRequestDto;
import springclasses.aluno.dto.AlunoResponseDto;
import springclasses.aluno.persistance.Aluno;
import springclasses.aluno.persistance.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

    final private AlunoRepository repositorio;

    public AlunoServiceImpl(AlunoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public AlunoResponseDto gravarAluno(AlunoRequestDto request) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        return AlunoResponseDto.from(repositorio.save(aluno));
    }

    @Override
    public List<AlunoResponseDto> listarAlunos(Optional<String> prefixo) {
        final List<Aluno> listaAlunos = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaAlunos.stream().map(AlunoResponseDto::from).collect(Collectors.toList());
    }

    @Override
    public AlunoResponseDto buscarAluno(int id) {
        return AlunoResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")));
    }

    @Override
    public void apagarAluno(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public AlunoResponseDto atualizarAluno(int id, AlunoRequestDto request) {
        final Aluno aluno = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setNome(request.getNome());
        return AlunoResponseDto.from(repositorio.save(aluno));
    }
}
