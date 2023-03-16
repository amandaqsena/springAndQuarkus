package amandaqsena.disciplinas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import amandaqsena.disciplinas.dto.DisciplinaRequestDto;
import amandaqsena.disciplinas.dto.DisciplinaResponseDto;
import amandaqsena.disciplinas.model.Disciplina;
import amandaqsena.disciplinas.model.DisciplinaRepository;

@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaController {

    final private DisciplinaRepository repositorio;

    public DisciplinaController(DisciplinaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GET
    public List<DisciplinaResponseDto> buscarDisciplinas(@QueryParam("prefix") Optional<String> prefix) {

        return prefix
                .map(s -> repositorio
                        .findComPrefixo(s)
                        .stream()
                        .map(DisciplinaResponseDto::from)
                        .collect(Collectors.toList()))
                .orElseGet(() -> Disciplina
                        .findAll()
                        .list()
                        .stream()
                        .map(it -> (Disciplina) it)
                        .map(DisciplinaResponseDto::from)
                        .collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    public DisciplinaResponseDto encontrarDisciplina(@PathParam("id") final int id) {
        return DisciplinaResponseDto.from((Disciplina) Disciplina
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new));
    }

    @POST
    @Transactional
    public DisciplinaResponseDto criarDisciplina(final DisciplinaRequestDto request) {

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.getNome());

        repositorio.persist(disciplina);
        return DisciplinaResponseDto.from(disciplina);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagarDisciplina(@PathParam("id") int id) {
        Disciplina.findByIdOptional(id)
                .orElseThrow(NotFoundException::new).delete();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public DisciplinaResponseDto atualizarDisciplina(
            @PathParam("id") int id,
            final DisciplinaRequestDto request) {
        Disciplina disciplina = (Disciplina) Disciplina.findByIdOptional(id).orElseThrow(NotFoundException::new);

        disciplina.setNome(request.getNome());
        disciplina.persist();
        return DisciplinaResponseDto.from(disciplina);
    }

}
