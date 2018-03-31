/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.ivavondrova.Game_of_girl.main;

//import com.github.ivavondrova.Game_of_girl.logika.Hra;
//import com.github.ivavondrova.Game_of_girl.logika.IHra;
import com.github.ivavondrova.Game_of_girl.logika.*;
import com.github.ivavondrova.Game_of_girl.uiText.HomeController;
import com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import com.github.ivavondrova.Game_of_girl.logika.*;
//import com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author      	Jarmila Pavlíčková, Jan Říha
 * @author      	Iva Vondrová
 * @version 		LS 2016/2017, 27/5/2017 (4IT101)
 * @version 		LS 2017/2018, 2/4/2018 (4IT115)
 * 
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
    	
    	//		  TODO parametrické spuštění hry
//        IHra hra = new Hra();
//        TextoveRozhrani ui = new TextoveRozhrani(hra);
//        ui.hraj();
    	
//    	launch(args);
//        IHra hra = new Hra();
//        com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani ui = new com.github.ivavondrova.Game_of_girl.uiText.TextoveRozhrani(hra);
//        
//        if (args.length > 0)
//        {
//            ui.hrajZeSouboru(args[0]);
//        }
//        else
//        {
//            ui.hraj();
//        }
    }
   

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../uiText/MainWindow.fxml"));    	
    	Parent root = loader.load();

    	HomeController controller = loader.getController();
    	IHra hra = new Hra();
		controller.inicializuj(hra);
    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("Game of girl");
    	
    	primaryStage.setOnCloseRequest(e -> Platform.exit());
		
	}
}
