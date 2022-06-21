package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.vue.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class InfosEventController extends Controller{

    private Evenement evenement;

    @FXML private Label title;
    @FXML private TextField nom_tf;
    @FXML private TextField lieu_tf;
    @FXML private DatePicker date_debut_dp;
    @FXML private DatePicker date_fin_dp;
    @FXML private TextField nb_personnes_tf;
    @FXML private TextField type_tf;
    @FXML private TextField nom_artiste_tf;
    @FXML private TextField cout_tf;

    @FXML private ListView<Materiel> materiel_list;
    @FXML private ListView<Personnel> personnel_list;


    public void setEvenement(Evenement e) {
        this.evenement = e;

        title.setText(e.getNomEvenement().getValue());
        nom_tf.setText(e.getNomEvenement().getValue());
        lieu_tf.setText(e.getLieu().getNom().getValue());
        date_debut_dp.setValue(e.getDateDebut());
        date_fin_dp.setValue(e.getDateFin());
        nb_personnes_tf.setText(e.getNbPersonnes().getValue().toString());
        type_tf.setText(e.getType().getValue().getNom());
        nom_artiste_tf.setText(e.getNomArtiste().getValue());

        ObservableList<Materiel> materiels = FXCollections.observableArrayList(e.getMateriel());
        ObservableList<Personnel> personnels = FXCollections.observableArrayList(e.getPersonnel());

        materiel_list.setItems(materiels);
        personnel_list.setItems(personnels);

        materiel_list.setCellFactory(new Callback<ListView<Materiel>, ListCell<Materiel>>() {
            @Override
            public ListCell<Materiel> call(ListView<Materiel> materielListView) {
                return new MaterielItem(e);
            }
        });

        personnel_list.setCellFactory(new Callback<ListView<Personnel>, ListCell<Personnel>>() {
            @Override
            public ListCell<Personnel> call(ListView<Personnel> personnelListView) {
                return new PersonnelItem();
            }
        });
    }



    @FXML
    private void editEvenementClick(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-event-page1-view.fxml"));
        CreateEventController controller = new CreateEventController();

        fxmlLoader.setController(controller);

        Stage stage = getStageFromTarget(e.getTarget());
        try {
            stage.setScene(new Scene(fxmlLoader.load()));

            controller.setEditMode(evenement);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }




}
