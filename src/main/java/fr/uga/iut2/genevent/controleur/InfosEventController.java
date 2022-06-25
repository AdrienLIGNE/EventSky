package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.vue.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class InfosEventController extends Controller implements Initializable {

    private static InfosEventController controller;
    private static Stage createEventStage;

    static {
        controller = new InfosEventController();
        createEventStage = new Stage();
    }

    public static InfosEventController getController() {
        return controller;
    }

    private Evenement evenement;

    @FXML private Label title;
    @FXML private Label type;
    @FXML private Label date;
    @FXML private Label nom_evenement;
    @FXML private Label duree;
    @FXML private Label cout;
    @FXML private Label nom_artistes;
    @FXML private Label nb_spectateurs;
    @FXML private Label lieu;


    @FXML private ListView<ChoixMaterielQuantite> materiel_list;
    @FXML private ListView<Personnel> personnel_list;

    private static ObservableList<ChoixMaterielQuantite> materiels_items;
    private static ObservableList<Personnel> personnels_items;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materiels_items = FXCollections.observableArrayList();
        personnels_items = FXCollections.observableArrayList();

        materiel_list.setItems(materiels_items);
        personnel_list.setItems(personnels_items);

        materiel_list.setCellFactory(new Callback<ListView<ChoixMaterielQuantite>, ListCell<ChoixMaterielQuantite>>() {
            @Override
            public ListCell<ChoixMaterielQuantite> call(ListView<ChoixMaterielQuantite> choixMaterielQuantiteListView) {
                return new MaterielItemChoice(null);
            }
        });

        personnel_list.setCellFactory(new Callback<ListView<Personnel>, ListCell<Personnel>>() {
            @Override
            public ListCell<Personnel> call(ListView<Personnel> personnelListView) {
                return new PersonnelItem();
            }
        });

    }

    public void setEvenement(Evenement e) {
        this.evenement = e;

        title.textProperty().bind(evenement.getNomEvenement());

        type.textProperty().bind(evenement.getType().asString());

        date.setText(evenement.getDateDebut().getValue().format(DateTimeFormatter.ofPattern("dd MMMM YYYY")) + "  -  " + evenement.getDateFin().getValue().format(DateTimeFormatter.ofPattern("dd MMMM YYYY")));

        evenement.getDateDebut().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate d, LocalDate t1) {
                date.setText(evenement.getDateDebut().getValue().format(DateTimeFormatter.ofPattern("dd MMMM YYYY")) + "  -  " + evenement.getDateFin().getValue().format(DateTimeFormatter.ofPattern("dd MMMM YYYY")));
            }
        });

        nom_evenement.textProperty().bind(evenement.getNomEvenement());
        duree.textProperty().bind(evenement.getDuree().asString());
        nom_artistes.textProperty().bind(evenement.getNomArtiste());
        nb_spectateurs.textProperty().bind(evenement.getNbPersonnes().asString());

        lieu.textProperty().bind(evenement.getLieu().getValue().getNom());

        evenement.getLieu().addListener(new ChangeListener<Lieu>() {
            @Override
            public void changed(ObservableValue<? extends Lieu> observableValue, Lieu l, Lieu t1) {
                lieu.textProperty().bind(evenement.getLieu().getValue().getNom());
            }
        });

        ObservableList<ChoixMaterielQuantite> materiels = ChoixMaterielQuantite.createList(FXCollections.observableArrayList(e.getMateriel()));
        ObservableList<Personnel> personnels = FXCollections.observableArrayList(e.getPersonnel());

        for(ChoixMaterielQuantite m : materiels) {
            m.setDefaultValue(m.getMateriel().getQuantiteAffecte(evenement));
        }

        materiels_items.setAll(materiels);
        personnels_items.setAll(personnels);


    }


    @FXML
    private void editButtonClick(ActionEvent e) {
        Stage stage = new Stage();

        CreateEventController controller = CreateEventController.getController();
        controller.showPage(stage);
        controller.setEditMode(evenement);
        stage.show();

        stage.addEventFilter(WindowEvent.WINDOW_HIDING, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                //setEvenement();
            }
        });
    }








}
