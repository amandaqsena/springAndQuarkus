package amandaqsena.professor.model;
import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer>{
    
}
