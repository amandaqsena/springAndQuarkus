package amandaqsena.professor.dto;

import amandaqsena.professor.model.Professor;
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
public class ProfessorResponseDto {
    private int id;
    private String nome;

    public static ProfessorResponseDto from (Professor professor) {
        final ProfessorResponseDtoBuilder response = new ProfessorResponseDtoBuilder();
        response.id = professor.getId();
        response.nome = professor.getNome();
        return response.build();
    }

    @Override
    public String toString() {
        return "O meu nome é: " + nome;
    }
}