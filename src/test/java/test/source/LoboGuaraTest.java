package test.source;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import main.source.Campo;
import main.source.LoboGuara;
import main.source.Localizacao;
import main.source.Ovelha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

public class LoboGuaraTest {
    private static final int VALOR_FOME_OVELHA = 7;
    private Campo campo;
    
    @BeforeEach
    void init() {
        campo = new Campo(50, 50);
    }
    
    @Test
    @DisplayName("when the wolf hunts a sheep and found, he eat the she.")
    void test_when_the_wolf_hunts_a_sheep_and_found_he_eat_the_she() 
            throws NoSuchMethodException, SecurityException, 
            IllegalAccessException, IllegalArgumentException, 
            InvocationTargetException, NoSuchFieldException {
        //Arrange
        Localizacao localizacaoLobo = new Localizacao(0, 0);
        LoboGuara loboGuara = new LoboGuara(true, campo, localizacaoLobo);
        
        Localizacao localizacaoOvelha = new Localizacao(0, 1);
        Ovelha ovelha = new Ovelha(true, campo, localizacaoOvelha);
        
        Field field = LoboGuara.class.getDeclaredField("nivelFome");
        field.setAccessible(true);
        
        List<LoboGuara> novosLobos = new ArrayList<LoboGuara>();
        
        //Act
        loboGuara.caca(novosLobos);
        int nivelFomeNow = field.getInt(loboGuara);

        //Assert
        assertAll("validation",
            () -> {
                assertEquals(VALOR_FOME_OVELHA, nivelFomeNow);
                assertNotEquals(true, ovelha.estaViva());
            }
        );
    }
}

//Method method = LoboGuara.class.getDeclaredMethod("procria");
//method.setAccessible(true);
//int nascimentos = (int) method.invoke(loboGuara);
