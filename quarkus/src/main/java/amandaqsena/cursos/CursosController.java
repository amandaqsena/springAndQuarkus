package amandaqsena.cursos;

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

import amandaqsena.cursos.dto.CursoRequestDto;
import amandaqsena.cursos.dto.CursoResponseDto;
import amandaqsena.cursos.model.Curso;
import amandaqsena.cursos.model.CursoRepository;
import amandaqsena.disciplinas.model.Disciplina;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursosController {
    final private CursoRepository repositorio;

    public CursosController(CursoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GET
    public List<CursoResponseDto> buscarCursos(@QueryParam("prefix") Optional<String> prefix) {
        return prefix
                .map(s -> repositorio
                        .findComPrefixo(s)
                        .stream()
                        .map(CursoResponseDto::from)
                        .collect(Collectors.toList()))
                .orElseGet(() -> Curso
                        .findAll()
                        .list()
                        .stream()
                        .map(it -> (Curso) it)
                        .map(CursoResponseDto::from)
                        .collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    public CursoResponseDto encontrarCurso(@PathParam("id") final int id) {
        return CursoResponseDto.from((Curso) Curso
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new));
    }

    @POST
    @Transactional
    public CursoResponseDto criarCurso(
            final CursoRequestDto request) {
        final Curso curso = Curso.fromCursoRequestDto(request);
        repositorio.persist(curso);

        return CursoResponseDto.from(curso);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagarCurso(@PathParam("id") int id) {
        Curso.findByIdOptional(id)
                .orElseThrow(NotFoundException::new).delete();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public CursoResponseDto atualizarCurso(
            @PathParam("id") int id,
            final CursoRequestDto request) {
        Curso curso = (Curso) Curso.findByIdOptional(id).orElseThrow(NotFoundException::new);

        curso.setDuracao(request.getDuracao());
        curso.setDescricao(request.getDescricao());
        curso.setNome(request.getNome());
        curso.persist();
        return CursoResponseDto.from(curso);

    }

    @Transactional
    @PATCH
    @Path("/{id}/adicionar")
    public Response incluiDisciplinaNoCurso(@PathParam("id") int id, @QueryParam("idDisciplina") int idDisciplina)  {
        final Curso curso = (Curso) Curso
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        
        final Disciplina disciplina = (Disciplina) Disciplina
                .findByIdOptional(idDisciplina)
                .orElseThrow(NotFoundException::new);
        try {
            curso.incluiDisciplina(disciplina);
            disciplina.setCurso(curso);
        } catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity("{\"msg\":\"Já matriculado\"}").build();
        }
        curso.persist();
        return Response.status(200).entity(CursoResponseDto.from(curso)).build();
    }

    @Transactional
    @PATCH
    @Path("/{id}/remover")
    public Response removeDisciplinaDaGrade(@PathParam("id") int id, @QueryParam("idDisciplina") int idDisciplina)  {
        
        
        final Disciplina disciplina = (Disciplina) Disciplina
                .findByIdOptional(idDisciplina)
                .orElseThrow(NotFoundException::new);
        
        final Curso curso = disciplina.getCurso();

        try {
            curso.removeDisciplina(disciplina);
            disciplina.setCurso(null);
        } catch (Exception e) {
           return Response.status(Response.Status.CONFLICT).entity("{\"msg\":\"Não matriculado\"}").build();
        }
        curso.persist();
        return Response.status(200).entity(CursoResponseDto.from(curso)).build();
    }


}
