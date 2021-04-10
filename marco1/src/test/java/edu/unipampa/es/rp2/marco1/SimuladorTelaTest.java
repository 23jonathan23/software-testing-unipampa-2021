package edu.unipampa.es.rp2.marco1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SimuladorTelaTest {

    SimuladorTela tela;
    CampoEstatistica estatisticas;
    Campo campo;

    @BeforeEach
    public void setup() {
        tela = new SimuladorTela(50, 50);

    }

    @Test
    public void testVerificaVisibilidadeTela() {

        assertTrue(tela.isVisible());
    }

    @Test
    public void testMostraStatus_campo_vazio() {
        //Arrange
        campo = new Campo(50, 50);
        estatisticas = new CampoEstatistica();
        int statusEsperado = 0;
        campo.limpa();
        //Act
        int statusObtido = estatisticas.getPopulationDetails(campo).length();

        //Assert
        assertEquals(statusEsperado, statusObtido);
    }

    @Test
    public void testMostraStatus_1_lobo_1_ovelha() {
        //Arrange
        campo = new Campo(50, 50);

        Localizacao localizacaoLobo = new Localizacao(10, 10);
        LoboGuara lobo = new LoboGuara(true, campo, localizacaoLobo);

        Localizacao localizacaoOvelha = new Localizacao(20, 20);
        Ovelha ovelha = new Ovelha(true, campo, localizacaoOvelha);

        estatisticas = new CampoEstatistica();
        estatisticas.incrementaContador(lobo.getClass());
        estatisticas.incrementaContador(ovelha.getClass());
        String statusEsperado;
        System.out.println(ovelha.getClass());
        campo.limpa();

        //Act
        String statusObtido = estatisticas.getPopulationDetails(campo);
        if (statusObtido.startsWith("O")) {
            statusEsperado = "Ovelha: 1 LoboGuara: 1 ";
        } else {
            statusEsperado = "LoboGuara: 1 Ovelha: 1 ";
        }

        //Assert
        assertEquals(statusEsperado, statusObtido);
    }

    @Test
    public void testMostraStatus_10_lobos_10_ovelhas() {
        //Arrange
        campo = new Campo(50, 50);
        estatisticas = new CampoEstatistica();
        tela.setVisible(false);

        for (int i = 0; i < 10; i++) {

            Localizacao localizacaoOvelha = new Localizacao(i + 10, i + 10);
            Ovelha ovelha = new Ovelha(true, campo, localizacaoOvelha);

            Localizacao localizacaoLobo = new Localizacao(i, i);
            LoboGuara lobo = new LoboGuara(true, campo, localizacaoLobo);

            estatisticas.incrementaContador(ovelha.getClass());
            estatisticas.incrementaContador(lobo.getClass());
        }
        campo.limpa();
        String statusEsperado = "LoboGuara: 10 Ovelha: 10 ";

        //Act
        tela.mostraStatus(0, campo);
        String statusObtido = estatisticas.getPopulationDetails(campo);
        
        if (statusObtido.startsWith("O")) {
            statusEsperado = "Ovelha: 10 LoboGuara: 10 ";
        } else {
            statusEsperado = "LoboGuara: 10 Ovelha: 10 ";
        }
        //Assert
        assertEquals(statusEsperado, statusObtido);
    }

    @Test
    public void testChecarViabilidade() {

        // Arrange
        campo = new Campo(50, 50);
        estatisticas = new CampoEstatistica();
        boolean esperado = estatisticas.checarViabilidade(campo);

        //Act
        boolean obtido = tela.checarViabilidade(campo);

        //Assert
        assertEquals(obtido, esperado);
    }

}
