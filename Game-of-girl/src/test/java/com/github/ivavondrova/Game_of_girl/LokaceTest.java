package com.github.ivavondrova.Game_of_girl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ivavondrova.Game_of_girl.logika.Lokace;
import com.github.ivavondrova.Game_of_girl.logika.Predmet;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování
 * třídy Lokace
 *
 * @author      	Jarmila Pavlickova, Jan Riha
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class LokaceTest
{
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

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    
    @Test
    public  void testLzeProjit() {      
        Lokace domecek = new Lokace ("domecek", "Domecek v podhradi, ve kterem zije Frantisek", 0, 0);
        Lokace louka = new Lokace ("louka", "Louka zalita sluncem", 4, 5);
        //Lokace les = new Lokace ("les", "Strasidelny les, ve kterem ciha nebezpeci - vlk, ktery te sni");
        //Lokace hladomorna = new Lokace ("hladomorna", "Hladomorna, zde zemres hlady a bez Anicky.");
        Lokace knihovna = new Lokace ("knihovna", "Knihovna, mistnost s moudrymi knihami a poznamkami", 1, 1);
        //Lokace vstupniHala = new Lokace ("hala", "Vstupni hala");
        //Lokace kuchyn = new Lokace ("kuchyn", "Kuchyn, kde se muzes po dlouhe ceste obcerstvit... ale dej si pozor, co pijes!!");
        //Lokace pracovna = new Lokace ("pracovna", "Pracovna s dulezitymi dokumenty");
        Lokace chodbaVPrvnimPatre = new Lokace ("chodba", "Chodba v prvnim patre", 2, 2);
        //Lokace loznice = new Lokace ("loznice", "Loznice, ve ktere spi carodej... ejhle, prave jsi ho vzbudil a on te promenil v krkavce...");
        //Lokace komnata = new Lokace ("komnata", "komnata ve vezi, ve ktere byla veznena krasna Anicka... ale ty jsi ji prave zachranil");
            
        domecek.setVychod(louka);
        louka.setVychod(domecek);
        assertEquals(louka, domecek.vratSousedniLokaci("louka"));
        assertEquals(null, louka.vratSousedniLokaci("hladomorna"));
        
        chodbaVPrvnimPatre.setVychod(knihovna);
        knihovna.setVychod(chodbaVPrvnimPatre);
        assertEquals(knihovna, chodbaVPrvnimPatre.vratSousedniLokaci("knihovna"));
        assertEquals(null, knihovna.vratSousedniLokaci("komnata"));
    }
    
    @Test
    public void testVeci()
    {
        Lokace domecek = new Lokace ("domecek", "Domecek v podhradi, ve kterem zije Frantisek", 1, 1);
        Predmet rum = new Predmet ("rum", "rum od hradni brany", true, 5, false);
        domecek.vlozPredmet(rum);
        assertTrue(domecek.obsahujePredmet("rum"));
        assertSame(rum, domecek.najdiPredmet("rum"));
        assertSame(rum, domecek.vyberPredmet("rum"));
        assertFalse(domecek.obsahujePredmet("mec"));
        assertNull(domecek.najdiPredmet("mec"));
    }
}