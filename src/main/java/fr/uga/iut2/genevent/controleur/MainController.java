package fr.uga.iut2.genevent.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controlleur principale, gère l'affichage et le lien avec les données
 */
public class MainController extends Controller implements Initializable{


    private static MainController controller;

    static {
        controller = new MainController();
    }

    public static MainController getController() {
        return controller;
    }

    @FXML
    private Node menu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
