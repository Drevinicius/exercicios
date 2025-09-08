import java.util.Scanner;

public class ConversorTemperatura {
    private double celsius, fahren;

    public ConversorTemperatura(double temp){
        setTempCelsius(temp);
        setTempFarh(temp);
    }
    public double getCelsius(){
        return celsius;
    }
    public double getFahren(){
        return fahren;
    }

    public void setTempCelsius(double temp){
        if(temp < (-273.15)){
            System.out.println("[erro] - Numero abaixo de 0 absoluto");
        }else{
            this.celsius = temp;
        }
    }

    public void setTempFarh(double temp){
        if(temp < (-459.67)){
            System.out.println("[erro] - Numero abaixo de 0 absoluto");
        }else{
            this.fahren = temp;
        }
    }

    public void celsiusToFahrenheint(){
        double novoFar = (getCelsius() * 9/5) + 32;
        System.out.println(getCelsius() + " C° -> " + String.format("%.2f", novoFar) + " F°");
    }
    public void fahrenheintToCelsius(){
        double novoCels = (getFahren() - 32) * 5/9;
        System.out.println(getFahren() + " F° -> " + String.format("%.2f", novoCels) + " C°");
    }
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConversorTemperatura tempe = new ConversorTemperatura(0);

        System.out.print("C: Celsius -> Fahrenheint\nF: Fahrenheint -> Celsius\nDigite o sistema deseja utilizar: ");
        String opcao = teclado.nextLine().toUpperCase();

        switch (opcao) {
            case "C":
                System.out.print("\nTemperatura em grau celsius: ");
                double tempC = teclado.nextDouble();
                tempe.setTempCelsius(tempC);
                tempe.celsiusToFahrenheint();
                break;
        
            case "F":
                System.out.print("\nTemperatura em grau fahrenheint: ");
                double tempF = teclado.nextDouble();
                tempe.setTempFarh(tempF);
                tempe.fahrenheintToCelsius();
                break;
            default:
                System.out.println("Opçao invalida");
                break;
        }
        

        teclado.close();
    }
}
