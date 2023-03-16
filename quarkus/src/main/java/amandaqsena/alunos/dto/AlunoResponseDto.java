package amandaqsena.alunos.dto;

import java.util.Set;
import java.util.stream.Collectors;

import amandaqsena.alunos.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponseDto {
    private int id;
    private String nome;
    private Set<Integer> disciplinas;
    public static AlunoResponseDto from (Aluno aluno) {
        final AlunoResponseDtoBuilder response = new AlunoResponseDtoBuilder();
        response.id = aluno.getId();
        response.nome = aluno.getNome();
        response.disciplinas = aluno.getDisciplinas().stream().map(it -> it.getId()).collect(Collectors.toSet());
        return response.build();
    }

    @Override
    public String toString() {
        return "O meu nome é: " + nome;
    }
}
