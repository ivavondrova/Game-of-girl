package com.github.ivavondrova.Game_of_girl.logika;


/**
 * Třída představuje příkaz pro sebrání předmětu z aktuální lokace
 * a jeho vložení do batohu (inventáře) postavy.
 * 
 * @author      	Jan Riha, ...
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 *
*/

public class PrikazSeber implements IPrikaz
{
    private static final String NAZEV = "seber";
    private Batoh batoh;
    private HerniPlan hPlan;
    
    public PrikazSeber (Batoh batoh, HerniPlan hPlan)
    {
        this.batoh = batoh;
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro sebrání předmětu.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je předmět s tímto
     * názvem, zda je přenositelný, zda je v batohu místo a
     * následně předmět odebere z lokace a vloží ho do batohu.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    
    @Override
    
    public String proved(String... parametry)
    {
        // Pokud chybi druhe slovo (jmeno veci, kterou si chce hrac vzit do batohu).
        if (parametry.length == 0) 
        {
            return "Nevim, co mam sebrat.";
        }

        // Pokud zadám více parametrů.
         if (parametry.length > 1)
        {
            return "Tomu nerozumim, nedokazu sebrat vice veci najednou";
        }
        
        Lokace aktualniLokace = hPlan.getAktualniLokace();
        String predmetKteryChci = parametry[0];
        Predmet mujPredmet = aktualniLokace.najdiPredmet(predmetKteryChci);
        
        if (mujPredmet==null) 
        {
            return "Ses si jisty?! Tento predmet v teto mistnosti neni.";
        }
        else 
        {
            if (!mujPredmet.isPrenositelny()) 
            {
                return "Mmm... tento predmet si nemuzes vzit. Ten se neda prenaset.";
            }
           
            if (batoh.vejdeSePredmet(mujPredmet)) 
            {
                batoh.setPredmet(aktualniLokace.vyberPredmet(predmetKteryChci));
                return " Predmet \""+predmetKteryChci+"\" sis vzal do batohu s sebou. Treba se ti bude casem hodit.";
            }
            else 
            {
                return " Predmet \""+predmetKteryChci+"\" si nemuzes vzit s sebou. Batoh uz je moc tezky.";
            }
        }
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    
    public String getNazev()
    {
        return NAZEV;
    }
    
}
