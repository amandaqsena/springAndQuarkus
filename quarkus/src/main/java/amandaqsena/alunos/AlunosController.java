package amandaqsena.alunos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import amandaqsena.alunos.dto.AlunoRequestDto;
import amandaqsena.alunos.dto.AlunoResponseDto;
import amandaqsena.alunos.model.Aluno;
import amandaqsena.alunos.model.AlunoRepositorio;
import amandaqsena.disciplinas.model.Disciplina;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {

    final private AlunoRepositorio repositorio;

    public AlunosController(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GET
    public List<AlunoResponseDto> buscarAlunos(@QueryParam("prefix") Optional<String> prefix) {

        return prefix
                .map(s -> repositorio
                        .findComPrefixo(s)
                        .stream()
                        .map(AlunoResponseDto::from)
                        .collect(Collectors.toList()))
                .orElseGet(() -> Aluno
                        .findAll()
                        .list()
                        .stream()
                        .map(it -> (Aluno) it)
                        .map(AlunoResponseDto::from)
                        .collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    public AlunoResponseDto encontrarAlunoPorId(@PathParam("id") final int id) {
        return AlunoResponseDto.from((Aluno) Aluno
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new));
    }

    @POST
    @Transactional
    public AlunoResponseDto criarAluno(final AlunoRequestDto request) {
        final Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        repositorio.persist(aluno);
        return AlunoResponseDto.from(aluno);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagarAluno(@PathParam("id") int id) {
        Aluno.findByIdOptional(id)
                .orElseThrow(NotFoundException::new).delete();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public AlunoResponseDto atualizarAluno(@PathParam("id") int id, final AlunoRequestDto request) {
        final Aluno aluno = (Aluno) Aluno
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        aluno.setNome(request.getNome());
        aluno.persist();
        return AlunoResponseDto.from(aluno);
    }

    @Transactional
    @PATCH
    @Path("/{id}/adicionar")
    public Response inscreveAlunoNaDisciplina(@PathParam("id") int id, @QueryParam("idDisciplina") int idDisciplina)  {
        final Aluno aluno = (Aluno) Aluno
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        
        final Disciplina disciplina = (Disciplina) Disciplina
                .findByIdOptional(idDisciplina)
                .orElseThrow(NotFoundException::new);
        try {
            aluno.matriculaNaDisciplina(disciplina);
            disciplina.matriculaNaDisciplina(aluno);
        } catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity("{\"msg\":\"Já matriculado\"}").build();
        }
        aluno.persist();
        return Response.status(200).entity(AlunoResponseDto.from(aluno)).build();
    }

    @Transactional
    @PATCH
    @Path("/{id}/remover")
    public Response removeDisciplinaDaGrade(@PathParam("id") int id, @QueryParam("idDisciplina") int idDisciplina)  {
        final Aluno aluno = (Aluno) Aluno
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        
        final Disciplina disciplina = (Disciplina) Disciplina
                .findByIdOptional(idDisciplina)
                .orElseThrow(NotFoundException::new);
        try {
            aluno.removeDisciplina(disciplina);
            disciplina.removeDisciplina(aluno);
        } catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity("{\"msg\":\"Não matriculado\"}").build();
        }
        aluno.persist();
        return Response.status(200).entity(AlunoResponseDto.from(aluno)).build();
    }
}
