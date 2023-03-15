package springclasses.aluno;

import java.util.List;
import java.util.Optional;

import springclasses.aluno.dto.AlunoRequestDto;
import springclasses.aluno.dto.AlunoResponseDto;

public interface AlunoService {

    AlunoResponseDto gravarAluno(AlunoRequestDto aluno);
    List<AlunoResponseDto> listarAlunos(Optional<String> prefixo);
    AlunoResponseDto buscarAluno(int id);
    void apagarAluno(int id);
    AlunoResponseDto atualizarAluno(int id, AlunoRequestDto aluno);
}
