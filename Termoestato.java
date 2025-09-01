/* Crie `Termostato` com `temperaturaAtual` (privada, só
leitura), `temperaturaDesejada` (entre 18.0°C e 28.0°C). */

class Termoestato{
    private double temperaturaAtual;
    private double temperaturaDesejada;

    public Termoestato(double temperaturaAtual, double temperaturaDesejada){
        this.temperaturaAtual = temperaturaAtual;

        setTemperaturaDesejada(temperaturaDesejada);
    }

    public double getTemperaturaAtual(){
        return temperaturaAtual;
    }

    public double getTemperaturaDesejada(){
        return temperaturaDesejada;
    }

    public void setTemperaturaDesejada(double temperaturaDesejada){
        if(temperaturaDesejada >= 18.0 && temperaturaDesejada <= 28.0){
            this.temperaturaDesejada = temperaturaDesejada;
        } else {
            System.out.println("Valor invalido, matendo 25.00°");
            this.temperaturaDesejada = 25;
        }
    }

    public void simularAlteracaoTemp(double delta){
        this.temperaturaAtual = delta;
    }

    public String obterStatus(){
        if(temperaturaAtual > temperaturaDesejada){
            return "Temperatura maior que a desejaqda";
        } else if (temperaturaAtual < temperaturaDesejada){
            return "Temperatura menor que a desejaqda";
        }else{
            return "Ambas sao iguais";
        }
    }

    public static void main(String[] args) {
        Termoestato temperatura = new Termoestato(30, 20);

        String temp = temperatura.obterStatus();
        System.out.println(temp);

        temperatura.simularAlteracaoTemp(20);

        String temp2 = temperatura.obterStatus();
        System.out.println(temp2);
    }
}