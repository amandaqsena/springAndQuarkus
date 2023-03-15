package springclasses.disciplina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import springclasses.disciplina.persistance.Disciplina;

@Getter
@AllArgsConstructor
public class DisciplinaResponseDto {
    private int id;
    private String nome;

    public static DisciplinaResponseDto from (Disciplina disciplina) {
        return new DisciplinaResponseDto(disciplina.getId(), disciplina.getNome());
    }
}
