 /* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;

import java.util.Observable;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author      	Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class HerniPlan extends Observable
{
    private static final String NAZEV_VITEZNE_LOKACE = "komnata";
    private Lokace aktualniLokace;
    private Batoh batoh;    
    private Hra hra;
    private boolean vysekano = false; 
    private boolean magicka_sila = false;
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    
    public HerniPlan(Batoh batoh, Hra hra) 
    {
        zalozLokaceHry();
        this.batoh = batoh;
        this.hra = hra;
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * 
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    
    private void zalozLokaceHry() 
    {
        // Vytvářejí se jednotlivé lokace, věci a postavy v nich
        Lokace domecek = new Lokace ("domecek", "Domecek v podhradi, ve kterem zije Frantisek", 275, 170);            	// Vytvoření místnosti (lokace)
        Predmet rum = new Predmet ("rum", "Stary vyzraly rum", true, 5, false);                            			// Vytvoření předmětu (jeho název, popis, určení, zda je přenosný a jeho váha, zda je otraveny)
        domecek.vlozPredmet(rum);                                                                          			// Vložení předmětu do dané místnosti
        Postava babicka = new Postava ("babicka", "Frantisku, bud hlavne opatrny, nebezpeci muze cihat vsude... i v nedalekem lese.");
        domecek.vlozPostavu(babicka);
       
        Lokace louka = new Lokace ("louka", "Louka zalita sluncem", 175, 170);
        Predmet strom = new Predmet ("strom", "Majestatne se tycici lipa", false, 20, false);
        Predmet motyl = new Predmet ("motyl", "Poletujici motyl Emanuel", false, 2, false);
        Predmet mec = new Predmet ("mec", "Zapomenuty mec potrebny k vysekani trni u vstupni brany", true, 10, false);
        louka.vlozPredmet(strom);
        louka.vlozPredmet(motyl);
        louka.vlozPredmet(mec);
        Postava starik = new Postava ("starik", "Zdravim, davej si pozor, co si beres s sebou, ne vse si vzit muzes a tvuj batoh neni bezedny.");
        louka.vlozPostavu(starik);

        Lokace les = new Lokace ("les", "Strasidelny les, ve kterem ciha nebezpeci - vlk, ktery te sni", 65, 170);
        
        Lokace hladomorna = new Lokace ("hladomorna", "Hladomorna, zde zemres hlady a bez Anicky.", -25, 110);
        
        Lokace knihovna = new Lokace ("knihovna", "Knihovna, mistnost s moudrymi knihami a poznamkami", 275, 110);
        Predmet kniha = new Predmet ("kniha", "Stara zaprasena tezka kniha", true, 20, false);
        Predmet globus = new Predmet ("globus", "Velky staroveky globus", true, 20, false);
        knihovna.vlozPredmet(kniha);
        knihovna.vlozPredmet(globus);
    
        Lokace vstupniHala = new Lokace ("hala", "Vstupni hala", 175, 110);
        
        Lokace kuchyn = new Lokace ("kuchyn", "Kuchyn, kde se muzes po dlouhe ceste obcerstvit... ale dej si pozor, co pijes!!", 65, 110);
        Predmet cervenyNapoj = new Predmet ("cerveny_napoj", "Carodej namichal jakysi lektvar... urcite bude otraveny...", true, 6, true);
        Predmet bezbarvyNapoj = new Predmet ("bezbarvy_napoj", "Zazracny napoj, ktery ti doda silu", true, 6, false);
        kuchyn.vlozPredmet(cervenyNapoj);
        kuchyn.vlozPredmet(bezbarvyNapoj);

        Lokace pracovna = new Lokace ("pracovna", "Pracovna s dulezitymi dokumenty", 275, 50);
        Predmet stul = new Predmet ("stul", "Stary zapraseny stul", false, 20, false);
        Predmet listina = new Predmet ("listina", "Listina s tajnym heslem od dveri komnaty ve vezi", true, 8, false);
        pracovna.vlozPredmet(stul);
        pracovna.vlozPredmet(listina);
        
        Lokace chodbaVPrvnimPatre = new Lokace ("chodba", "Chodba v prvnim patre", 175, 50);
        
        Lokace loznice = new Lokace ("loznice", "Loznice, ve ktere spi carodej... ejhle, prave jsi ho vzbudil a on te promenil v krkavce...", 65, 50);
        
        Lokace komnata = new Lokace (NAZEV_VITEZNE_LOKACE, "komnata ve vezi, ve ktere byla veznena krasna Anicka... ale ty jsi ji prave zachranil", 175, 0);
       
        // Přiřazují se průchody mezi lokacemi (sousedící lokace)
        domecek.setVychod(louka);
        
        louka.setVychod(domecek);
        louka.setVychod(les);
        louka.setVychod(vstupniHala);
        
        // les.setVychod(louka);
        
        // hladomorna.setVychod(kuchyn);
        
        knihovna.setVychod(vstupniHala);
        
        vstupniHala.setVychod(louka);
        vstupniHala.setVychod(knihovna);
        vstupniHala.setVychod(kuchyn);
        vstupniHala.setVychod(chodbaVPrvnimPatre);
        
        kuchyn.setVychod(vstupniHala);
        kuchyn.setVychod(hladomorna);
        
        pracovna.setVychod(chodbaVPrvnimPatre);
        
        chodbaVPrvnimPatre.setVychod(pracovna);
        chodbaVPrvnimPatre.setVychod(loznice);
        chodbaVPrvnimPatre.setVychod(komnata);  
        chodbaVPrvnimPatre.setVychod(vstupniHala);
        
        // loznice.setVychod(chodbaVPrvnimPatre);
        
        // komnata.setVychod(chodbaVPrvnimPatre);

        // Hra zacina v domečku
        aktualniLokace = domecek;     
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() 
    {
        return aktualniLokace;
    }
    
    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     * 
     * Zobrazení lokace a možných východů na grafickém panelu.
     * 
     */
    public void setAktualniLokace(Lokace lokace) 
    {
       aktualniLokace = lokace;
       this.setChanged();
       this.notifyObservers();
    }
    
    
     /**
     *  Vrací objekt batoh.
     *  
     *  @return  odkaz na batoh
     */

    public Batoh getBatoh()
    {
        return batoh;
    }

    /**
     *  Vrací, zda-li je již vysekáno trní
     *  
     *  @return  true = trní vysekáno, false = trní doposud nevysekáno
     */

    public boolean getVysekano()
    {
        return vysekano;
    }

    /**
     *  Nastavuje, že trní u vchodu je již vysekáno.
     *  
     *  @return  
     */

    public void setVysekano()
    {
        vysekano = true;
    }
    
   
    /**
     *  Vrací, zda-li již hráč vypil magický nápoj
     *  
     *  @return  true = vypil, false = doposud vypil
     */

    public boolean getMagickaSila()
    {
        return magicka_sila;
    }

    /**
     *  Nastavuje, že hráč již vypil magický nápoj
     *  
     *  @return  
     */

    public void setMagickaSila()
    {
        magicka_sila = true;
    }
    
    /**
     * Metoda vrací informaci, zda hráč vyhrál (zda dorazil do lokace komnata).
     * 
     * @return    true, pokud hráč vyhrál; jinak false
     */
    
    public boolean hracVyhral()
    {
        return aktualniLokace.getNazev().equals(NAZEV_VITEZNE_LOKACE);
    }
    
    /**
     * Metoda vrací odkaz na instanci Hry.
     *
     * @return    instance Hry
     */
    
    public Hra getHra() 
    {
        return hra;
    }

}
