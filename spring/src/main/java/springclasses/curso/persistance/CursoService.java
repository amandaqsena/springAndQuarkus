package springclasses.curso.persistance;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import springclasses.curso.dto.CursoRequestDto;
import springclasses.curso.dto.CursoResponseDto;
import springclasses.disciplina.dto.DisciplinaRequestDto;
import springclasses.disciplina.persistance.Disciplina;
import springclasses.disciplina.persistance.DisciplinaRepository;

@Service
public class CursoService {
    final private CursoRepository repositorio;
    final private DisciplinaRepository disciplinaRepository;

    public CursoService(CursoRepository repositorio,
                        DisciplinaRepository disciplinaRepository) {
        this.repositorio = repositorio;
        this.disciplinaRepository = disciplinaRepository;
    }

    public CursoResponseDto gravarCurso(CursoRequestDto request) {
        final Curso curso = new Curso();
        curso.setNome(request.getNome());
        curso.setDescrição(request.getDescricao());
        curso.setDuracao(request.getDuracao());
        return CursoResponseDto.from(repositorio.save(curso));
    }

    public List<CursoResponseDto> listarCurso(Optional<String> prefixo) {
        final List<Curso> listaCursos = prefixo.isPresent() ? repositorio.findByNomeStartsWith(prefixo.get()) : repositorio.findAll();
        return listaCursos.stream().map(CursoResponseDto::from).collect(Collectors.toList());
    }

    public CursoResponseDto buscarCurso(int id) {
        return CursoResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado")));
    }

    public void apagarCurso(int id) {
        repositorio.deleteById(id);
    }

    public CursoResponseDto atualizarCurso(int id, CursoRequestDto request) {
        final Curso curso = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.setNome(request.getNome());
        return CursoResponseDto.from(repositorio.save(curso));
    }

    public CursoResponseDto adicionarDisciplina(int id, DisciplinaRequestDto request) {
        final Curso curso = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        final Disciplina disc = new Disciplina();
        disc.setNome(request.getNome());
        disc.setCurso(curso);
        disciplinaRepository.save(disc);
        return CursoResponseDto.from(repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado")));
    }
}
