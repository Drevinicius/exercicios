package exercicio_5_3;

public class Cliente {
    private String nomeCliente;
    private String email;

    public Cliente(String nomeCliente, String email){
        this.nomeCliente = nomeCliente;
        this.email = email;
    }

    public String getNome(){
        return nomeCliente;
    }
    public String getEmail(){
        return email;
    }
}
