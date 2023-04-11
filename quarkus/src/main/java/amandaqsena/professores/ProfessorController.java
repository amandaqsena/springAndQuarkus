package amandaqsena.professor;

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

import amandaqsena.professor.dto.ProfessorRequestDto;
import amandaqsena.professor.dto.ProfessorResponseDto;
import amandaqsena.professor.model.Professor;
import amandaqsena.professor.model.ProfessorRepository;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    private final ProfessorRepository repositorio;

    public ProfessorController(ProfessorRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GET
    public List<ProfessorResponseDto> buscarProfessores(@QueryParam("prefix") Optional<String> prefix) {

        return prefix
                .map(s -> repositorio
                        .findComPrefixo(s)
                        .stream()
                        .map(ProfessorResponseDto::from)
                        .collect(Collectors.toList()))
                .orElseGet(() -> Professor
                        .findAll()
                        .list()
                        .stream()
                        .map(it -> (Professor) it)
                        .map(ProfessorResponseDto::from)
                        .collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    public ProfessorResponseDto encontrarProfessorPorId(@PathParam("id") final int id) {
        return ProfessorResponseDto.from((Professor) Professor
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new));
    }

    @POST
    @Transactional
    public ProfessorResponseDto criarProfessor(final ProfessorResponseDto request) {
        final Professor professor = new Professor();
        professor.setNome(request.getNome());
        repositorio.persist(professor);
        return ProfessorResponseDto.from(professor);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagarProfessor(@PathParam("id") int id) {
        Professor.findByIdOptional(id)
                .orElseThrow(NotFoundException::new).delete();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public ProfessorResponseDto atualizarProfessor(@PathParam("id") int id, final ProfessorRequestDto request) {
        final Professor professor = (Professor) Professor
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        professor.setNome(request.getNome());
        professor.persist();
        return ProfessorResponseDto.from(professor);
    }

}

