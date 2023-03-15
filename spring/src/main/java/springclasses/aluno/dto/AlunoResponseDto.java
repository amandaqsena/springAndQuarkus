package springclasses.aluno.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import springclasses.aluno.persistance.Aluno;

@Getter
@AllArgsConstructor
public class AlunoResponseDto {
    private int id;
    private String nome;

    public static AlunoResponseDto from (Aluno aluno) {
        return new AlunoResponseDto(aluno.getId(), aluno.getNome());
    }
}
