package amandaqsena.disciplinas.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import amandaqsena.alunos.model.Aluno;
import amandaqsena.cursos.model.Curso;
import amandaqsena.professor.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "disciplinas")
public class Disciplina extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne
    private Curso curso;
    @ManyToMany
    private List<Professor> professores;
    @ManyToMany
    private Set<Aluno> alunos;

    public void matriculaNaDisciplina(Aluno aluno){
        if(!alunos.add(aluno)){
            throw new IllegalArgumentException("Aluno já matriculado na disciplina");
        }
    }

    public void removeDisciplina(Aluno aluno){
        if(!alunos.remove(aluno)){
            throw new IllegalArgumentException("Aluno não estava matriculado na disciplina");
        }
    }

    
}
