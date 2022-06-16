package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.vue.EvenementItem;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controlleur principale, gère l'affichage et le lien avec les données
 */
public class ManageEventController extends Controller implements Initializable{

    @FXML private ListView<Evenement> list_brouillon;
    @FXML private ListView<Evenement> list_evenement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // On affiche les listes avec les évènements
        list_brouillon.setItems(getModel().getEvenements());

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

    @FXML
    private void showInfosEvenementClick(MouseEvent e) {
        System.out.println("lets go");
    }

    @FXML
    private void gestionRessourcesClick(ActionEvent event){
        try {
            // Chargement de la nouvelle interface
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-ressource-view.fxml"));
            fxmlLoader.setController(new ManageRessourcesController());
            Scene scene = new Scene(fxmlLoader.load());

            // Création d'un nouveau stage
            Stage stage = (Stage) ((Button) event.getTarget()).getScene().getWindow();
            stage.setTitle("Gestion des ressources");
            // affectation de la nouvelle scène au stage et affichage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
