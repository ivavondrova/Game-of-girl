/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author      Jarmila Pavlickova, Luboš Pavlíček, Jan Říha
 * @author      Iva Vondrová
 * @version     LS2016/2017, 27/5/2017
 */
public class PrikazNapoveda implements IPrikaz 
{
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

   /**
    * Konstruktor třídy
    *
    * @param    platnePrikazy seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) 
    {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String proved(String... parametry) 
    {
        return  "Tvym ukolem je zachranit krasnou divku Anicku, kterou hlida zly carodej v komnate ve vezi.\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
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
