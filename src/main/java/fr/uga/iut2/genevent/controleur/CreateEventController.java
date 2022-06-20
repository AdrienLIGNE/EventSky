package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.util.FiltreUtilitaire;
import fr.uga.iut2.genevent.vue.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreateEventController extends FormulaireController<Evenement> implements Initializable {

    private int etape;

    // Données récupérées à chaque étapes
    private TypeEvenement type;
    private int nb_personnes;
    private String nom_artistes;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Lieu lieu;
    private ObservableList<ChoixMaterielQuantite> choix_materiel;
    private ObservableList<Personnel> choix_personnel;



    // Partie 1 - Infos générales
    @FXML private ComboBox<TypeEvenement> type_cb;
    @FXML private Spinner<Integer> nb_personnes_s;
    @FXML private TextField nom_artiste_tf;
    @FXML private DatePicker date_debut_dp;
    @FXML private DatePicker date_fin_dp;

    // Partie 2 - Choix du lieu
    @FXML private ListView<Lieu> lieux_list;

    // Partie 3 - Choix du matériel/personnel
    @FXML private ListView<ChoixMaterielQuantite>  materiel_list;
    @FXML private ListView<Personnel> personnel_list;

    // Partie 4 - Récap
    @FXML private TextField nom_lieu_tf;
    @FXML private TextField nb_place_tf;
    @FXML private TextField cout_tf;

    public CreateEventController() {
        etape = 1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEtape();
    }

    @Override
    public boolean verifieSaisies() {
        boolean valide = true;
        // TODO: Vérification des valeurs à chaque étapes

        if(etape == 1) {

        }

        if(etape == 2) {

        }

        if (etape == 3){

        }

        if (etape == 4){

        }

        return valide;
    }

    @FXML
    private void nextButtonClick(ActionEvent e) {

        if(verifieSaisies()) {
            saveSaisies();

            etape += 1;

            showPage(Controller.getStageFromTarget(e.getTarget()), etape);
        }

    }

    @FXML
    private void precButtonClick(ActionEvent e) {
        etape -= 1;

        showPage(Controller.getStageFromTarget(e.getTarget()), etape);
    }


    /**
     * Affichage de la page à l'étape choisis
     * @param stage stage
     * @param n numéro étape
     */
    private void showPage(Stage stage, int n) {

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-event-page" + n + "-view.fxml"));

        if(fxmlLoader.getController() == null) {
            fxmlLoader.setController(this);
        }

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            //initEtape();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void confirmButtonClick(ActionEvent e) {
        // Création de l'événement

        Evenement evenement = new Evenement(0, date_debut, date_fin, nom_artistes);
        evenement.setLieu(lieu);

        for(ChoixMaterielQuantite m : choix_materiel) {
            evenement.addMateriel(m.getMateriel(), m.getQuantite());
        }

        for(Personnel p : choix_personnel) {
            evenement.addPersonnel(p);
        }

        evenement.confirme();
        getModel().addEvenement(evenement);
        exitStage(Controller.getStageFromTarget(e.getTarget()));
    }

    /**
     * Récupère les données saisies par l'utilisateur et les enregistre
     */
    private void saveSaisies() {
        if(etape == 1) {

            type = type_cb.getValue();
            nb_personnes = nb_personnes_s.getValue();
            nom_artistes = nom_artiste_tf.getText();

            date_debut = date_debut_dp.getValue();
            date_fin = date_fin_dp.getValue();

        }
        if(etape == 2) {
            lieu = lieux_list.getSelectionModel().getSelectedItem();
        }
        if(etape == 3) {
            choix_materiel = materiel_list.getItems();
            choix_personnel = personnel_list.getSelectionModel().getSelectedItems();
        }
    }

    /**
     * Permet de mettre les bonne valeurs lors de l'affichage de chaque étapes (restauration lorsque l'on appuie sur
     * précédent, chargement des listes)
     */
    private void initEtape() {
        if(etape == 1) {
            type_cb.setItems(FXCollections.observableList(Arrays.asList(TypeEvenement.values())));

            type_cb.setValue(type);
            nb_personnes_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000000, nb_personnes));
            nb_personnes_s.setEditable(true);
            nom_artiste_tf.setText(nom_artistes);
            date_debut_dp.setValue(date_debut);
            date_fin_dp.setValue(date_fin);

        }
        else if(etape == 2) {
            // Choix de la salle
            lieux_list.setItems(FiltreUtilitaire.filtreLieuByCapacite(getModel().getLieuxDisponibles(date_debut, date_fin), nb_personnes));

            lieux_list.setCellFactory(new Callback<ListView<Lieu>, ListCell<Lieu>>() {
                @Override
                public ListCell<Lieu> call(ListView<Lieu> lieuListView) {
                    return new LieuItem();
                }
            });

            lieux_list.getSelectionModel().select(lieu);
        }
        else if(etape == 3) {

            materiel_list.setItems(ChoixMaterielQuantite.createList(getModel().getMaterielDisponibles(date_debut, date_fin)));
            personnel_list.setItems(getModel().getPersonnelDisponibles(date_debut, date_fin));

            materiel_list.setCellFactory(new Callback<ListView<ChoixMaterielQuantite>, ListCell<ChoixMaterielQuantite>>() {
                @Override
                public ListCell<ChoixMaterielQuantite> call(ListView<ChoixMaterielQuantite> choixMaterielQuantiteListView) {
                    return new MaterielItemChoice(date_debut, date_fin);
                }
            });

            personnel_list.setCellFactory(new Callback<ListView<Personnel>, ListCell<Personnel>>() {
                @Override
                public ListCell<Personnel> call(ListView<Personnel> personnelListView) {
                    return new PersonnelItem();
                }
            });
            personnel_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        if(etape == 4) {
            nom_lieu_tf.setText(lieu.getNom().getValue());
            nb_place_tf.setText(Integer.toString(nb_personnes));
            nom_artiste_tf.setText(nom_artistes);
        }
    }
}
