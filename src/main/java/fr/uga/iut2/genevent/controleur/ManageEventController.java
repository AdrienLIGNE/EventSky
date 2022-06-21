package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.vue.EvenementItem;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controlleur principale, gère l'affichage et le lien avec les données
 */
public class ManageEventController extends Controller implements Initializable {

    @FXML
    private ListView<Evenement> list_brouillon;
    @FXML
    private ListView<Evenement> list_evenement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // On affiche les listes avec les évènements
        list_brouillon.setItems(getModel().getEvenementsNonConfirme());
        list_evenement.setItems(getModel().getEvenementsConfirme());

        list_brouillon.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
            @Override
            public ListCell<Evenement> call(ListView<Evenement> evenementListView) {
                return new EvenementItem();
            }
        });

        list_evenement.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
            @Override
            public ListCell<Evenement> call(ListView<Evenement> evenementListView) {
                return new EvenementItem();
            }
        });



    }


    @FXML
    private void createEventButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-event-page1-view.fxml"));
        fxmlLoader.setController(new CreateEventController());
        Stage stage = new Stage();

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();
        } catch (IOException e) {
            // TODO: Logger
            e.printStackTrace();
        }
    }


    @FXML
    private void listViewEvent(MouseEvent e) {
        // Si on a sélectionné un évenement
        if(getSelectedEvent() != null) {
            if(e.getClickCount() == 2) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("event-infos-view.fxml"));

                    Stage stage = new Stage();
                    stage.setScene(new Scene(fxmlLoader.load()));

                    InfosEventController controller = fxmlLoader.getController();
                    controller.setEvenement(getSelectedEvent());

                    stage.show();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }


    /**
     * Retourne l'événement sélectionné
     * @return evenement
     */
    public Evenement getSelectedEvent() {
        return list_brouillon.getSelectionModel().getSelectedItem();
    }
}
