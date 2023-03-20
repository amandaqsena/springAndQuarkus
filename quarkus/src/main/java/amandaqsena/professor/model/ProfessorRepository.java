package amandaqsena.professor.model;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer> {
    public List<Professor> findComPrefixo(String prefixo) {
        return find("nome like ?1", prefixo + "%").list();
    }
}
