/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl.logika;

/*******************************************************************************
 * Třída PrikazPoloz implementuje pro hru příkaz polož.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class PrikazPoloz implements IPrikaz 
{
    private static final String NAZEV = "poloz";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */

    public PrikazPoloz(HerniPlan plan) 
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "poloz". Zkouší položit daný předmět.
     *  Pokud předmět v batohu existuje, položí ho. Pokud pořadovaný předmět
     *  v batohu není, vypíše chybové hlášení.
     *
     *  @param parametry - předmět, kerý se má odebrat z batohu
     *  @return zpráva, kterou vypíše hra hráči
     */ 
    @Override

    public String proved(String... parametry) 
    {
        // Pokud chybi druhe slovo (jmeno veci, kterou chce hrac vyndat z batohu).
        if (parametry.length == 0) 
        {
            return "Nevim, co mam polozit.";
        }

        Lokace aktualniLokace = plan.getAktualniLokace();
        String predmetKteryNechci = parametry[0];
        
        if (predmetKteryNechci==null) 
        {
            return "Tento predmet v batohu neni.";
        }
        
        else 
        {	
        		Predmet p;
        		if((p = plan.getBatoh().odeberPredmet(predmetKteryNechci)) != null)
        		{	
        			aktualniLokace.vlozPredmet(p);
        			return " Predmet \""+predmetKteryNechci+"\" byl vyndan z batohu.";
        		}
        		else {
        			return "Zadany predmet se v batohu nenachazi!";
        		}
        			
        }
     
    }    

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override

    public String getNazev() 
    {
        return NAZEV;
    }

}
