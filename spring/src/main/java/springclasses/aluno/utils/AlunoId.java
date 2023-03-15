package springclasses.aluno.utils;

public class AlunoId {
    static int id = 0;

    public static int incrementId() {
        return ++id;
    }
}
