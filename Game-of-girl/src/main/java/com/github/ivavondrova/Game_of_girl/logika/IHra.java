/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

 package com.github.ivavondrova.Game_of_girl.logika;

/**
 * Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní
 *
 * @author      	Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @author      	Iva Vondrová
 * @version     	LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */
 
public interface IHra
{
    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
	
	public void novaHra();
	
    public String vratUvitani();
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
    
    public String vratEpilog();
    
    /**
     * Vrací informaci o tom, zda hra již skončila, je jedno zda výhrou, prohrou nebo příkazem konec.
     *
     * @return    vrací true, pokud hra skončila
     */
    
     public boolean konecHry();
     
    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param     radek text, který zadal uživatel jako příkaz do hry.
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
     
     public String zpracujPrikaz(String radek);
   
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return    odkaz na herní plán
     */
     
     public HerniPlan getHerniPlan();
}
