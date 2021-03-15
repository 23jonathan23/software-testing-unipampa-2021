package edu.unipampa.es.rp2.marco1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomizadorTest {
  
  @Test
  public void testIfGetRandomMethodReturnsARandomObject(){
    //Act
    var teste = Randomizador.getRandom();
    // Assert
    assertTrue(teste instanceof Random && teste != null);
  }

  @Test
  public void testIfResetMethodSetsTheRANDSeedTo1111(){
    Random random1111 = new Random(1111);
    Random teste = Randomizador.getRandom();
    // Act
    Randomizador.reset();
    boolean igual = true;

    for (int i = 0; i < 100; i++){
      if (random1111.nextInt() != teste.nextInt()){
        igual = false;
      }
    }
    // Assert
    assertTrue(igual);
  }
}
