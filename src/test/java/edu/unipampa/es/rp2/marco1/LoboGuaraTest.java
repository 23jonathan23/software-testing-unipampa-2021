package edu.unipampa.es.rp2.marco1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

public class LoboGuaraTest {
    private int loboGuaraXPosition = 0;
    private int loboGuaraYPosition = 0;
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

        var ovelha = new Ovelha(RANDOM_AGE, campo, new Localizacao(0, 1));

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

        var ovelha = new Ovelha(RANDOM_AGE, campo, new Localizacao(2, 10));

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

        var newLocalizacao = new Localizacao(loboGuaraXPosition++, loboGuaraYPosition++);

        // Act
        loboGuara.setLocalizacao(newLocalizacao);

        // Assert
        assertNotNull(campo.getObjectAt(newLocalizacao));
    }
}
