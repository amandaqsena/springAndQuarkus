package amandaqsena.disciplinas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;
    @ManyToMany
    private List<Professor> professores;
    @ManyToMany
    private List<Aluno> alunos;
}
