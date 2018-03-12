/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl.logika;

/*******************************************************************************
 * Třída PrikazPij implementuje pro hru prikaz pij.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class PrikazPij implements IPrikaz
{
    public static final String NAZEV = "pij";
    public HerniPlan plan;
    public Batoh batoh;
    
    /**
     * Konstruktor třídy
     * 
     * @param - herní plán, ve kterém se bude ve hře pít, batoh, ve kterém bude mít hráč nápoje
     */
    public PrikazPij(Batoh batoh, HerniPlan plan)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     * Provádí příkaz pij. Zkouší pít věci, které jsou už v batohu.
     */
    
    @Override
    public String proved(String... parametry)
    {
        // Pokud hrac nezada zadne parametry.
        if (parametry.length == 0)
        {
            return "Co mam vypit? Musis mi to rict...";
        }
        
        // Pokud chce hrac vypit vec, kterou nema v batohu.
        String nazevPredmetu = parametry[0];
        if (plan.getBatoh().obsahujePredmet(nazevPredmetu))
        {
            if(nazevPredmetu.equals("bezbarvy_napoj"))
            {
                return bezbarvy_napoj();
            }
            if(nazevPredmetu.equals("cerveny_napoj"))
            {
                return cerveny_napoj();
            }
            return "Vypít se dá jen nápoj!";
        }
        else
        {
            return "Jejda, nemas lektvar.";
        }
    }
    
    /**
     * Metoda zajišťuje, že se lektvar dá vypít pouze jednou.
     */
    public String bezbarvy_napoj()
    {
        plan.getBatoh().vyberPredmet("bezbarvy_napoj");
        plan.setMagickaSila();
        plan.getHra().setKonecHry(false);
        return "Vyborne, vypil jsi zazracny lekvat a ziskal zazracnou silu.";
    }
    
    /**
     * Metoda zajišťuje, že v případě vypití otráveného nápoje hra končí.
     */
    public String cerveny_napoj()
    {
        plan.getBatoh().vyberPredmet("cerveny_napoj");            
        plan.getHra().setKonecHry(true);
        return "Nedaval sis pozor a vypil jsi otraveny lektvar, ktery uvaril zly carodej.";
    }
            
    /**
     * Metoda vraci nazev prikazu.
     * 
     * @return - nazev prikazu
     */    
    
    public String getNazev()
    {
        return NAZEV;
    }
        
}
