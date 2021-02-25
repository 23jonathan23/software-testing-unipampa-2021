package edu.unipampa.es.rp2.marco1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

public class LoboGuaraTest {
    private final String LOBO_GUARA = "LOBO_GUARA";
    private final String OVELHA = "OVELHA";
    private Campo campo;

    @BeforeEach
    void init() {
        campo = new Campo(50, 50);
    }

    @Test
    @DisplayName("When the wolf is hunting and finds a sheep, the wolf eats it")
    void test_when_the_wolf_is_hunting_and_finds_a_sheep_the_wolf_eats_it() {
        // Arrange
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) Arrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        int xPositionOvelha = 0;
        int yPositionOvelha = 1;
        var ovelha = (Ovelha) Arrange(OVELHA, xPositionOvelha, yPositionOvelha);

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
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) Arrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        int xPositionOvelha = 2;
        int yPositionOvelha = 10;
        var ovelha = (Ovelha) Arrange(OVELHA, xPositionOvelha, yPositionOvelha);

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
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) Arrange(LOBO_GUARA, xPositionLobo, yPositionLobo);
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
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) Arrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        var newLocalizacao = new Localizacao(xPositionLobo++, yPositionLobo++);

        // Act
        loboGuara.setLocalizacao(newLocalizacao);

        // Assert
        assertNotNull(campo.getObjectAt(newLocalizacao));
    }

    private Animal Arrange(String typeEntity, int xPosition, int yPosition) {
        if (typeEntity.equals(LOBO_GUARA)) {
            return new LoboGuara(true, campo, new Localizacao(xPosition, yPosition));
        }

        return new Ovelha(true, campo, new Localizacao(xPosition, yPosition));
    }
}
