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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import amandaqsena.alunos.dto.AlunoRequestDto;
import amandaqsena.alunos.dto.AlunoResponseDto;
import amandaqsena.alunos.model.Aluno;
import amandaqsena.alunos.model.AlunoRepositorio;


@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {
    
    final private AlunoRepositorio repositorio;

    public AlunosController(AlunoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @GET
    public List<AlunoResponseDto> buscarAlunos(@QueryParam("prefix") Optional<String> prefix) {

        return prefix
            .map(s-> repositorio
                .findComPrefixo(s)
                .stream()
                .map(AlunoResponseDto::from)
                .collect(Collectors.toList()))
            .orElseGet(() -> Aluno
                .findAll()
                .list()
                .stream()
                .map(it -> (Aluno) it )
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
    public AlunoResponseDto criarAluno (final AlunoRequestDto request) {
        //alunos.add(new Aluno(alunos.size(), aluno.getNome()));
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

    @PATCH
    @Path("/{id}")
    @Transactional
    public AlunoResponseDto atualizarAluno(
        @PathParam("id") int id,
        final AlunoRequestDto request
    ){
        //Optional<Aluno> aluno = alunos.stream().filter(it -> it.getId() == id).findFirst();
        Aluno aluno = Aluno.findById(id);
        if (aluno == null) {
            throw new NotFoundException();
            
        }
        //Aluno resposta = aluno.get();
        aluno.setNome(request.getNome());

        return AlunoResponseDto.from(aluno);
        
    }
}
