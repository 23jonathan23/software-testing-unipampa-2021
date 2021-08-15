/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.es.rp2.marco1;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 *
 * @author Rafael
 */
public class StartupTest {

    
    public StartupTest() {

    }

    @Test
    public void testMain() {
        
    }
    
    @Test
    public void testPodeSerConvertidoParaInteiro_inteiro()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
        var startup = new Startup();
        var entrada = "1";
        Method method = Startup.class.getDeclaredMethod("podeSerConvertidoParaInteiro", entrada.getClass());
        method.setAccessible(true);
       var result = (boolean) method.invoke(startup, entrada);

        assertTrue(result);
        
    }

    @Test
    public void testPodeSerConvertidoParaInteiro_nao_inteiro()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{        
        var startup = new Startup();
        var entrada = "a";
        Method method = Startup.class.getDeclaredMethod("podeSerConvertidoParaInteiro", entrada.getClass());
        method.setAccessible(true);
       var result = (boolean) method.invoke(startup, entrada);
        
        assertFalse(result);
        
    }
    
    @Test
    public void testMenu_1()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        System.out.println("método testMenu......................");
        var startup = new Startup();
        Simulador simulador = new Simulador();
        
        var scan = new Scanner("1");
        Method method = Startup.class.getDeclaredMethod("menu", simulador.getClass(), scan.getClass());
        method.setAccessible(true);
        
        assertThrows(InvocationTargetException.class, () ->
            method.invoke(startup, simulador, scan));     
       
    } 
    
    @Test
    public void testMenu_2()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        System.out.println("método testMenu......................");
        var startup = new Startup();
        Simulador simulador = new Simulador();
        
        var scan = new Scanner("2");
        Method method = Startup.class.getDeclaredMethod("menu", simulador.getClass(), scan.getClass());
        method.setAccessible(true);
        
        assertThrows(InvocationTargetException.class, () ->
            method.invoke(startup, simulador, scan));     
       
    }
    
    @Test
    public void testMenu_3()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        System.out.println("método testMenu......................");
        var startup = new Startup();
        Simulador simulador = new Simulador();
        
        var scan = new Scanner("3");
        Method method = Startup.class.getDeclaredMethod("menu", simulador.getClass(), scan.getClass());
        method.setAccessible(true);
        
        assertThrows(InvocationTargetException.class, () ->
            method.invoke(startup, simulador, scan));     
       
    }
    
    @Test
    public void testMenu_4()
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        System.out.println("método testMenu......................");
        var startup = new Startup();
        Simulador simulador = new Simulador();
        
        var scan = new Scanner("4");
        Method method = Startup.class.getDeclaredMethod("menu", simulador.getClass(), scan.getClass());
        method.setAccessible(true);
        
        assertThrows(InvocationTargetException.class, () ->
            method.invoke(startup, simulador, scan));     
       
    }
    
}
