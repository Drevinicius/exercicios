import java.time.LocalDate;

public class Livro {
    
    private String titulo;
    private String autor;
    private int paginas;
    private int anoPublicado;
    
    public Livro(String titulo, String autor, int paginas, int anoPublicado){
        this.titulo = titulo;
        this.autor = autor;
        
        setPaginas(paginas);
        setAnoPublicado(anoPublicado);
    }
    // Metodos getters
    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public int getPaginas(){
        return paginas;
    }

    public int getAnoPublicado(){
        return anoPublicado;
    }

    // Metodos setters
    public void setPaginas(int paginas){
        if(paginas > 0){
            this.paginas = paginas;
        } else {
            this.paginas = 1;
        }
    }
    
    public void setAnoPublicado(int anoPublicado){
        // Ano atual do pc
        LocalDate hoje = LocalDate.now();
        int anoAtual = hoje.getYear();
        
        if(anoPublicado < anoAtual){
            this.anoPublicado = anoPublicado; // Recebe o ano passado no parametro
        } else {
            this.anoPublicado = anoAtual; // Se for maior recebe o ano atual
        }
    }
    
    // Metodos solicitados
    public boolean ehLivroGrande(){
        if(getPaginas() > 500){
            return true;
        } else {
            return false;
        }
    }

    public void exibirInformacoes(){
        System.out.println("Titulo: " + getTitulo() + "\nAutor: " + getAutor() + "\nNum. Paginas: " + getPaginas() + "\nAno publicado: " + getAnoPublicado());
    }

    public static void main(String[] args) {
        Livro novoLivro = new Livro("Pequeno principe", "Antoine de Saint-Exupéry", 96 , 1943);

        novoLivro.exibirInformacoes();

        String tamanhoLivro = novoLivro.ehLivroGrande() ? "E grande":"E pequeno";
        System.out.println(tamanhoLivro);
    }
}
