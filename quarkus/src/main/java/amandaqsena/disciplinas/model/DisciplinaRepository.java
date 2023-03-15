package amandaqsena.disciplinas.model;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class DisciplinaRepository implements PanacheRepositoryBase<Disciplina, Integer> {
    public List<Disciplina> findComPrefixo(String prefixo) {
        return find("nome like ?1", prefixo + "%").list();
    }
}
