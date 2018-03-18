package com.github.ivavondrova.Game_of_girl.uiText;

import java.util.Observable;
import java.util.Observer;

import com.github.ivavondrova.Game_of_girl.logika.IHra;
import com.github.ivavondrova.Game_of_girl.logika.Lokace;
import com.github.ivavondrova.Game_of_girl.logika.Postava;
import com.github.ivavondrova.Game_of_girl.logika.Predmet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
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
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmety());
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPostavy());
		seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyVBatohu());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
		seznamVeci.getItems().clear();
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPredmety());
		seznamPostav.getItems().clear();
		seznamPostav.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getPostavy());
		seznamVeciVBatohu.getItems().clear();
		seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getPredmetyVBatohu());
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
	}

}