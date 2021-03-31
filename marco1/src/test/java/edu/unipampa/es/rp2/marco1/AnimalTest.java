package edu.unipampa.es.rp2.marco1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimalTest {

  Localizacao localizacao;
  Campo campo;
  @BeforeEach
  public void setup(){
    localizacao = new Localizacao(10, 10);
    campo = new Campo(50, 50);
  }

  @Test
  public void testIfTheAnimalClassSetsVivoToTrue(){
    // Act
    var ovelha = new Ovelha(true, campo, localizacao);
    // Assert
    assertTrue(ovelha.vivo);
  }
  
  @Test
  public void testIfTheAnimalClassSetsIdadeToZero(){
    // Act
    var lobo = new LoboGuara(false, campo, localizacao);
    // Assert
    assertEquals(0, lobo.idade);
  }
}
