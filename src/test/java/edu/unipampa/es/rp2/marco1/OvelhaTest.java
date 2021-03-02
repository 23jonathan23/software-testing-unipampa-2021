package edu.unipampa.es.rp2.marco1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

public class OvelhaTest {
    Campo campo;

    @BeforeEach
    public void setup() {
        campo = new Campo(50, 50);
    }

    @Test
    public void testWhenTheSheepDiesItClearsTheCampo() {
        Localizacao localizacao = new Localizacao(10, 10);
        Ovelha ovelha = new Ovelha(true, campo, localizacao);
        ovelha.setMorte();
        assertNull(campo.getObjectAt(localizacao));
    }

    @Test
    public void testWhenTheSheepReachesMaxAgeItDies() {
        Localizacao localizacao = new Localizacao(10, 10);
        Ovelha ovelha = new Ovelha(true, campo, localizacao);
        ovelha.idade = ovelha.IDADE_MAXIMA;
        ovelha.incrementaIdade();
        assertFalse(ovelha.estaViva());
    }

    @Test
    public void testWhenProcriaIsCalledItReturnsAValidNumber() {
        Localizacao localizacao = new Localizacao(10, 10);
        Ovelha ovelha = new Ovelha(true, campo, localizacao);
        int n = ovelha.procria();
        assertTrue(n >= 0 && n <= ovelha.TAMANHO_MAXIMO_NINHADA);
    }

    @Test
    public void testTheSheepNeverGivesBirthToMoreThanTheSpaceAvailableInCampo()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException {
        Localizacao localizacao = new Localizacao(10, 0);
        Ovelha ovelha = new Ovelha(true, campo, localizacao);
        int n = ovelha.procria();
        List<Localizacao> espacoLivre = campo.localizacoesAdjacentesLivres(localizacao);
        List<Ovelha> arrayOvelha = new ArrayList<Ovelha>();
        Method method = Ovelha.class.getDeclaredMethod("daALuz", List.class);
        method.setAccessible(true);
        try {
            method.invoke(ovelha, arrayOvelha);
        } catch (Exception e) {
        }
        ;

        assertTrue(arrayOvelha.size() <= espacoLivre.size());
    }

    @Test
    public void testWhenTheSetLocationMethodIsCalledTheSheepChangesItsLocation() {
        Localizacao localizacao = new Localizacao(10, 10);
        Ovelha ovelha = new Ovelha(true, campo, localizacao);
        Localizacao novaLocalizacao = new Localizacao(10, 11);
        ovelha.setLocalizacao(novaLocalizacao);
        assertNotNull(campo.getObjectAt(novaLocalizacao));
    }
}
