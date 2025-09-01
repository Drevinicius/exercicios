package exercicio_5_1;

public class Curso {
    String codigo;
    String nome;

    public Curso(String codigo, String nome){
        setCodigo(codigo);
        setNome(nome);
    }

    public void setCodigo(String codigo){
        String codigoTratado = codigo.trim();
        if(codigoTratado.equals("")){
            this.codigo = "Indefinido";
        } else {
            this.codigo = codigoTratado;
        }
    }
    public void setNome(String nome){
        String nomeTratado = nome.trim();
        if(nomeTratado.equals("")){
            this.nome = "Indefinido";
        } else {
            this.nome = nomeTratado;
        }
    }

    // Metodos GETTER
    public String getCodigo(){
        return codigo;
    }
    public String getNome(){
        return nome;
    }

    // Metodo de exibicao de detalhes
    public void exibirInformacoes(){
        System.out.println("Codigo: " + this.codigo + "\nCurso: " + this.nome);
    }
}
