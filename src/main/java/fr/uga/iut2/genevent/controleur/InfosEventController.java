package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.vue.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;

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

    @FXML private Button confirm_btn;
    @FXML private Button edit_btn;
    @FXML private Label confirm;


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

        confirm_btn.setDisable(evenement.isConfirmed());
        edit_btn.setDisable(evenement.isConfirmed());
        confirm.setText(evenement.isConfirmed() ? "Confirmé" : "Non confirmé");
        confirm.setTextFill(evenement.isConfirmed() ? new Color(0, 0.7, 0, 1) : new Color(0.7, 0.5, 0, 1));

        evenement.getConfirmed().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                confirm_btn.setDisable(evenement.isConfirmed());
                edit_btn.setDisable(evenement.isConfirmed());
                confirm.setText(evenement.isConfirmed() ? "Confirmé" : "Non confirmé");
                confirm.setTextFill(evenement.isConfirmed() ? new Color(0, 0.7, 0, 1) : new Color(0.7, 0.5, 0, 1));
            }
        });

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

        ObservableList<Personnel> personnels = FXCollections.observableArrayList(e.getPersonnel());
        personnels_items.setAll(personnels);

        updateMateriel(e);

        e.getMateriel().addListener(new ListChangeListener<Materiel>() {
            @Override
            public void onChanged(Change<? extends Materiel> change) {
                updateMateriel(e);
            }
        });


    }

    private void updateMateriel(Evenement e) {
        ObservableList<ChoixMaterielQuantite> materiels = ChoixMaterielQuantite.createList(FXCollections.observableArrayList(e.getMateriel()));

        for(ChoixMaterielQuantite m : materiels) {
            m.setDefaultValue(m.getMateriel().getQuantiteAffecte(evenement));
        }

        materiels_items.setAll(materiels);
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


    @FXML
    private void deleteButtonClick(ActionEvent e) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation requise");
        confirm.setHeaderText("Supprimer cet évenement ?");
        confirm.setContentText("Une fois confirmé, vous ne pourrez plus revenir en arrière !");

        Optional<ButtonType> result = confirm.showAndWait();

        if(result.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
            // On supprime l'élement sélectionné

            Main.LOGGER.log(Level.INFO, "Suppression de l'évènement...");
            getModel().supprimeEvenement(evenement);

            back(getStageFromTarget(e.getTarget()));
        }
    }

    @FXML
    private void backButtonClick(ActionEvent e) {
        back(getStageFromTarget(e.getTarget()));
    }

    private void back(Stage stage) {
        stage.setScene(MenuController.getManageEventScene());
    }

    @FXML
    private void confirmButtonClick(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("confirm-event-view.fxml"));
        ConfirmEventController controller = ConfirmEventController.getController();
        fxmlLoader.setController(controller);

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
            controller.setEvenement(evenement);
            stage.show();
            stage.setResizable(false);

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
