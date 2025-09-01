/* Crie `Funcionario` com `nome` (leitura pública), `salario`
(não negativo, com `aumentarSalario`), `cargo` (validar
"Desenvolvedor", "Analista", "Gerente"). */

public class Funcionario {
    public String nome;
    private double salario;
    private String cargo;

    public Funcionario(String nome, double salario, String cargo){
        this.nome = nome;
        
        setSalario(salario);
        setCargo(cargo);
    }

    // Funçoes GETTER
    public String getNome(){
        return nome;
    }
    public double getSalario(){
        return salario;
    }
    public String getCargo(){
        return cargo;
    }

    // SETTER - Area de setter dos privates

    public void setSalario(double salario){
        if(salario > 0){
            this.salario = salario;
        }else{
            System.out.println("[ERRO] - Salario nao pode ser negativo");
        }
    }
    public void setCargo(String cargo){
        String cargoLoweCase = cargo.toLowerCase().trim();
        if(cargoLoweCase.equals("desenvolvedor") || cargoLoweCase.equals("analista") || cargoLoweCase.equals("gerente")){
            this.cargo = cargo;
        } else {
            System.out.println("Alocado como: 'indefinida'");
            this.cargo = "Indefinido";
        }
    }

    // Metodo de aumento de salario e exibir infos
    public void aumentarSalario(double porcentagem){
        if(porcentagem >= 0){
            this.salario += getSalario() * (porcentagem/ 100);
            System.out.println("Salario de " + getNome() +" aumentou para: " + String.format("%.2f", getSalario()));
        } else {
            System.out.println("Erro -  Porcentagem nao pode ser negativo, salario mantido");
        }
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + getNome() + "\nSalario: " + String.format("%.2f", getSalario()) + "\nCargo: " + getCargo()+ "\n");
    }

    public static void main(String[] args){
        Funcionario cadastroFuncionario1 = new Funcionario("Andre", 3000, "desenvolvedor");
       //Funcionario cadastroFuncionario2 = new Funcionario("Joao", 5000, "Desenvolvedor");

        cadastroFuncionario1.exibirInformacoes();

        //cadastroFuncionario1.setSalario(10);

        cadastroFuncionario1.aumentarSalario(10);

        cadastroFuncionario1.exibirInformacoes();
    }
}
