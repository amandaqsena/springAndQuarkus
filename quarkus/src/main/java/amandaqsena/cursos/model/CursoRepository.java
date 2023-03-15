package amandaqsena.cursos.model;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CursoRepository implements PanacheRepositoryBase<Curso,Integer>{
    public List<Curso> findComPrefixo(String prefixo){
        return find("nome like ?1",prefixo+"%").list();
    }
}
