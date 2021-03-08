package edu.unipampa.es.rp2.marco1;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CampoEstatisticaTest {
  private Campo campo;
  private final boolean RANDOM_AGE = true;

  @BeforeEach
  void init() {
    Setup();
  }

  @Test
  @DisplayName("When the method getPopulationDetails is called and counters is valid, the method generateCounters don´t must be called")
  void when_the_method_getPopulationDetails_is_called_and_counters_is_valid_the_method_generateCounters_dont_must_be_called() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());

    // Act
    campoEstatistica.getPopulationDetails(campo);

    // Assert
    verify(campoEstatistica, times(0)).redefine();
  }

  @Test
  @DisplayName("When the method getPopulationDetails is called and counters isn't valid, the method generateCounters must be called")
  void when_the_method_getPopulationDetails_is_called_and_counters_isnt_valid_the_method_generateCounters_must_be_called() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());
    campoEstatistica.redefine();

    // Act
    campoEstatistica.getPopulationDetails(campo);

    // Assert
    verify(campoEstatistica, times(2)).redefine();
  }

  @Test
  @DisplayName("When the method getPopulationDetails is called and the field contains occupied positions, the method generateCounters should return the number occupied positions")
  void when_the_method_getPopulationDetails_is_called_and_the_field_contains_occupied_positions_the_method_generateCounters_should_return_the_number_occupied_positions() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());
    campoEstatistica.redefine();

    var expectedPopulation = 2;

    // Act
    var population = campoEstatistica.getPopulationDetails(campo).replaceAll("[\\D]", "").split("");
    var populationQtd = Integer.parseInt(population[0]) + Integer.parseInt(population[1]);

    // Assert
    assertEquals(expectedPopulation, populationQtd);
  }

  @Test
  @DisplayName("When the method incrementCounter is called, the method getPopulationDetails should return the exactly number incremented")
  void when_the_method_incrementCounter_is_called_the_method_getPopulationDetails_should_return_the_exactly_number_incremented() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());

    var expectedPopulation = 1;

    // Act
    campoEstatistica.incrementaContador(LoboGuara.class);

    // Assert
    var population = campoEstatistica.getPopulationDetails(campo).replaceAll("[\\D]", "").split("");
    var populationQtd = Integer.parseInt(population[0]);
    assertEquals(expectedPopulation, populationQtd);
  }

  @Test
  @DisplayName("When the method checkVisibility is called and counters is valid, the method generateCounters don´t must be called")
  void when_the_method_checkVisibility_is_called_and_counters_is_valid_the_method_generateCounters_dont_must_be_called() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());

    // Act
    campoEstatistica.checarViabilidade(campo);

    // Assert
    verify(campoEstatistica, times(0)).redefine();
  }

  @Test
  @DisplayName("When the method checkVisibility is called and counters isn't valid, the method generateCounters must be called")
  void when_the_method_checkVisibility_is_called_and_counters_isnt_valid_the_method_generateCounters_must_be_called() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());
    campoEstatistica.redefine();

    // Act
    campoEstatistica.checarViabilidade(campo);

    // Assert
    verify(campoEstatistica, times(2)).redefine();
  }

  @Test
  @DisplayName("When the method checkVisibility is called and the field contains more than one occupied positions, its should return true")
  void when_the_method_checkVisibility_is_called_and_the_field_contains_more_than_one_occupied_positions_its_should_return_true() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());
    campoEstatistica.redefine();

    // Act and Assert
    assertTrue(campoEstatistica.checarViabilidade(campo));
  }

  @Test
  @DisplayName("When the method checkVisibility is called and the field doesn't contains more than one occupied positions, its should return false")
  void when_the_method_checkVisibility_is_called_and_the_field_doesnt_contains_more_than_one_occupied_positions_its_should_return_true() {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());
    campoEstatistica.incrementaContador(LoboGuara.class);

    // Act and Assert
    assertFalse(campoEstatistica.checarViabilidade(campo));
  }

  @Test
  @DisplayName("When the method generateCounters is called and the field contains occupied positions, the method incrementCounters should be called")
  void when_the_method_generateCounters_is_called_and_the_field_contains_occupied_positions_the_method_incrementCounters_should_be_called()
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    // Arange
    var campoEstatistica = Mockito.spy(new CampoEstatistica());

    Method method = CampoEstatistica.class.getDeclaredMethod("geraContadores", Campo.class);
    method.setAccessible(true);

    // Act
    method.invoke(campoEstatistica, campo);

    // Assert
    verify(campoEstatistica, times(2)).incrementaContador(Mockito.any());
  }

  @Test
  @DisplayName("When the method generateCounters is called and the field doesn't contains occupied positions, the method incrementCounters not should be called")
  void when_the_method_generateCounters_is_called_and_the_field_doesnt_contains_occupied_positions_the_method_incrementCounters_not_should_be_called()
      throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    // Arange
    var campoMock = Mockito.spy(new Campo(50, 50));

    var campoEstatistica = Mockito.spy(new CampoEstatistica());

    Method method = CampoEstatistica.class.getDeclaredMethod("geraContadores", Campo.class);
    method.setAccessible(true);

    // Act
    method.invoke(campoEstatistica, campoMock);

    // Assert
    verify(campoEstatistica, times(0)).incrementaContador(Mockito.any());
  }

  private void Setup() {
    campo = new Campo(50, 50);
    var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(0, 0));
    var ovelha = new Ovelha(RANDOM_AGE, campo, new Localizacao(0, 1));
  }
}
