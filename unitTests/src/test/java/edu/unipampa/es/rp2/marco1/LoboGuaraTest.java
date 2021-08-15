package edu.unipampa.es.rp2.marco1;

import java.lang.reflect.InvocationTargetException;
import static org.mockito.Mockito.when;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoboGuaraTest {
    private int loboGuaraXPosition = 0;
    private int loboGuaraYPosition = 0;
    private int ovelhaXPosition = 0;
    private int ovelhaYPosition = 0;
    private final boolean RANDOM_AGE = true;
    private Campo campo;

    @BeforeEach
    void init() {
        campo = new Campo(50, 50);
    }

    @Test
    @DisplayName("When the wolf is hunting and finds a sheep, the wolf eats it")
    void test_when_the_wolf_is_hunting_and_finds_a_sheep_the_wolf_eats_it() {
        // Arrange
        var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(loboGuaraXPosition, loboGuaraYPosition));

        var ovelha = new Ovelha(RANDOM_AGE, campo, new Localizacao(ovelhaXPosition, ++ovelhaYPosition));

        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();

        // Act
        loboGuara.caca(novosLobos);

        // Assert
        assertFalse(ovelha.estaViva());
    }

    @Test
    @DisplayName("When the wolf is hunting and not find a sheep, the wolf not eats it")
    void test_when_the_wolf_is_hunting_and_not_find_a_sheep_the_wolf_not_eats_it() {
        // Arrange
        var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(loboGuaraXPosition, loboGuaraYPosition));

        var ovelha = new Ovelha(RANDOM_AGE, campo, new Localizacao((ovelhaXPosition += 2), (ovelhaYPosition += 10)));

        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();

        // Act
        loboGuara.caca(novosLobos);

        // Assert
        assertTrue(ovelha.estaViva());
    }

    @Test
    @DisplayName("The wolf must die when it reaches max age")
    void test_the_wolf_must_die_when_it_reaches_max_age() {
        // Arrange
        var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(loboGuaraXPosition, loboGuaraYPosition));
        loboGuara.idade = loboGuara.IDADE_MAXIMA;

        // Act
        loboGuara.incrementaIdade();

        // Assert
        assertFalse(loboGuara.estaVivo());
    }

    @Test
    @DisplayName("The wolf must move to new location on the field when it changes its location")
    void test_the_wolf_must_move_to_new_location_on_the_field_when_it_changes_its_location() {
        // Arrange
        var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(loboGuaraXPosition, loboGuaraYPosition));

        var newLocalizacao = new Localizacao(++loboGuaraXPosition, ++loboGuaraYPosition);

        // Act
        loboGuara.setLocalizacao(newLocalizacao);

        // Assert
        assertNotNull(campo.getObjectAt(newLocalizacao));
    }

    @Test
    @DisplayName("The wolf move to invalid new location on the field, must throw a exception")
    void test_the_wolf_move_to_invalid_new_location_on_the_field_must_throw_a_exception() {
        // Arrange
        var loboGuara = new LoboGuara(RANDOM_AGE, campo, new Localizacao(loboGuaraXPosition, loboGuaraYPosition));

        var newLocalizacao = new Localizacao((loboGuaraXPosition += 100), (loboGuaraYPosition += 100));

        // Act and Assert
        assertThrows(IndexOutOfBoundsException.class, () -> loboGuara.setLocalizacao(newLocalizacao));
    }

    @Test
    @DisplayName("When the wolf procreate and there is adjacent empty location on the field, must have new wolfs on adjacent positions")
    void test_when_the_wolf_procreate_and_there_is_adjacent_empty_location_on_the_field_must_have_new_wolfs_on_adjacent_positions()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        // Arrange
        var localizacao = new Localizacao(loboGuaraXPosition, loboGuaraYPosition);
        var loboGuara = Mockito.spy(new LoboGuara(RANDOM_AGE, campo, localizacao));

        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();

        var methodName = "daALuz";
        Method method = LoboGuara.class.getDeclaredMethod(methodName, List.class);
        method.setAccessible(true);

        List<Localizacao> livreOld = campo.localizacoesAdjacentesLivres(localizacao);
        var qtdLocalizacaoLivresOld = livreOld.size();

        var numeroDeNascimentos = 10;
        when(loboGuara.procria()).thenReturn(numeroDeNascimentos);

        // Act
        method.invoke(loboGuara, novosLobos);

        // Assert
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        var qtdLocalizacaoLivres = livre.size();
        assertNotEquals(qtdLocalizacaoLivresOld, qtdLocalizacaoLivres);
    }

    @Test
    @DisplayName("When the wolf try procreate, but don't have any births, the adjacent positions should remain empty")
    void test_when_the_wolf_try_procreate_but_dont_have_any_births_the_adjacent_positions_should_remain_empty()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        // Arrange
        var localizacao = new Localizacao(loboGuaraXPosition, loboGuaraYPosition);
        var loboGuara = Mockito.spy(new LoboGuara(RANDOM_AGE, campo, localizacao));

        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();

        var methodName = "daALuz";
        Method method = LoboGuara.class.getDeclaredMethod(methodName, List.class);
        method.setAccessible(true);

        List<Localizacao> livreOld = campo.localizacoesAdjacentesLivres(localizacao);
        var qtdLocalizacaoLivresOld = livreOld.size();

        var numeroDeNascimentos = 0;
        when(loboGuara.procria()).thenReturn(numeroDeNascimentos);

        // Act
        method.invoke(loboGuara, novosLobos);

        // Assert
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        var qtdLocalizacaoLivres = livre.size();
        assertEquals(qtdLocalizacaoLivresOld, qtdLocalizacaoLivres);
    }
}
