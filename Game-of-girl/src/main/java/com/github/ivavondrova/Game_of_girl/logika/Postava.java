/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl.logika;





/*******************************************************************************
 * Třída {Postava} je hlavní třídou projektu,
 * který představuje jednoduchou textovou hru.
 *
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class Postava
{
    private String jmeno;
    private String proslov;
    
    /**
     * Konstruktor nastavi jmeno postavy a to, co nam postava chce rict.
     */
    public Postava (String jmeno, String proslov)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }
    
    /**
     * Metoda vrací jméno postavy.
     *
     * @return  String jmeno postavy
     */
    public String getJmeno()
    {
        return jmeno;
    }
    
    /**
     * Metoda vrací proslov postavy.
     * 
     * @return  String proslov postavy
     */
    public String getProslov()
    {
        return proslov;
    }
    
}
