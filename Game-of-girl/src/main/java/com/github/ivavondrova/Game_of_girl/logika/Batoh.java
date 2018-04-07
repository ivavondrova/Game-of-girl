/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.ivavondrova.Game_of_girl.logika;
import java.util.*;

/*******************************************************************************
 * Třída Batoh implementuje batoh pro použití v adventuře.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * Do batohu lze ukládat jednotlivé předměty.
 * Celková váha předmětů je v batohu omezena proměnnou MAX_VAHA_PREDMETU.
 * Celkový počet předmětůí, které jsou uložené v batohu, je také omezený - proměnnou MAX_POCET_PREDMETU.
 *
 * @author  Iva Vondrová
 * @version LS 2016/2017, 27/5/2017 (4IT101)
 * @version LS 2017/2018, 2/4/2018 (4IT115)
 */

public class Batoh extends Observable
{
    private Set <Predmet> predmety;
    final int MAX_POCET_PREDMETU;
    final int MAX_VAHA_PREDMETU;
    
     /**
     *  Konstruktor vytváří instanci třídy Batoh s parametry maxPocetPredmetu a maxVahaPredmetu
     *  
     *  @param  maxPocetPredmetu - maximální počet předmětůí v batohu
     *  @param  maxVahaPredmetu - maximální váha předmětůí v batohu
     */

    public Batoh (int maxPocetPredmetu, int maxVahaPredmetu) 
    {
        predmety = new HashSet <Predmet> ();
        MAX_POCET_PREDMETU = maxPocetPredmetu;
        MAX_VAHA_PREDMETU = maxVahaPredmetu;
    }

    /**
     *  Vrací počet předmětůí uložených v batohu
     *  
     *  @return  počet předmetů
     */

    public int pocetPredmetu () 
    {
        return predmety.size(); 
    }

    /**
     *  Přidává předmět do batohu
     *  
     *  @param  přidávaný předmět
     *  
     *  Zobrazení předmětu na grafickém panelu.
     */

    public void setPredmet (Predmet p) 
    {
        predmety.add (p); 
        setChanged();
        notifyObservers();
    }

    /**
     *  Zjišťuje celkovou váhu předmětů v batohu.
     *  
     *  @return  celková váha
     */

    public int vahaPredmetu () 
    {
        int celkovaVaha;
        celkovaVaha = 0;
        
        for (Predmet p : predmety) 
        {
            celkovaVaha = celkovaVaha + p.getVaha();
        }
        
        return celkovaVaha; 
    }

    /**
     *  Zjistí, zda lze danou předmět přidat do batohu s ohledem na celkovou nosnost
     *  
     *  @param  předmět, který se má vložit do batohu
     *  @return true = lze vložit, false = nelze již vložit
     */

    public boolean vejdeSePredmet (Predmet p) 
    {
        boolean vejdeSe = true;
        
        if (p.getVaha() + vahaPredmetu() > MAX_VAHA_PREDMETU) 
        {
            vejdeSe = false;
        }
        
        return vejdeSe;
    }

    /**
     *  Vyndá předmět z batohu
     *  
     *  @param  předmět, který se má vyndat z batohu, pokud tam není, vrací null
     *  
     *  Změna na grafickém panelu, předmět již není vidět (není v batohu).
     */

    public Predmet odeberPredmet (String jm) 
    {	
    		Predmet odebranyPredmet = null;
    		for (Iterator<Predmet> iterator = predmety.iterator(); iterator.hasNext();) {
    		    Predmet p = iterator.next();
    		    if(p.getNazev().equals(jm)) {
    		    		odebranyPredmet = p;
    		        iterator.remove();
    		    }
    		}
//    		for (Predmet p : predmety ) 
//        {
//            if (p.getNazev().equals(jm)) 
//            {
//            		odebranyPredmet = p;
//                predmety.remove(p);
//                //return p;
//            }
//        }
        setChanged();
        notifyObservers();
        return odebranyPredmet;
    }


    /**
     *  Zjišťuje, zda je předmět v batohu
     *  
     *  @param  jméno předmětu
     *  @return true = je v batohu, false = není
     */

    public boolean obsahujePredmet (String jm) 
    {
        for (Predmet p : predmety ) 
        {
            if (p.getNazev().equals(jm)) 
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     *  Zjišťuje, zda je předmět v batohu
     *  
     *  @param  jméno předmětu
     *  @return true = je v batohu, false = není
     */

    public Predmet najdiPredmet (String jm) 
    {
        for (Predmet p : predmety ) 
        {
            if (p.getNazev().equals(jm)) 
            {
                Predmet vybranyPredmet = p;                
                return vybranyPredmet;             
            }
        }
        return null;
    }

    /**
     *  Zjistí seznam předmětůí uložených v batohu
     *  
     *  @return  String obsahující předměty v batohu
     */

    public String seznamPredmetu () 
    {
        String seznam = " Seznam predmetu v batohu: \n";
        
        if (predmety.size()==0) 
        {
            seznam = "Bohuzel v batohu nic nemas. :-( \n";
        }
        else 
        {
            for (Predmet p : predmety) 
            {
                seznam = seznam + "  Predmet: " + p.getNazev() + " " + p.getPopis() + " , ktery vazi " + p.getVaha() + "\n";
            }
        }
        return seznam; 
    }
    
    public Collection<Predmet> getPredmetyVBatohu() {
    	 	return predmety;
    }
}



