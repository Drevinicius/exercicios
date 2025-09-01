package exercicio_5_1;

public class Aluno {
    private int matricula;
    private String nomeAluno;
    private Curso cursoMatriculado;
    
    public Aluno(int matricula, String nomeAluno, Curso cursoMatriculado){
        this.matricula = matricula;
        this.nomeAluno = nomeAluno;
        setCursoMatriculado(cursoMatriculado); // Outro metodo de atribuicao
    }

    // Metodos GETTER
    public int getMatricula(){
        return matricula;
    }
    public String getNomeAluno(){
        return nomeAluno;
    }
    public Curso getCursoMatriculado(){
        return cursoMatriculado;
    }

    // Metodo SETTER
    public void setCursoMatriculado(Curso curso){
        this.cursoMatriculado = curso;
    }

    public void exibirInformacoesCompleto(){
        System.out.println("Aluno: " + getNomeAluno()
         + "\nMatricula: " + getMatricula()); // Exibindo todas as infos ate da classe curso
         // Verifica se o aluno ta cadastrado no curso
         if(getCursoMatriculado().getCodigo().equals("Indefinido") || getCursoMatriculado().getNome().equals("Indefinido")){
            System.out.println("[ERRO] - Aluno cadastrado em nenhum curso");
         } else{
            getCursoMatriculado().exibirInformacoes();
         }
    }
    
    public static void main(String[] args) {
        Curso novoCurso = new Curso("ESW02", "Eng. de Software");   
        Aluno novoAluno = new Aluno(123, "Andre", novoCurso);

        novoAluno.exibirInformacoesCompleto(); // Exibir as informacoes do aluno e qual curso esta matriculado
       //System.out.println(novoAluno.getCursoMatriculado().codigo);
    }
}