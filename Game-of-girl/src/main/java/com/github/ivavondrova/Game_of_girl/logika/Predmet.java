/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;

/**
 * Třída Predmet představuje jednotlivé předměty (věci), které je možné ve hře najít.
 * 
 * Předmět může být přenositelný nebo nepřenositelný. 
 * Přenositelné předměty je možné vložit do batohu postavy a přenášet mezi lokacemi. 
 * Nepřenositelné předměty není možné z lokace odnést.
 *
 * @author      	Jan Riha, ...
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class Predmet
{
    private String nazev;
    private String popis;
    private boolean prenositelny;
    private int vaha;
    private boolean otraveny;
    
    /**
     * Vytvoří nový předmět se zadaným názvem, popisem a přenositelností.
     * 
     * @param   nazev           název předmětu (jedno slovo)
     * @param   popis           popis předmětu (může se jednat o text libovolné délky)
     * @param   prenositelny    true, pokud má být předmět přenositelný; jinak false
     * @param   vaha            váha předmětu    
     * @param   otraveny        true, predmet je otraveny, jinak false
     */
    
    public Predmet(String nazev, String popis, boolean prenositelny, int vaha, boolean otraveny)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        this.vaha = vaha;
        this.otraveny = otraveny;
    }
    
    /**
     * Vrátí název předmětu.
     * 
     * @returns    název předmětu
     */
    
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * Vrátí popis předmětu.
     * 
     * @returns    popis předmětu
     */
    
    public String getPopis()
    {
        return popis;
    }
    
    /**
     * Vrátí příznak, zda je předmět přenostilený, nebo ne.
     * 
     * @returns    true, pokud je předmět přenositelný; jinak false
     */
    
    public boolean isPrenositelny()
    {
        return prenositelny;
    }
    
    /**
     *  Vrací váhu věci
     *  
     *  @return  váha
     */

    public int getVaha() 
    {
        return vaha;
    }

    /**
     * Nastaví nový popis předmětu.
     * 
     * @param    popis nový popispředmětu
     */
    
    public void setPopis(String popis)
    {
        this.popis = popis;
    }
    
    /**
     * Nastaví přenositelnost předmětu.
     * 
     * @param    prenositelny   true, pokud má být předmět přenositelný; jinak false
     */
    
    public void setPrenositelny(boolean prenositelny)
    {
        this.prenositelny = prenositelny;
    }
    
     /**
     * Metoda rozhodne, zda je nápoj otrávený
     */
    
    public boolean isOtraveny()
    {
        return otraveny;
    }
    
    /**
     * Nastaví, zda je (či není) nápoj otrávený
     */
    
    public void setOtraveny (boolean otraveny)
    {
        this.otraveny = otraveny;
    }
    
    
    public int hashCode()
    {
        return nazev.hashCode();
    }
    
    @Override
    public String toString()
    {
        return nazev;
    }

}
