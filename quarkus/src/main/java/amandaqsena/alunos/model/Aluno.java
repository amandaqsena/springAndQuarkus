package amandaqsena.alunos.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import amandaqsena.cursos.model.Curso;
import amandaqsena.disciplinas.model.Disciplina;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne
    private Curso curso;
    @ManyToMany
    private Set<Disciplina> disciplinas = new HashSet<>();

    public void matriculaNaDisciplina(Disciplina disciplina){
        if(!disciplinas.add(disciplina)){
            throw new IllegalArgumentException("Aluno já matriculado na disciplina");
        }
    }

    public void removeDisciplina(Disciplina disciplina){
        if(!disciplinas.remove(disciplina)){
            throw new IllegalArgumentException("Aluno não estava matriculado na disciplina");
        }
    }

}
