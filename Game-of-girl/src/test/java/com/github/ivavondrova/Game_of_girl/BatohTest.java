/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ivavondrova.Game_of_girl.logika.HerniPlan;
import com.github.ivavondrova.Game_of_girl.logika.Hra;
import com.github.ivavondrova.Game_of_girl.logika.Lokace;
import com.github.ivavondrova.Game_of_girl.logika.Predmet;

import static org.junit.Assert.*;


/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování
 * třídy Batoh.
 *
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class BatohTest
{
    private Hra hra1;
    private HerniPlan herniPlan;
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    
    @Before
    public void setUp()
    {
        hra1 = new Hra();        
        herniPlan = hra1.getHerniPlan();
        Predmet rum = new Predmet ("rum", "rum od hradni brany", true, 5,false);
       	Predmet mec = new Predmet ("mec", "Zapomenuty mec potrebny k vysekani trni u vstupni brany", true, 10, false);
		Predmet strom = new Predmet ("strom", "Majestatne se tycici lipa", false, 20, false);
        Lokace testLokace = herniPlan.getAktualniLokace();
        testLokace.vlozPredmet(rum);
        testLokace.vlozPredmet(mec);
        testLokace.vlozPredmet(strom);        
    }


    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    
    @After
    public void tearDown()
    {
    }

    /**
     * Testuje přetížení batohu tak, že postupně sbírá jednotlivé předměty a testuje, zda se do batohu vejdou, nebo nikoliv.
     */
    
    @Test
    public void testPretizeniBatohu()
    {        		
		// Do batohu vlozime rum a zkontrolujeme, ze batoh skutecne rum obsahuje.
		hra1.zpracujPrikaz("seber rum");
		assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
		
		// Vlozime batoh a chceme vlozit mec.
		hra1.zpracujPrikaz("seber rum");
		hra1.zpracujPrikaz("seber mec");
		assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
		assertTrue(herniPlan.getBatoh().obsahujePredmet("mec"));
		
		// Hrac se pokusi do batohu vlozit jeste knihu, nyni je jiz ale batoh pretizeny.
		hra1.zpracujPrikaz("seber rum");
		hra1.zpracujPrikaz("seber mec");
		hra1.zpracujPrikaz("seber kniha");
		assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
		assertTrue(herniPlan.getBatoh().obsahujePredmet("mec"));
		assertFalse(herniPlan.getBatoh().obsahujePredmet("kniha"));
    }
    
    /**
     * Testuje přenositelnosti věcí.
     */
    
    @Test
    public void testPrenositelnostiPredmetu()
    {        
        // Vezmeme do batohu věc, která je přenositelná.
        hra1.zpracujPrikaz("seber rum");
        assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
        
        // Chceme do batohu přidat věc, která není přenositelná.
        hra1.zpracujPrikaz("seber strom");
        assertFalse(herniPlan.getBatoh().obsahujePredmet("strom"));      
    }
    
    /**
     * Testuje přenesení věci z prostoru do prostoru.
     */
    
    @Test
    public void testPreneseniPredmetu()
    {
        Lokace domecek = new Lokace ("domecek", "Domecek v podhradi, ve kterem zije Frantisek", 0, 0);
        Lokace louka = new Lokace ("louka", "Louka zalita sluncem", 1, 1);
        Predmet rum = new Predmet ("rum", "rum od hradni brany", true, 5, false);
        domecek.vlozPredmet(rum);	
        domecek.setVychod(louka);
        
        assertTrue(hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("rum"));
        hra1.zpracujPrikaz("seber rum");
        assertFalse(hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("rum"));
        assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
        hra1.zpracujPrikaz("jdi louka");
        assertTrue(!hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("rum"));
        assertTrue(herniPlan.getBatoh().obsahujePredmet("rum"));
        hra1.zpracujPrikaz("poloz rum");
        assertTrue(hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("rum"));
        assertTrue(!herniPlan.getBatoh().obsahujePredmet("rum"));
    }
}
