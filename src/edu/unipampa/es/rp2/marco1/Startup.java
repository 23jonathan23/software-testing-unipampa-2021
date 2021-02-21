package edu.unipampa.es.rp2.marco1;

import java.util.Scanner;

public class Startup {
  public static void main(String[] args) {
    Scanner entradas = new Scanner(System.in);
    Simulador simulador = new Simulador();

    System.out.println("#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");
    System.out.println("   Simulador de Lobos-Guará e Ovelhas   ");
    System.out.println("        Grupo 6 de RP II 2020.02        ");

    menu(simulador, entradas);
  }

  public static void menu(Simulador simulador, Scanner entradas) {
    int comando = 0;

    System.out.println("#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");
    System.out.println("1 - Executar uma etapa");
    System.out.println("2 - Executar uma quantidade x de etapas");
    System.out.println("3 - Executar uma simulação automática");
    System.out.println("4 - Reiniciar o simulador");
    System.out.println("5 - Sair");
    System.out.println("#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");

    String entrada = entradas.nextLine();

    if (podeSerConvertidoParaInteiro(entrada)) {
      comando = Integer.parseInt(entrada);

      switch (comando) {
        case 1:
          simulador.simulacaoUmaEtapa();
          menu(simulador, entradas);
          break;

        case 2:
          System.out.println("Digite a quantidade de etapas a ser executada:");
          String quantidadeDeEtapas = entradas.nextLine();

          if (podeSerConvertidoParaInteiro(quantidadeDeEtapas)) {
            simulador.simulacao(Integer.parseInt(quantidadeDeEtapas));
            menu(simulador, entradas);
          } else {
            System.err.println("Entrada inválida.");
            menu(simulador, entradas);
          }
          break;

        case 3:
          simulador.executaLongaSimulacao();
          menu(simulador, entradas);
          break;

        case 4:
          simulador.redefine();
          menu(simulador, entradas);
          break;

        case 5:
          System.exit(0);

        default:
          System.err.println("Entrada inválida.");
          menu(simulador, entradas);
      }
    } else {
      System.err.println("Entrada inválida.");
      menu(simulador, entradas);
    }
  }

  public static boolean podeSerConvertidoParaInteiro(String entrada) {
    try {
      Integer.parseInt(entrada);
      return true;
    } catch (Exception erro) {
      return false;
    }
  }
}
