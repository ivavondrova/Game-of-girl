/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry. Tato třída je
 * součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 *
 * @author      Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class Lokace 
{
    private String nazev;   
    private String popis;
    private Set<Lokace> vychody;   
    private Map<String, Predmet> seznamPredmetu;
    private Map<String, Postava> seznamPostav;

    /**
     * Vytvoření lokace se zadaným popisem, např. "kuchyň", "hala", "trávník před domem"
     *
     * @param    nazev nazev lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    popis Popis lokace
     */
    public Lokace(String nazev, String popis) 
    {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamPredmetu = new HashMap<String, Predmet>();
        seznamPostav = new HashMap<String, Postava>();
    }

    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    vedlejsi lokace, která sousedi s aktualní lokací.
     *
     */
    public void setVychod(Lokace vedlejsi) 
    {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) 
    {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Lokace)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }
        // přetypujeme parametr na typ Lokace 
        Lokace druha = (Lokace) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druha.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() 
    {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    název lokace
     */
    public String getNazev() 
    {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis lokace, který může vypadat následovně: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhý popis lokace
     */
    public String dlouhyPopis() 
    {
        return "Jsi v mistnosti/lokaci " + popis + ".\n"
                + popisVychodu() + "\n"
                + nazvyPredmetu() + "\n"
                + nazvyPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje všechny předměty v lokaci, například:
     * "predmety: zidle, stul, rum ".
     *
     * @return    popis předmětů - názvů všech předmětů v lokaci
     */
//    private String seznamPredmetu()
//    {
//        String seznam = "Predmety v mistnosti: \n";
//        if (seznamPredmetu.size()==0)
//        {
//            seznam = seznam + "V mistnosti" + nazev + "zadne predmety nejsou. \n";
//        }
//        else
//        {
//            for (String nazevPredmetu : seznamPredmetu.keySet())
//            {
//                seznam = seznam + "Predmet: " + nazevPredmetu + " " + seznamPredmetu.get(nazevPredmetu).getPopis() + ", ktery vazi " + seznamPredmetu.get(nazevPredmetu).getVaha() + "\n";
//            }
//        }
//        return seznam;
//    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        
        for (Lokace sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        
        return vracenyText;
    }

    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     nazevSouseda Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Lokace vratSousedniLokaci(String nazevSouseda) {
        List<Lokace>hledaneLokace = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return hledaneLokace.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Lokace> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Přidá předmět do lokace.
     * 
     * @param    predmet předmět, který má být přidán do lokace
     */
    public void vlozPredmet(Predmet predmet)
    {
        seznamPredmetu.put(predmet.getNazev(), predmet);
    }

    /**
     * Zjistí, jestli je věc v prostoru
     * 
     * @param   vec
     * return   true, když obsahuje, jinak false
     */
    public boolean obsahujePredmet (String nazevPredmetu)
    {
        return seznamPredmetu.containsKey(nazevPredmetu);
    }
    
    /** 
     * Metoda najde předměty v prostoru
     * 
     * @return  vrátí nalezené věci v prostoru
     */
    
    public Predmet najdiPredmet (String jmeno)
    {
        return seznamPredmetu.get(jmeno);
    }
    
    /**
     * Metoda vyberPredmet vybere předmět z prostoru a smaži ji z prostoru.
     * 
     * @ param      jméno předmětu
     * @ return     objekt Předmět, pokud předmět v místnosti není (nebo je nepřenositelný) - null
     */
    
    public Predmet vyberPredmet (String nazevPredmetu)
    {
        Predmet nalezenyPredmet;
        if (seznamPredmetu.containsKey(nazevPredmetu))
        {
            nalezenyPredmet = seznamPredmetu.get(nazevPredmetu);
            if (nalezenyPredmet.isPrenositelny())
            {
                seznamPredmetu.remove(nazevPredmetu);
                return nalezenyPredmet;
            }
            return null;
        }
        return null;
    }
    
    /** 
     * Vypíše názvy věcí
     */
    
    public String nazvyPredmetu()
    {
        String nazvy = "Predmety: ";
        for (String nazevPredmetu : seznamPredmetu.keySet())
        {
            nazvy += nazevPredmetu + " ";
        }
        return nazvy;
    }
    
    /**
     * Metoda vloží postavu do místnosti.
     */
    public void vlozPostavu(Postava postava)
    {
        seznamPostav.put(postava.getJmeno(), postava);
    }
    
    /**
     * Metoda najde postavu
     */
    public Postava najdiPostavu(String jmeno)
    {
        return seznamPostav.get(jmeno);
    }
    
    /**
     * Vypíše názvy postav v prostoru
     */
    public String nazvyPostav()
    {
        String jmena = "Postavy: ";
        for (String jmenoPostavy : seznamPostav.keySet())
        {
            jmena += jmenoPostavy + " ";
        }
        return jmena;
    }
    
}

