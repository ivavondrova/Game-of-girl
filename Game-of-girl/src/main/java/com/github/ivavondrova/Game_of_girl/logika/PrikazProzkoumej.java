/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;


/**
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      Jan Riha, ...
 * @author      Iva Vondrová
 * @version     LS2016/2017, 27/5/2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    private static final String NAZEV = "prozkoumej";
    private HerniPlan hPlan;
    
    
   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */  
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    /**
     * Provádí příkaz "prozkoumej". Pokud nebyl zadán žádný parametr, vrátí kompletní
     * popis aktuální lokace. Pokud byl zadán jeden parametr, pokusí se v aktuální lokaci
     * a následně v batohu najít předmět s daným názvem a vrátit jeho popis. Pokud bylo
     * zadáno více parametrů, vrátí chybové hlášení.
     *
     * @param     parametry může jako parametr obsahovat název předmětu, který chce hráč prozkoumat
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry)
    {
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nemůžeš prozkoumat více předmětů najednou";
        }
        
        Lokace aktualniLokace = hPlan.getAktualniLokace();
        if (parametry.length > 0)
        {
            // Vypis popis predmetu
            String nazevPredmetu = parametry[0];
            
            if (aktualniLokace.obsahujePredmet(nazevPredmetu))
            {
                return aktualniLokace.najdiPredmet(nazevPredmetu).getPopis();
            }

            // Pokud predmet neni v aktualni lokaci, zkusime ho najit jeste v batohu 
            if (hPlan.getBatoh().obsahujePredmet(nazevPredmetu))
            {
                return hPlan.getBatoh().najdiPredmet(nazevPredmetu).getPopis();
            }

            return "Předmět " + nazevPredmetu + " není v batohu ani v aktuální lokaci";
        }
        else
        {
            // Zobraz popis aktualni lokace
            return aktualniLokace.dlouhyPopis();
        }
    }


    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
    public String getNazev()
    {
        return NAZEV;
    }

}
