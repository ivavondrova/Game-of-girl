/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.main;

import com.github.ivavondrova.Game_of_girl.logika.Hra;
import com.github.ivavondrova.Game_of_girl.logika.IHra;

//import com.github.ivavondrova.Game_of_girl.logika.*;
//import com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author      Jarmila Pavlíčková, Jan Říha
 * @author      Iva Vondrová
 * @version     LS 2016/2017, 27/5/2017
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani ui = new com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani(hra);
        
        if (args.length > 0)
        {
            ui.hrajZeSouboru(args[0]);
        }
        else
        {
            ui.hraj();
        }
    }
    
    private Start() {}
}
