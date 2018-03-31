/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.uiText;

import java.io.*;
import java.util.Scanner;
//import com.github.ivavondrova.Game_of_girl.logika.IHra;

/**
 * Class TextoveRozhrani
 *
 * Toto je třída uživatelského rozhraní aplikace Adventura. Třída vytváří instanci třídy Hra,
 * která představuje logiku aplikace. Čte vstup zadaný uživatelem a předává tento řetězec logice
 * a vypisuje odpověď logiky na konzoli.
 *
 * @author       Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @author       Iva Vondrová
 * @version      LS 2016/2017, 27/5/2017 (4IT101)
 * @version		LS 2017/2018, 2/4/2018 (4IT115)
 */

public class TextoveRozhrani 
{
    private com.github.ivavondrova.Game_of_girl.logika.IHra hra;

    /**
     * Vytváří hru.
     */
    
    public TextoveRozhrani(com.github.ivavondrova.Game_of_girl.logika.IHra hra) 
    {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry pro hraní z konzole. Vypíše úvodní text a pak opakuje
     * čtení a zpracování příkazu od hráče do konce hry (dokud metoda konecHry()
     * z logiky nevrátí hodnotu true). Nakonec vypíše text epilogu.
     */
    
    public void hraj() 
    {
        System.out.println(hra.vratUvitani());
        //System.out.println();
        
        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) 
        {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
            System.out.println();
        }

        System.out.println(hra.vratEpilog());
    }
    
    /**
     * Hlavní metoda hry pro přehrávání scénáře ze souboru. Vypíše úvodní text a pak
     * opakuje čtení a zpracování příkazu ze souboru, dokud nepřečte celý soubor nebo
     * do konce hry (dokud metoda konecHry() z logiky nevrátí hodnotu true). Nakonec
     * vypíše text epilogu.
     * 
     * @param    nazevSouboru název textového souboru se scénářem hry
     */
    
    public void hrajZeSouboru(String nazevSouboru) 
    {
        System.out.println("Nacitam prikazy ze souboru '" + nazevSouboru + "':\n");
        
        try (BufferedReader ctec = new BufferedReader(new FileReader(nazevSouboru)))
        {
            System.out.println(hra.vratUvitani());
            System.out.println();

            String radek = ctec.readLine();
            while (radek != null && !hra.konecHry())
            {
                System.out.println("* " + radek + " *");
                System.out.println(hra.zpracujPrikaz(radek));
                System.out.println();

                radek = ctec.readLine();
            }
            
            System.out.println(hra.vratEpilog());
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Soubor '" + nazevSouboru + "' nelze otevřít\n" + e);
        }
        catch (IOException e)
        {
            System.out.println("Doslo k chybě při čtení ze souboru '" + nazevSouboru + "'\n" + e);
        }
    }

    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return    vrací přečtený příkaz jako instanci třídy String
     */
    
    private String prectiString() 
    {
        try (Scanner scanner = new Scanner(System.in))
        {
        		System.out.print("> ");
        		return scanner.nextLine();
        }
    }
}
