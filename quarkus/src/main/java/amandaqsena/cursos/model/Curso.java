package amandaqsena.cursos.model;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import amandaqsena.cursos.dto.CursoRequestDto;
import amandaqsena.disciplinas.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="cursos")
public class Curso extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descrição;
    private int duracao;
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Disciplina> disciplinas = new ArrayList<>();

    public static Curso fromCursoRequestDto(CursoRequestDto request){
        final CursoBuilder curso = new CursoBuilder();
        curso.descrição = request.getDescricao();
        curso.duracao = request.getDuracao();
        curso.nome = request.getNome();
        return curso.build();
    }
}
