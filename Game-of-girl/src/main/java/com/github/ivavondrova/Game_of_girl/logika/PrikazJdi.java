/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      Jarmila Pavlickova, Lubos Pavlicek, Jan Riha
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class PrikazJdi implements IPrikaz 
{
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    //private Batoh batoh;

   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) 
    {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param     parametry jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) 
    {
        // Pokud chybi druhe slovo (sousedni prostor).
        if (parametry.length == 0) 
        {
            return "Kam mam jit? Musis zadat jmeno vychodu.";
        }

        // Pokud je zadano více parametru na cilovy prostor.
        if (parametry.length > 1) 
        {
            return "Kam mam jit? Zadal jsi prilis mnoho cilu.";
        }
        
        String smer = parametry[0];        
        Lokace sousedniLokace = plan.getAktualniLokace().vratSousedniLokaci(smer);
        // Pokud je zadan parametr mistnosti, ktera nesousedi s aktualni mistnosti.
        if (sousedniLokace == null) {
            return "Tam se odsud jít nedá!";
        }
        // Zkoušíme přejít do sousední lokace
        else 
        {            
            // Pokud hrac chce projit do vstupni haly, ale nema mec, kterym si muze cestu vysekat.
            if (smer.equals("vstupni_hala") && !plan.getVysekano() && !plan.getBatoh().obsahujePredmet("mec"))
            {
                return "Nemas mec, nemuzes si vysekat pruchod vstupni branou, ktera je zarostla trnim. Zkus mec najit!";
            }
                     
            // Pokud se hrac snazi dostat do komnaty ve vezi, aniz by mel tajne heslo.
            if (smer.equals("komnata") && !plan.getBatoh().obsahujePredmet("listina"))
            {
                return "Néé, dveře jsou zasifrovane. Hradni pan byl ale zapomnetlivy stary muz a vse si zapisoval. Zkus nekde najit napsane heslo, ktere ti pomuze dvere otevrit.";
            }
            
            // Pokud se hrac snazi dostat do komnaty ve vezi, aniz by vypil magický lektvar
            if (smer.equals("komnata") && plan.getBatoh().obsahujePredmet("listina") && !plan.getMagickaSila())
            {
                return "Znáš heslo, ale dveře stejně nejdou otevřít... Jako kdyby byly něčím zablokované. Musíš zvýšit svojí sílu, jinak se do komnaty nedostaneš";
            }
    
            // Pokud hrac prekona vsechna uskali, ktera na nej na hrade cekaji a dostane se i s tajnym heslem ke dverim komnaty ve vezi, vyhrava.
            if (smer.equals("komnata") && plan.getBatoh().obsahujePredmet("listina") && plan.getMagickaSila())
            {
                plan.getHra().setKonecHry(true);
                return "Prekonal jsi vsechna uskali, ktera ti carokrasny ale zaroven nebezpecny hrad Lipnice nad Sazavou prichystal. \n Dostal jsi se az do komnaty ve vezi a zachranil jsi Anicku z jejiho vezeni!! \n Vyhral jsi tuto hru. \n Hip, hip hura!";
            }
           
            // Hra muze pro hrace skoncit prohrou, pokud se dostane do lesa (zde ciha vlk), hladomorny, nebo loznice (kde spi carodej).  
            if (smer.equals("les")|| smer.equals("hladomorna")|| smer.equals("loznice"))
            { 
                plan.getHra().setKonecHry(true);
                return "Umřel jsi a bez Aničky :-( ";
            }
                    
            //Pokud jsme mohli vejít do místnosti
            plan.setAktualniLokace(sousedniLokace);
            plan.setVysekano();
            return sousedniLokace.dlouhyPopis();           
        }        
        
    }
    
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

 }
