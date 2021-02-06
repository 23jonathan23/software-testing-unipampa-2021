
import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {
        
        Scanner e = new Scanner(System.in);
        Simulador simulador = new Simulador();
        menu(simulador, e);


    }
    
    public static void menu(Simulador simulador, Scanner e) {

        int comando = -1;

        System.out.println("-------------------------------------");
        System.out.println("1 - Executa Uma Etapa");
        System.out.println("2 - Executa um número x de etapas");
        System.out.println("3 - Executa a simulação automática");
        System.out.println("4 - Reinicia o simulador");
        System.out.println("5 - Sair");
        System.out.println("-------------------------------------");

        comando = e.nextInt();

        switch (comando) {

            case 1:
                simulador.simulacaoUmaEtapa();
                menu(simulador, e);
                
                break;

            case 2:
                int numEtapas = 1;

                System.out.println("Digite o número de etapas:");
                numEtapas = e.nextInt();
                simulador.simulacao(numEtapas);
                menu(simulador, e);

                break;

            case 3:
                simulador.executaLongaSimulacao();
                menu(simulador, e);
                
                break;

            case 4:
                simulador.redefine();
                menu(simulador, e);
                
                break;

            case 5:
                System.exit(0);
            default:
                System.out.println("Entrada inválida");
                menu(simulador, e);
                
        }
    }    
}
