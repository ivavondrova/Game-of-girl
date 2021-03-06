/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;


/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      	Jarmila Pavlickova, Jan Riha
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 *  
 */

public class PrikazKonec implements IPrikaz 
{
    private static final String NAZEV = "konec";
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param    hra odkaz na hru, která má být příkazem konec ukončena
     */    
    
    public PrikazKonec(Hra hra) 
    {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String proved(String... parametry) 
    {
        if (parametry.length > 0) 
        {
            return "Ukoncit co? Proc jsi zadal druhe slovo?";
        }
        else 
        {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
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
