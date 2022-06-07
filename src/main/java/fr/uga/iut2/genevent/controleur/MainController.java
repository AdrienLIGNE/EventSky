package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controlleur principale, gère l'affichage et le lien avec les données
 */
public class MainController implements Initializable {

    private final IHM ihm;
    private final MainApplication app;

    @FXML private ListView list_brouillon;
    @FXML private ListView list_evenement;

    public MainController(MainApplication app) {
        this.app = app;
        this.ihm = new JavaFXGUI(this);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void demarrer() {
        this.ihm.demarrerInteraction();
    }



}
