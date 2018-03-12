package com.github.ivavondrova.Game_of_girl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ivavondrova.Game_of_girl.logika.Hra;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author      Jarmila Pavlíčková, Jan Říha
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class HraTest 
{
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() 
    {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() 
    {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testVitezstvi() 
    {
        assertEquals("domecek", hra1.getHerniPlan().getAktualniLokace().getNazev());
            
        hra1.zpracujPrikaz("jdi louka");
        assertEquals("louka", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("seber mec");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi vstupni_hala");
        assertEquals("vstupni_hala", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi knihovna");
        assertEquals("knihovna", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("poloz mec");
        hra1.zpracujPrikaz("seber recept");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi vstupni_hala");
        assertEquals("vstupni_hala", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi kuchyn");
        assertEquals("kuchyn", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("seber bezbarvy_napoj");
        hra1.zpracujPrikaz("pij bezbarvy_napoj");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi vstupni_hala");
        assertEquals("vstupni_hala", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi chodba_v_prvnim_patre");
        assertEquals("chodba_v_prvnim_patre", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi pracovna");
        assertEquals("pracovna", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("poloz recept");
        hra1.zpracujPrikaz("poloz napoj");
        hra1.zpracujPrikaz("seber listina");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi chodba_v_prvnim_patre");
        assertEquals("chodba_v_prvnim_patre", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("jdi komnata");
        assertEquals(true, hra1.konecHry());
    }

}
