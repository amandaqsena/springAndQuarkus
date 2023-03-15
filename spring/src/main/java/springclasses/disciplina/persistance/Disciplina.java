package springclasses.disciplina.persistance;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springclasses.aluno.persistance.Aluno;
import springclasses.curso.persistance.Curso;
import springclasses.professor.persistance.Professor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;
    @ManyToMany
    private List<Professor> professores;
    @OneToMany(mappedBy = "disciplina")
    private List<Aluno> alunos;
}
