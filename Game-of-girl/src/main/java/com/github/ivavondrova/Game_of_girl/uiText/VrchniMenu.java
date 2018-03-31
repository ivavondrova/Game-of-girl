/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivavondrova.Game_of_girl.uiText;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.github.ivavondrova.Game_of_girl.logika.IHra;

/**Třída do hry zavadí ovládací lištu s novou hrou, nápovědou, koncem hry a verzí.
 */

public class VrchniMenu {
    private MenuBar menuBar;
    private IHra hra;
    private MenuItem novaHra;    

    /**Konstruktor...
     *
     * @param hra
     */
    public VrchniMenu(IHra hra) {
        this.setHra(hra);
        nastavMenu();
    }        
    
     /** Metoda se stará především o funkční logiku tlačítek, tlačítko pro novou hru je nastaveno externě v hlavní třídě.
     * 
     * @param hra
     */    
    private void nastavMenu()
      {
        menuBar = new MenuBar();

        Menu menuSoubor = new Menu("Soubor");

        novaHra = new MenuItem("Nova hra");
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem konec = new MenuItem("Konec");
        konec.setMnemonicParsing(true);

        konec.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
              {
                System.exit(0);
              }
        });    
    
        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);

        menuBar.getMenus().add(menuSoubor);
        
        Menu menuNapoveda = new Menu("Nápověda");
        MenuItem oProgramu = new MenuItem("O programu");
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události O programu
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Grafická adventura");
                alert.setHeaderText("JavaFX adventura");
                alert.setContentText("FIS VŠE v Praze, LS 2017/2018");

                alert.showAndWait();
            }
        }
        );


        MenuItem napovedaKAplikaci = new MenuItem("Nápověda k aplikaci");
        napovedaKAplikaci.setAccelerator(KeyCombination.keyCombination("F1"));
//        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent t) {
//                // obsluha události Nápověda k aplikaci
//                // sekundární okno
//                Stage stage = new Stage();
//                stage.setTitle("Nápověda k aplikaci");
//                WebView webview = new WebView();
//                webview.getEngine().load(
//                        getClass().getResource("/zdroje/napoveda.htm").toExternalForm()
//                );
//                stage.setScene(new Scene(webview, 500, 500));
//                stage.show();
//            }
//        });

        
        menuNapoveda.getItems().addAll(oProgramu, new SeparatorMenuItem(), napovedaKAplikaci) ;
        
        menuBar.getMenus().add(menuNapoveda);

    }
        
    /**
     *
     * @return
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     *
     * @return
     */
    public MenuItem getNovaHra() {
        return novaHra;
    }

	public IHra getHra() {
		return hra;
	}

	public void setHra(IHra hra) {
		this.hra = hra;
	}    
}
