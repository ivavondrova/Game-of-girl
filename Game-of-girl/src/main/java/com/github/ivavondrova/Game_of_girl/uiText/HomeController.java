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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	
	private final Image IMAGE_RUM  = new Image("/rum.png");
    private final Image IMAGE_LISTINA  = new Image("/listina.png");
    private final Image IMAGE_LEKTVAR  = new Image("/lektvar.png");
    private final Image IMAGE_MEC = new Image("/mec.png");
    private final Image IMAGE_GLOBUS = new Image("/globus.png");
    private final Image IMAGE_KNIHA = new Image("/kniha.png");
    private final Image IMAGE_JED = new Image("/jed.png");

    private Image[] listOfImages = {IMAGE_RUM, IMAGE_LISTINA, IMAGE_LEKTVAR, IMAGE_MEC, IMAGE_GLOBUS, IMAGE_KNIHA, IMAGE_JED};
	
    /**
     * Metoda ovládání příkazu "jdi" - po kliknutí hráč přejde do příslušné lokace.
     */
    
    public void jdiDoLokace() {
    		String temp = "jdi " + seznamMistnosti.getSelectionModel().getSelectedItem().getNazev();
    		String vypis = hra.zpracujPrikaz(temp);
    		textVypis.appendText("\n--------\n"+ temp + "\n--------\n");
    		textVypis.appendText(vypis);
    		
    		if(hra.konecHry()) {
    			textVypis.appendText("\n\n Konec hry \n");
    			odesli.setDisable(true);
    		}
    }
    
    /**
     * Metoda ovládání příkazu "seber" - po kliknutí hráč sebere věc a přidá ji do batohu.
     */
    
    public void seberVec() {
    		String temp = "seber " + seznamVeci.getSelectionModel().getSelectedItem().getNazev();
		String vypis = hra.zpracujPrikaz(temp);
		textVypis.appendText("\n--------\n"+ temp + "\n--------\n");
		textVypis.appendText(vypis);
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			odesli.setDisable(true);
		}
    }
    
    /*
     * Metoda na ovládání příkazu polož - po kliknutí na danou věc je věc odebraná z batohu.
     * Pokud se jedná o lektvary (nápoje) a klikneme na ně pravým tlačítkem, nápoj bude vypit.
     */
    
    public void zahodVec(MouseEvent event) {
    		String temp, x;
    		x = seznamVeciVBatohu.getSelectionModel().getSelectedItem().getNazev();
    		if (event.getButton() == MouseButton.SECONDARY && (x == "bezbarvy_napoj" || x == "cerveny_napoj")) {
    			temp = "pij " + x;
    		}
    		else {
    			temp = "poloz " + x;
    		}
		String vypis = hra.zpracujPrikaz(temp);
		textVypis.appendText("\n--------\n"+ temp + "\n--------\n");
		textVypis.appendText(vypis);
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			odesli.setDisable(true);
		}
    }
	
    /*
     * Metoda na ovládání příkazu "promluv".
     */
    
    public void mluvSPostavou () {
    		String temp = "promluv " + seznamPostav.getSelectionModel().getSelectedItem().getJmeno();
		String vypis = hra.zpracujPrikaz(temp);
		textVypis.appendText("\n--------\n"+ temp + "\n--------\n");
		textVypis.appendText(vypis);
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			odesli.setDisable(true);
		}
    }
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			odesli.setDisable(true);
		}
		
	}
	
	/**
	 * Metoda pro ukončení hry.
	 */
	
	public void konecHry() {
		Platform.exit();
	}
	
	/**
	 * Metoda pro nastavení nové hry - nulování panelů (seznamu místností, věcí, postav apod.)
	 */
	
	public void novaHra() {
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamPostav.getItems().clear();
		seznamVeciVBatohu.getItems().clear();
		hra.novaHra();
		this.inicializuj(hra);
	}
	
	/**
	 * Metoda, která nastavuje vyskakovací okno s informacemi o hře.
	 */
	
	public void oHre() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game of Girl");
        alert.setHeaderText("Game of Girl \nAdventura - textová hra rozšířena o grafickou podobu");
        alert.setContentText("Iva Vondrová \nLS 2016/2017 - 4IT101 a LS 2017/2018 - 4IT115 \nFIS VŠE v Praze");
        alert.showAndWait();
	}
	
	/**
	 * Metoda, která nastavuje vyskakovací okno s nápovědou v html.
	 */
	
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
		// textVstup.setDisable(false);
		// odesli.setDisable(false);
		
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
		
		/**
		 * Nastavení seznamu věcí v batohu - jejich zobrazí pomocí obrázků.
		 */
		
		seznamVeciVBatohu.setCellFactory(param -> new ListCell<Predmet>()
				{
			private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Predmet vec, boolean empty) {
                super.updateItem(vec, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(vec.getNazev().equals("rum"))
                        imageView.setImage(listOfImages[0]);
                    else if(vec.getNazev().equals("listina"))
                        imageView.setImage(listOfImages[1]);
                    else if(vec.getNazev().equals("bezbarvy_napoj"))
                        imageView.setImage(listOfImages[2]);
                    else if(vec.getNazev().equals("mec"))
                        imageView.setImage(listOfImages[3]);
                    else if(vec.getNazev().equals("globus"))
                        imageView.setImage(listOfImages[4]);
                    else if(vec.getNazev().equals("kniha"))
                        imageView.setImage(listOfImages[5]);
                    else if(vec.getNazev().equals("cerveny_napoj"))
                    		imageView.setImage(listOfImages[6]);
                    setText(vec.getNazev());
                    setGraphic(imageView);
                	}
            	}
		  }
		);
				
		/**
		 * Aktualizace aktuální lokace.
		 */
				
		hra.getHerniPlan().getAktualniLokace().addObserver(this);
		uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
	}
	
}