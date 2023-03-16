package amandaqsena.disciplinas.dto;

import java.util.Set;
import java.util.stream.Collectors;

import amandaqsena.cursos.model.Curso;
import amandaqsena.disciplinas.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisciplinaResponseDto {
    private int id;
    private String nome;
    private Curso curso;
    private Set<Integer> alunos;

    public static DisciplinaResponseDto from(Disciplina disciplina) {
        return new DisciplinaResponseDto(disciplina.getId(), disciplina.getNome(), disciplina.getCurso(),
                disciplina.getAlunos().stream().map(it -> it.getId()).collect(Collectors.toSet()));
    }
}
