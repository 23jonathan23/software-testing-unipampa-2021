package edu.unipampa.es.rp2.marco1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LocalizacaoTest {
    
    public LocalizacaoTest() {
    }

    @Test
    public void testEquals_isLocalizacao() {
        //Arrange
        var loc = new Localizacao(10, 10);
        boolean result;
        
        //Act
        result = loc.equals(loc);
        
        //Assert
        assertTrue(result);
    }
        @Test
    public void testEquals_isNotLocalizacao() {
        //Arrange
        var loc = new Localizacao(10, 10);
        var str = new String();
        boolean result;
        
        //Act
        result = loc.equals(str);
        
        //Assert
        assertFalse(result);
    }

    @Test
    public void testToString() {
        //Arrange
        var loc = new Localizacao(10, 10);
        var esperado = "10,10";
        
        //Act
        var result = loc.toString();
        
        //Assert
        assertEquals(esperado, result);
    }

    @Test
    public void testHashCode() {
        //Arrange        
        var loc = new Localizacao(10, 10);
        var esperado = 655370;
        
        //Act
        var result = loc.hashCode();
        
        //Assert
        assertEquals(esperado, result);
    }
    
}
