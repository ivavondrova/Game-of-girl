/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

 package com.github.ivavondrova.Game_of_girl.logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy HerniPlan, která inicializuje
 * mistnosti hry a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author      	Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class Hra implements IHra 
{
    private SeznamPrikazu platnePrikazy;    	// Obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;            	// Obsahuje informace o hře
    private boolean konecHry = false;       	// Vrací, že hra ještě neskončila
    private Batoh batoh;
    //private Lokace lokace;                	// Určuje, zda jsme již vysekali trní u vstupu do hradu

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    
    public Hra() 
    {
        novaHra();
    }
   
    
    public void novaHra() {
    	batoh = new Batoh (10, 20);                                         // Batoh (int maxPocetPredmetu, int maxVahaPredmetu)
        herniPlan = new HerniPlan(batoh,this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));        // Přidáváme platné příkazy
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(batoh,herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPij(batoh, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        this.setKonecHry(false);
    }
    
    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    
    public String vratUvitani() 
    {
        return  "Vitejte!\n" +
                "Toto je pribeh o zlem carodeji, ktery unesl krasnou divku a zacal ji veznit v mistnosti ve vezi.\n" +
                "Zachranit se ji ale vydava statecny mladik Frantisek.\n" +
                "Pomuzete mu vyhrat? Napiste 'pomoc', pokud si nevite rady, jak hrat dal.\n" +
                "\n" +
        herniPlan.getAktualniLokace().dlouhyPopis();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    
    public String vratEpilog() 
    {
        return "Dík, že jste si zahráli. :-) Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
    
     public boolean konecHry() 
     {
        return konecHry;
     }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param    radek  text, který zadal uživatel jako příkaz do hry.
     * @return   vrací se řetězec, který se má vypsat na obrazovku
     */
     
     public String zpracujPrikaz(String radek) 
     {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        
        for(int i=0 ;i<parametry.length;i++)
        {
            parametry[i]= slova[i+1];   
        }
        
        String textKVypsani=" ... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) 
        {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            
            if (herniPlan.hracVyhral())
            {
                konecHry = true;
            }
           
        }
        else 
        {
            textKVypsani= "Nevím, co tím myslíš? Tento příkaz neznám. :-( ";
        }
        return textKVypsani;
    } 
    
    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *  
     * @param    konecHry hodnota false = konec hry, true = hra pokračuje
     */
     
    void setKonecHry(boolean konecHry) 
    {
        this.konecHry = konecHry;
    }
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     * @return    odkaz na herní plán
     */
    
     public HerniPlan getHerniPlan()
     {
        return herniPlan;
     }
}


