package com.github.ivavondrova.Game_of_girl.uiText;

import java.util.Observable;
import java.util.Observer;

import com.github.ivavondrova.Game_of_girl.logika.IHra;
import com.github.ivavondrova.Game_of_girl.logika.Lokace;
import com.github.ivavondrova.Game_of_girl.logika.Postava;
import com.github.ivavondrova.Game_of_girl.logika.Predmet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.MenuBar;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author	Filip Vencovsky
 * @author	Iva Vondrová
 * 
 * @version	LS 2017/2018, 2/4/2018 (4IT115)
 *
 */

public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Lokace> seznamMistnosti;
	@FXML private ListView<Predmet> seznamVeci;
	@FXML private ListView<Predmet> seznamVeciVBatohu;
	@FXML private ListView<Postava> seznamPostav;
	@FXML private ImageView uzivatel;
	@FXML private AnchorPane mapa;
	@FXML private MenuBar menu;
	@FXML private MenuItem newGame;
	@FXML private MenuItem endGame;
	private IHra hra;
	
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
		
	}
	
	public void konecHry() {
		Platform.exit();
	}
	
	public void novaHra() {
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamPostav.getItems().clear();
		seznamVeciVBatohu.getItems().clear();
		hra.novaHra();
		this.inicializuj(hra);
	}
	
	public void oHre() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game of Girl");
        alert.setHeaderText("Game of Girl \nAdventura - textová hra rozšířena o grafickou podobu");
        alert.setContentText("Iva Vondrová \nLS 2016/2017 - 4IT101 a LS 2017/2018 - 4IT115 \nFIS VŠE v Praze");
        alert.showAndWait();
	}
	
	public void napoveda() {
		Stage stage = new Stage();
        stage.setTitle("Nápověda k aplikaci");
        WebView webview = new WebView();
        webview.getEngine().load(
                getClass().getResource("/napoveda.html").toExternalForm()
        );
        stage.setScene(new Scene(webview, 500, 500));
        stage.show();
	}
	
	/**
	 * Metoda, která inicializuje výchozí stav hry.
	 * 
	 * @param hra
	 */
	
	public void inicializuj(IHra hra) 
	{
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmety());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPostavy());
		seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyVBatohu());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
		mapa.getStyleClass().add("test");
		textVstup.setDisable(false);
		odesli.setDisable(false);
		
	}

	/**
	 *  Metoda, která aktualizuje stav hry - tj. aktualizace grafických panelů, poloha hráče na mapě apod.
	 */
	
	@Override
	public void update(Observable o, Object arg) 
	{
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamVeci.getItems().clear();
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmety());
		seznamPostav.getItems().clear();
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPostavy());
		seznamVeciVBatohu.getItems().clear();
		seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyVBatohu());
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
	}
	
}