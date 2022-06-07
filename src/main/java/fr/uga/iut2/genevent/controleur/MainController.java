package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.vue.EvenementItem;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controlleur principale, gère l'affichage et le lien avec les données
 */
public class MainController implements Initializable{

    private final MainApplication app;

    @FXML private ListView<Evenement> list_brouillon;
    @FXML private ListView<Evenement> list_evenement;

    public MainController() {
        app = new MainApplication();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // On affiche les listes avec les évènements
        list_brouillon.setItems(app.getEvenements());

        list_brouillon.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
            @Override
            public ListCell<Evenement> call(ListView<Evenement> evenementListView) {
                return new EvenementItem();
            }
        });
    }

    @FXML
    private void click(ActionEvent e) {
        System.out.println("test");
    }



}
