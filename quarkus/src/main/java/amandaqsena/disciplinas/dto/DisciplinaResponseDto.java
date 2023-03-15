package amandaqsena.disciplinas.dto;

import amandaqsena.disciplinas.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisciplinaResponseDto {
    private int id;
    private String nome;

    public static DisciplinaResponseDto from(Disciplina disciplina) {
        return new DisciplinaResponseDto(disciplina.getId(), disciplina.getNome());
    }
}
