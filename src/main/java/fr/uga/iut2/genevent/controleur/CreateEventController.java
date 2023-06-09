package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.util.FiltreUtilitaire;
import fr.uga.iut2.genevent.util.VerifUtilitaire;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Controleur qui gère la création d'un évenement lors des différentes étapes
 */
public class CreateEventController extends FormulaireController<Evenement> implements Initializable {

    private static CreateEventController[] controller = new CreateEventController[4];
    private static Scene[] scenes = new Scene[4];
    static {

        // On charge toutes les pages à l'avance
        for(int i = 0; i < 4; i++) {
            try {
                controller[i] = new CreateEventController();
                FXMLLoader loader = new FXMLLoader(JavaFXGUI.class.getResource("create-event-page" + (i + 1) + "-view.fxml"));
                loader.setController(controller[i]);
                scenes[i] = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static CreateEventController getController() {
        return controller[0];
    }

    private static int etape;

    // Données récupérées à chaque étapes
    private static Evenement evenement;
    private static TypeEvenement type;
    private static int nb_personnes;
    private static String nom_artistes;
    private static String nom;
    private static LocalDate date_debut;
    private static LocalDate date_fin;
    private static int duree;
    private static Lieu lieu;
    private static ObservableList<ChoixMaterielQuantite> choix_materiel;
    private static ObservableList<Personnel> choix_personnel;
    private static ObservableList<DatePossible> date_possibles;

    private static ObservableList<Lieu> lieu_items;
    private static ObservableList<ChoixMaterielQuantite> materiel_items;
    private static ObservableList<ChoixPersonnel> personnel_items;

    // Partie 1 - Infos générales
    @FXML private ComboBox<TypeEvenement> type_cb;
    @FXML private Spinner<Integer> nb_personnes_s;
    @FXML private TextField nom_artiste_tf;
    @FXML private TextField nom_tf;
    @FXML private DatePicker date_debut_dp;
    @FXML private DatePicker date_fin_dp;
    @FXML private Spinner<Integer> duree_s;

    // Partie 2 - Choix du lieu
    @FXML private ListView<Lieu> lieux_list;

    // Partie 3 - Choix du matériel/personnel
    @FXML private ListView<ChoixMaterielQuantite>  materiel_list;
    @FXML private ListView<ChoixPersonnel> personnel_list;

    // Partie 4 - Récap
    @FXML private TextField nom_lieu_tf;
    @FXML private TextField nb_place_tf;
    @FXML private TextField cout_tf;
    @FXML private ListView<DatePossible> dates_list;

    public CreateEventController() {
        etape = 1;
        duree = 1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(lieux_list != null) {

            lieu_items = FXCollections.observableArrayList();

            lieux_list.setItems(lieu_items);

            lieux_list.setCellFactory(new Callback<ListView<Lieu>, ListCell<Lieu>>() {
                @Override
                public ListCell<Lieu> call(ListView<Lieu> lieuListView) {
                    return new LieuItem();
                }
            });
        }

        if(materiel_list != null & personnel_list != null) {
            personnel_items = FXCollections.observableArrayList();
            materiel_items = FXCollections.observableArrayList();

            personnel_list.setItems(personnel_items);
            materiel_list.setItems(materiel_items);

            materiel_list.setCellFactory(new Callback<ListView<ChoixMaterielQuantite>, ListCell<ChoixMaterielQuantite>>() {
                @Override
                public ListCell<ChoixMaterielQuantite> call(ListView<ChoixMaterielQuantite> choixMaterielQuantiteListView) {
                    // On lui fournit toutes ces informations pour qu'il puisse calculer les dispos lorsque les dates possibles changent
                    return new MaterielItemChoice(date_possibles);
                }
            });

            personnel_list.setCellFactory(new Callback<ListView<ChoixPersonnel>, ListCell<ChoixPersonnel>>() {
                @Override
                public ListCell<ChoixPersonnel> call(ListView<ChoixPersonnel> choixPersonnelListView) {
                    return new PersonnelItemChoice();
                }
            });
        }
    }

    public void resetEtape() {
        setEditMode(false);
        etape = 1;

        type = null;
        nb_personnes = 0;
        nom = null;
        nom_artistes = null;
        date_debut = null;
        date_fin = null;
        duree = 1;
        lieu = null;
        choix_materiel = null;
        choix_personnel = null;
    }

    @Override
    public void setEditMode(Evenement evenement) {
        setEditMode(evenement, true);
    }

    public void setEditMode(Evenement ev, boolean restaure) {
        super.setEditMode(ev);

        if(restaure) {
            evenement = ev;
            type = ev.getType().getValue();
            nb_personnes = ev.getNbPersonnes().getValue();
            nom = ev.getNomEvenement().getValue();
            nom_artistes = ev.getNomArtiste().getValue();
            date_debut = ev.getDateDebut().getValue();
            date_fin = ev.getDateFin().getValue();
            duree = ev.getDuree().getValue();
            lieu = ev.getLieu().getValue();

            // On récupère la liste de personnel
            choix_personnel = FXCollections.observableArrayList(ev.getPersonnel());

            // On récupère la liste de matériel
            choix_materiel = FXCollections.observableArrayList();
            for (Materiel m : getModel().getMateriels()) {
                ChoixMaterielQuantite c = new ChoixMaterielQuantite(m);
                c.setDefaultValue(m.getQuantiteAffecte(ev));
                choix_materiel.add(c);
            }


            initEtape();
        }

    }

    @Override
    public boolean verifieSaisies() {
        boolean valide = true;

        if(etape == 1) {
            //reinitialisation des bordures des champs
            type_cb.setStyle("-fx-border-color: none");
            nom_artiste_tf.setStyle("-fx-border-color: none");
            date_debut_dp.setStyle("-fx-border-color: none");
            date_fin_dp.setStyle("-fx-border-color: none");
            duree_s.setStyle("-fx-border-color: none");

            //verification des champs
            if(type_cb.getValue() == null){
                type_cb.setStyle("-fx-border-color: red");
                valide = false;

                VerifUtilitaire.createPopOver(type_cb, " Choisir un type ");
            }if (nom_artiste_tf.getText() == null || nom_artiste_tf.getText().isEmpty()){
                nom_artiste_tf.setStyle("-fx-border-color: red");
                valide = false;

                VerifUtilitaire.createPopOver(nom_artiste_tf, " Champ obligatoire ");
            }if (date_debut_dp.getValue() != null & date_fin_dp.getValue() != null){
                //vérification que la date de fin n'est pas antérieure à la date de début
                if (date_debut_dp.getValue().isAfter(date_fin_dp.getValue())){
                    date_fin_dp.setStyle("-fx-border-color: red");
                    date_debut_dp.setStyle("-fx-border-color: red");
                    valide = false;

                    VerifUtilitaire.createPopOver(date_fin_dp, " Date invalide ");
                }
                //vérification que la durée n'est pas supérieure au laps de temps sélectionné
                if (duree_s.getValue() > java.time.temporal.ChronoUnit.DAYS.between(date_debut_dp.getValue(), date_fin_dp.getValue())+1){
                    duree_s.setStyle("-fx-border-color: red");
                    valide = false;

                    VerifUtilitaire.createPopOver(duree_s, " " + (java.time.temporal.ChronoUnit.DAYS.between(date_debut_dp.getValue(), date_fin_dp.getValue())+1) + " jours maximum ");
                }
            }if (date_debut_dp.getValue() == null){
                date_debut_dp.setStyle("-fx-border-color: red");
                valide = false;

                VerifUtilitaire.createPopOver(date_debut_dp, " Choisir une date ");
            }if (date_fin_dp.getValue() == null){
                date_fin_dp.setStyle("-fx-border-color: red");
                valide = false;

                VerifUtilitaire.createPopOver(date_fin_dp, " Choisir une date ");
            }
        }

        if(etape == 2) {
            //reset de la bordure
            lieux_list.setStyle("-fx-border-color: none");

            if (lieux_list.getSelectionModel().isEmpty()){
                lieux_list.setStyle("-fx-border-color: red");
                valide = false;

                VerifUtilitaire.createPopOver(lieux_list, " Choisir un lieu ");
            }
        }

        if (etape == 3){
            //reset des bordures
/*            materiel_list.setStyle("-fx-border-color: black");
            personnel_list.setStyle("-fx-border-color: black");

            if (materiel_list.getSelectionModel().isEmpty()){
                materiel_list.setStyle("-fx-border-color: red");
                valide = false;
            }if (personnel_list.getSelectionModel().isEmpty()){
                personnel_list.setStyle("-fx-border-color: red");
                valide = false;
            }*/
        }

        if (etape == 4);//rien ne se passe, l'étape 4 est seulement une consultation des informations rentrees

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
        stage.setScene(scenes[n-1]);
        if(isOnEditMode()) {
            controller[n-1].setEditMode(evenement, false);
        }
        else {
            controller[n-1].setEditMode(false);
        }
        controller[n-1].initEtape();
    }

    public void showPage(Stage stage) {
        resetEtape();
        showPage(stage, 1);
        stage.setResizable(false);
    }

    @Override
    public void confirmButtonClick(ActionEvent e) {

        // Création de l'événement

        Evenement evenement;

        if(!isOnEditMode()) {
            evenement = new Evenement(date_debut, date_fin, duree, nom, type);
        }
        else {
            evenement = getElementModifie();

            evenement.deleteAllReservable();
            evenement.setDateDebut(date_debut);
            evenement.setDateFin(date_fin);
            evenement.setDuree(duree);
            evenement.setNomEvenement(nom);
            evenement.setType(type);
        }

        evenement.setNbPersonnes(nb_personnes);
        evenement.setLieu(lieu);
        evenement.setNomArtiste(nom_artistes);

        for(ChoixMaterielQuantite m : choix_materiel) {
            if(m.getQuantite() > 0)
                evenement.addMateriel(m.getMateriel(), m.getQuantite());
        }

        for(Personnel p : choix_personnel) {
            evenement.addPersonnel(p);
        }

        if(!isOnEditMode()) {
            getModel().addEvenement(evenement);
            Main.LOGGER.log(Level.INFO, "Création de l'événement " + evenement.getNomEvenement());
        }
        else {
            Main.LOGGER.log(Level.INFO, "Modification de l'événement " + evenement.getNomEvenement());
        }
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
            nom = nom_tf.getText();

            date_debut = date_debut_dp.getValue();
            date_fin = date_fin_dp.getValue();
            duree = duree_s.getValue();

            date_possibles = DatePossible.getDatePossible(date_debut, date_fin, duree, lieu, choix_materiel, choix_personnel);


        }
        if(etape == 2) {
            lieu = lieux_list.getSelectionModel().getSelectedItem();

            date_possibles = DatePossible.getDatePossible(date_debut, date_fin, duree, lieu, choix_materiel, choix_personnel);
        }
        if(etape == 3) {
            choix_materiel = materiel_list.getItems();

            ObservableList<ChoixPersonnel> choix = personnel_list.getItems();
            choix_personnel = FXCollections.observableArrayList(); // On vide les choix du personnel précédent

            // on récupère tout le personnel sélectionné
            for(ChoixPersonnel c : choix) {
                if(c.isSelected()) {
                    choix_personnel.add(c.getPersonnel());
                }
            }


            date_possibles = DatePossible.getDatePossible(date_debut, date_fin, duree, lieu, choix_materiel, choix_personnel);
        }
    }

    @FXML
    private void onDateDebutClick(ActionEvent event){
        date_fin_dp.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(date_debut_dp.getValue()) < 0 );
            }
        });
    }

    /**
     * Permet de mettre les bonne valeurs lors de l'affichage de chaque étapes (restauration lorsque l'on appuie sur
     * précédent, chargement des listes)
     */
    private void initEtape() {
        if(etape == 1) {
            type_cb.setItems(FXCollections.observableList(Arrays.asList(TypeEvenement.values())));

            //faire en sorte que l'utilisateur ne puisse sélectionner seulement des dates futures
            date_debut_dp.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
                }
            });


            date_fin_dp.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
                }
            });

            type_cb.setValue(type);
            nb_personnes_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000000, nb_personnes));
            nb_personnes_s.setEditable(true);
            nom_artiste_tf.setText(nom_artistes);
            nom_tf.setText(nom);
            date_debut_dp.setValue(date_debut);
            date_fin_dp.setValue(date_fin);
            duree_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000000, duree));

        }
        else if(etape == 2) {
            // Choix de la salle
            lieu_items.setAll(FiltreUtilitaire.filtreLieuByCapacite(getModel().getLieuDisponibles(date_possibles), nb_personnes));
            lieux_list.getSelectionModel().select(lieu);
        }
        else if(etape == 3) {

            ObservableList<ChoixPersonnel> personnels = ChoixPersonnel.createList(getModel().getPersonnelDisponibles(date_possibles));

            if(choix_personnel != null) {
                // On coche toutes les personnels pris (modification)
                for (ChoixPersonnel p : personnels) {
                    if (choix_personnel.contains(p.getPersonnel())) {
                        p.setDefaultSelect();
                    }
                }
            }

            ObservableList<ChoixMaterielQuantite> materiels = ChoixMaterielQuantite.createList(getModel().getMaterielDisponibles(date_possibles));

            if(choix_materiel != null) {
                materiels = choix_materiel;

                for (ChoixMaterielQuantite m : materiels) {
                    m.setDefaultValue(choix_materiel.get(choix_materiel.indexOf(m)).getQuantite());
                }
            }

            materiel_items.setAll(materiels);
            personnel_items.setAll(personnels);
        }

        if(etape == 4) {
            nom_lieu_tf.setText(lieu.getNom().getValue());
            nb_place_tf.setText(Integer.toString(nb_personnes));
            nom_artiste_tf.setText(nom_artistes);

            double prix = 0;

            for(Personnel p : choix_personnel) {
                prix += p.getTypeEmploi().getValue().getSalaire();
            }

            prix = prix * duree;

            cout_tf.setText(prix + " €");

            dates_list.setItems(date_possibles);
            dates_list.setEditable(false);

        }
    }


    @FXML
    private void listViewEvent(MouseEvent e) {
        //date_possibles.setAll(DatePossible.getDatePossible(date_debut, date_fin, duree, lieu, choix_materiel, choix_personnel));
    }
}
