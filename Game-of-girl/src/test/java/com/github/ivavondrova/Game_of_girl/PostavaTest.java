/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ivavondrova.Game_of_girl.logika.Postava;

import static org.junit.Assert.*;


/*******************************************************************************
 * Testovací třída {@code PostavaTest} slouží ke komplexnímu otestování
 * třídy {@link PostavaTest}.
 *
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class PostavaTest
{


    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
	
    @Before
    public void setUp()
    {
    }


    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    
    @After
    public void tearDown()
    {
    }



//\TT== TESTS PROPER ===========================================================

    /***************************************************************************
     * Test of the {@link #setUp()} method preparing the test fixture.
     */
    
    @Test
    public void testSetUp()
    {
    }

    /**
     * Testuje metody getJmeno a getProslov
     */
    
    @Test
    public void testPostava()
    {
        Postava babicka = new Postava ("babicka", "Frantisku, nezapomen rum od vstupni brany. Bud hlavne opatrny, nebezpeci muze cihat vsude... i v nedalekem lese.");
        Postava starik = new Postava ("starik", "Zdravim, davej si pozor, co si beres s sebou, ne vse si vzit muzes a tvuj batoh neni bezedny.");
        
        assertEquals("babicka", babicka.getJmeno());
        assertEquals("Frantisku, nezapomen rum od vstupni brany. Bud hlavne opatrny, nebezpeci muze cihat vsude... i v nedalekem lese.", babicka.getProslov());
        
        assertEquals("starik", starik.getJmeno());
        assertEquals("Zdravim, davej si pozor, co si beres s sebou, ne vse si vzit muzes a tvuj batoh neni bezedny.", starik.getProslov());
    }

}
