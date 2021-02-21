package main.source;

import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {

        Scanner e = new Scanner(System.in);
        Simulador simulador = new Simulador();

        System.out.println("------ SIMULADOR DE LOBOS-GUARA E OVELHAS ------");
        System.out.println("---- VERSÃO REFATORADA PELO GRUPO 6 DE RP II ----");
        menu(simulador, e);

    }

    public static void menu(Simulador simulador, Scanner e) {

        int comando = 0;

        System.out.println("-------------------------------------");
        System.out.println("1 - Executa Uma Etapa");
        System.out.println("2 - Executa um número x de etapas");
        System.out.println("3 - Executa a simulação automática");
        System.out.println("4 - Reinicia o simulador");
        System.out.println("5 - Sair");
        System.out.println("-------------------------------------");

        String entrada = e.nextLine();

        if (isInt(entrada)) {

            comando = Integer.parseInt(entrada);

            switch (comando) {

                case 1:
                    simulador.simulacaoUmaEtapa();
                    menu(simulador, e);

                    break;

                case 2:

                    System.out.println("Digite o número de etapas:");
                    String numEtapas = e.nextLine();

                    if (isInt(numEtapas)) {
                        simulador.simulacao(Integer.parseInt(numEtapas));
                        menu(simulador, e);
                    } else {
                        System.err.println("Entrada inválida.");
                        menu(simulador, e);
                    }

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
                    System.err.println("Entrada inválida");
                    menu(simulador, e);

            }
        } else {

            System.err.println("Entrada inválida");
            menu(simulador, e);
        }

    }

    public static boolean isInt(String v) {
        try {
            Integer.parseInt(v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
