package amandaqsena.cursos.dto;

import java.util.Set;
import java.util.stream.Collectors;

import amandaqsena.cursos.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CursoResponseDto {
    private int id;
    private String nome;
    private String descricao;
    private int duracao;
    private Set<Integer> disciplinas;

    public static CursoResponseDto from(Curso curso) {
        return new CursoResponseDto(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getDuracao(),
                curso.getDisciplinas().stream().map(it-> it.getId()).collect(Collectors.toSet()));
    }
}
