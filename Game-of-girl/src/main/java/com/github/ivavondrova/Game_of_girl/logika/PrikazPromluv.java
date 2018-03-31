/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl.logika;

/*******************************************************************************
 * Třída PrikazPromluv je hlavní třídou projektu,
 * který představuje jednoduchou textovou hru.
 *
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class PrikazPromluv implements IPrikaz
{
    private static final String NAZEV = "promluv";
    private HerniPlan plan;
    
    /** 
     * Kontruktor
     * @param   plan
     */
    
    public PrikazPromluv(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    /**
     * Provádí příkaz "promluv". 
     * Díky tomu některřé postavy promluví a mohou hráči poradit.
     * 
     * @param   jmeno postavy
     * @return  proslov postavy
     */
    
    public String proved(String... parametry)
    {
        // Pokud hráč nezadá parametr
        if (parametry.length == 0)
        {
            return "Nezadal jsi jmeno postavy, se kterou chces mluvit.";
        }
        
        String jmeno = parametry[0];
        Lokace aktualniLokace = plan.getAktualniLokace();
        Postava postava = aktualniLokace.najdiPostavu(jmeno);
        
        // Pokud se postava nenachází v dané lokaci
        if (postava == null)
        { 
            return "Jejda, tato postava tady neni :-( ";
        }
        
        // Pokud hrac zada jednu z nadefinovanych postav...
        if (postava.getJmeno().contains("babicka"))
        {
            return postava.getProslov();
        }
        
        if (postava.getJmeno().contains("starik"))
        {
            return postava.getProslov();
        }
        
        return "Tak tuhle postavu neznam. :-(";
    }
    
    /**
    * Metoda vrací název příkazu
    * @ return     nazev prikazu
    */
        
    @Override
    public String getNazev()
    {
            return NAZEV;
    }

}
