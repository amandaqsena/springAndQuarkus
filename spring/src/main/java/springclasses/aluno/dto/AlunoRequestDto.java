package springclasses.aluno.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequestDto {
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$")
    private String nome;
}
