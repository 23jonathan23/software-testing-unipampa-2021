package edu.unipampa.es.rp2.marco1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.Class;
import java.lang.reflect.Field;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.List;

public class CampoTest{

  Campo c;

  @BeforeEach
  public void setup(){
    c = new Campo(2,2);
    System.out.println("deu");
  }

  @Test
  public void testIfTheFieldIsCreatedWithTheRightDimension(){
    // Arrange
    int profundidade = 40;
    int largura = 30;
    var campo = new Campo(profundidade, largura);

    // Act and Assert
    assertThrows(IndexOutOfBoundsException.class, () -> campo.getObjectAt(profundidade, largura));
  }

  @Test
  public void testIfLugarPutsAnObjectInTheProperSpot() throws NoSuchFieldException, IllegalAccessException{
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);

    // Act
    campo1.lugar(ovelha, localizacao);

    Field campoField = Campo.class.getDeclaredField("campo");
    campoField.setAccessible(true);

    Object[][] valorCampo = (Object[][]) campoField.get(campo1);

    // Assert
    assertTrue(valorCampo[10][10] instanceof Ovelha);
  }

  @Test
  public void testIfSecontLugarPutsanObjectInTheProperSpot() throws NoSuchFieldException, IllegalAccessException{
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);

    // Act
    campo1.lugar(ovelha, 10, 10);

    Field campoField = Campo.class.getDeclaredField("campo");
    campoField.setAccessible(true);

    Object[][] valorCampo = (Object[][]) campoField.get(campo1);

    // Assert
    assertTrue(valorCampo[10][10] instanceof Ovelha);
  }

  @Test
  public void testIfGetObjectAtMethodReturnsTheRightObject(){
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);

    campo1.lugar(ovelha, localizacao);

    // Act
    Object obj = campo1.getObjectAt(localizacao);

    // Assert
    assertEquals(obj, ovelha);
  }

  @Test
  public void testIfSecondGetObjectMethodReturnsTheRightObject(){
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);

    campo1.lugar(ovelha, localizacao);

    // Act
    Object obj = campo1.getObjectAt(10, 10);

    // Assert
    assertEquals(obj, ovelha);
  }

  @Test
  public void testIfLimpaMethodSetsAllIndexesOfCampoArrayToNull() throws NoSuchFieldException, IllegalAccessException{
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(20, 20);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(30, 30);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);

    // Act
    campo1.limpa();

    Field campoField = Campo.class.getDeclaredField("campo");
    campoField.setAccessible(true);

    Object[][] valorCampo = (Object[][]) campoField.get(campo1);

    boolean vazio = true;

    for (int i = 0; i < 50; i++){
      for (int j = 0; j < 50; j++){
        if (valorCampo[i][j] != null){
          vazio = false;
        }
      }
    }
    
    // Assert
    assertTrue(vazio);
  }

  @Test
  public void testIfLocalizacaoAdjacenteRandomicaReturnTheFirstIndexOfAdjacent(){
    var campo = new Campo(50, 50);
    var localizacao = new Localizacao(10, 10);
    List<Localizacao> listLocalizacao = campo.localizacoesAdjacentes(localizacao);

    // Act
    var local = campo.localizacaoAdjacenteRandomica(localizacao);
    int linhaDiferenca = localizacao.getLinha() - local.getLinha();
    int colunaDiferenca = localizacao.getLinha() - local.getColuna();
    int soma = linhaDiferenca + colunaDiferenca;
    // Assert
    assertTrue(soma >= -2 && soma <= 2);
  }

  @Test
  public void testIfLocalizacaoAdjacenteLivreReturnsTheRightLocation(){
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(9, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(9, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(10, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 10);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(10, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);

    // Act
    Localizacao local = campo1.localizacaoAdjacenteLivre(new Localizacao(10, 10));
    
    // Assert
    assertTrue(local.getLinha() == 9 && local.getColuna() == 10);
  }

  @Test
  public void testTheLocalizacoesAdjacentesLivresMustReturnAListOfLengthOne(){
    var campo1 = new Campo(50, 50);
    var localizacao = new Localizacao(10,10);
    var ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(9, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(9, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(10, 11);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(11, 10);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);
    localizacao = new Localizacao(10, 9);
    ovelha = new Ovelha(true, campo1, localizacao);
    campo1.lugar(ovelha, localizacao);

    // Act
    List<Localizacao> listLocal = campo1.localizacoesAdjacentesLivres(new Localizacao(10, 10));
    
    // Assert
    assertEquals(listLocal.size(), 1);
  }

  @Test
  public void testWhenTheEntryArgumentIsNullTheLocalizacoesAdjacentesReturnsAnEmptyList(){
    var campo = new Campo(50, 50);
    Localizacao local = null;
    
    // Act and Assert
    assertThrows(AssertionError.class, () -> campo.localizacoesAdjacentes(local));
  }

  @Test
  public void testWhenTheLocationIsNotInTheEdgeOfTheFieldItReturnAListOfLenghtEight(){
    var campo = new Campo(50, 50);
    var localizacao = new Localizacao(10, 10);

    // Act
    List<Localizacao> listLocal = campo.localizacoesAdjacentes(localizacao);
    
    // Assert
    assertEquals(listLocal.size(), 8);
  }

  @Test
  public void testWhenTheLocalizationIsInTheEdgeOfTheFieldLocalizacoesAdjacentesReturnsAListOfLenghtFive(){
    var campo = new Campo(50, 50);
    var localizacao = new Localizacao(49, 10);

    // Act
    List<Localizacao> listLocal = campo.localizacoesAdjacentes(localizacao);
    
    // Assert
    assertEquals(listLocal.size(), 5);
  }

  @Test
  public void testWhenTheLocationIsInTheCornerOfTheFieldLocalizacoesAdjacentesReturnsAListOfLenghtThree(){
    var campo = new Campo(50, 50);
    var localizacao = new Localizacao(0, 0);

    // Act
    List<Localizacao> listLocal = campo.localizacoesAdjacentes(localizacao);
    
    // Assert
    assertEquals(listLocal.size(), 3);
  }
}