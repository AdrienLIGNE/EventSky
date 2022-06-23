package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.vue.EvenementItem;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    private static ManageEventController controller;

    static {
        controller = new ManageEventController();
    }

    public static ManageEventController getController() {
        return controller;
    }

    @FXML
    private ListView<Evenement> list_brouillon;
    @FXML
    private ListView<Evenement> list_evenement;

    @FXML private Button confirm_event_btn;

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

        CreateEventController controller = CreateEventController.getController();
        controller.resetEtape();

        fxmlLoader.setController(controller);

        Stage stage = new Stage();

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.setTitle("SkyEvent - Créer un évènement");
            stage.setResizable(false);
        } catch (IOException e) {
            // TODO: Logger
            e.printStackTrace();
        }
    }


    @FXML
    private void listViewEvent(MouseEvent e) {
        // Si on a sélectionné un évenement
        if(getSelectedEvent() != null) {
            confirm_event_btn.setDisable(false);
            if(e.getClickCount() == 2) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-event-page1-view.fxml"));
                    CreateEventController controller = CreateEventController.getController();
                    fxmlLoader.setController(controller);
                    controller.resetEtape();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(fxmlLoader.load()));

                    controller.setEditMode(getSelectedEvent());
                    stage.setTitle("SkyEvent - Afficher un évènement");
                    stage.show();
                    stage.setResizable(false);

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

    @FXML
    private void confirmEventClick(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("confirm-event-view.fxml"));
        ConfirmEventController controller = ConfirmEventController.getController();
        fxmlLoader.setController(controller);

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
            controller.setEvenement(getSelectedEvent());
            stage.show();
            stage.setResizable(false);

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        confirm_event_btn.setDisable(true);
    }

    // Action appelé quand on clique quelque part sur la fenêtre
    @FXML
    private void parentClick(Event e) {
        confirm_event_btn.setDisable(true);
    }
}
