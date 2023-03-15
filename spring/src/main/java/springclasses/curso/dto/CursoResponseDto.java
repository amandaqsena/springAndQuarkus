package springclasses.curso.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import springclasses.curso.persistance.Curso;
import springclasses.disciplina.dto.DisciplinaResponseDto;

@Getter
@AllArgsConstructor
public class CursoResponseDto {
    private int id;
    private String nome;
    private String descricao;
    private int duracao;
    private List<DisciplinaResponseDto> disciplinas;

    public static CursoResponseDto from (Curso curso) {
        return new CursoResponseDto(
            curso.getId(),
            curso.getNome(),
            curso.getDescrição(),
            curso.getDuracao(),
            curso.getDisciplinas().stream().map(DisciplinaResponseDto::from).collect(Collectors.toList()));
    }
}
