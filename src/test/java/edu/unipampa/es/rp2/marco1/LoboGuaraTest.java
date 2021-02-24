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
        var loboGuara = (LoboGuara) CreateArrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        int xPositionOvelha = 0;
        int yPositionOvelha = 1;
        var ovelha = (Ovelha) CreateArrange(OVELHA, xPositionOvelha, yPositionOvelha);

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
        var loboGuara = (LoboGuara) CreateArrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        int xPositionOvelha = 2;
        int yPositionOvelha = 10;
        var ovelha = (Ovelha) CreateArrange(OVELHA, xPositionOvelha, yPositionOvelha);

        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();

        // Act
        loboGuara.caca(novosLobos);

        // Assert
        assertTrue(ovelha.estaViva());
    }

    @Test
    @DisplayName("When the wolf reaches the max age, the wolf must die")
    void test_when_the_wolf_reaches_the_max_age_the_wolf_must_die() {
        // Arrange
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) CreateArrange(LOBO_GUARA, xPositionLobo, yPositionLobo);
        loboGuara.idade = loboGuara.IDADE_MAXIMA;

        // Act
        loboGuara.incrementaIdade();

        // Assert
        assertFalse(loboGuara.estaVivo());
    }

    @Test
    @DisplayName("When the wolf to change his location, the wolf must to moving for new location on the fild.")
    void test_when_the_wolf_to_change_his_location_the_wolf_must_to_moving_for_new_location_on_the_fild() {
        // Arrange
        int xPositionLobo = 0;
        int yPositionLobo = 0;
        var loboGuara = (LoboGuara) CreateArrange(LOBO_GUARA, xPositionLobo, yPositionLobo);

        var newLocalizacao = new Localizacao(xPositionLobo++, yPositionLobo++);
        // Act
        loboGuara.setLocalizacao(newLocalizacao);

        // Assert
        assertNotNull(campo.getObjectAt(newLocalizacao));
    }

    private Animal CreateArrange(String typeEntity, int xPosition, int yPosition) {
        Localizacao localizacao = new Localizacao(xPosition, yPosition);

        if (typeEntity.equals(LOBO_GUARA)) {
            return new LoboGuara(true, campo, localizacao);
        }

        return new Ovelha(true, campo, localizacao);
    }
}
