package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.Reservable;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import fr.uga.iut2.genevent.vue.LieuItem;
import fr.uga.iut2.genevent.vue.MaterielItem;
import fr.uga.iut2.genevent.vue.PersonnelItem;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Cette classe gère la partie de l'interface de gestion des ressources. Elle charge les données dans les ListView
 * et gère les événements d'ajout/édition/supression des différents élèments
 */
public class ManageRessourcesController extends Controller implements Initializable {

    private final static int MATERIEL_TAB = 0;
    private final static int PERSONNEL_TAB = 1;
    private final static int LIEU_TAB = 2;

    @FXML private ListView<Materiel> materiel_list;
    @FXML private ListView<Personnel> personnel_list;
    @FXML private ListView<Lieu> lieux_list;

    @FXML private Button ajouter_btn;
    @FXML private Button modifier_btn;
    @FXML private Button supprimer_btn;

    @FXML private InfosMaterielController infos_materielController;

    // Les onglets permette de savoir quel type de ressource on gère
    @FXML private TabPane onglets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Désactivation des boutons modifier et supprimer en attendant que un élément soit séléctionné
        activeEditButton(false);

        materiel_list.setItems(getModel().getMateriels());
        personnel_list.setItems(getModel().getPersonnels());
        lieux_list.setItems(getModel().getLieux());

        materiel_list.setCellFactory(new Callback<ListView<Materiel>, ListCell<Materiel>>() {
            @Override
            public ListCell<Materiel> call(ListView<Materiel> materielListView) {
                return new MaterielItem();
            }
        });

        personnel_list.setCellFactory(new Callback<ListView<Personnel>, ListCell<Personnel>>() {
            @Override
            public ListCell<Personnel> call(ListView<Personnel> personnelListView) {
                return new PersonnelItem();
            }
        });

        lieux_list.setCellFactory(new Callback<ListView<Lieu>, ListCell<Lieu>>() {
            @Override
            public ListCell<Lieu> call(ListView<Lieu> lieuListView) {
                return new LieuItem();
            }
        });

    }

    /**
     * Méthode appeler lors du clique sur le bouton ajouter
     * @param e
     */
    @FXML
    public void ajoutRessourceClick(ActionEvent e){
        Stage stage = new Stage();

        // On récupère le bon FXML en fonction de l'onglet
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource(getFXMLRessourceCreator()));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter nouvelles ressources");
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void editRessourceClick(ActionEvent e){
        editRessource(getSelectedRessource());
    }

    /**
     * Permet d'éditer une ressource. Cette méthode est aussi utilisé depuis le menu contextuel des items
     * @param reservableModifie reservable à modifier
     */
    public static void editRessource(Reservable reservableModifie) {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource(getFXMLRessourceCreator(reservableModifie)));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modification du matériel");
            stage.show();

            FormulaireController controller =  fxmlLoader.getController();
            controller.setEditMode(reservableModifie);
        }
        catch (IOException ex) {

        }
    }

    @FXML
    private void supprimerRessourceClick() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation requise");
        confirm.setHeaderText("Supprimer cette ressource ?");
        confirm.setContentText("Une fois confirmer, vous ne pourrez plus revenir en arrière !");

        Optional<ButtonType> result = confirm.showAndWait();

        if(result.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
            // On supprime l'élement sélectionné
            getModel().removeReservable(getSelectedRessource());
            activeEditButton(false);
        }
    }


    /**
     * Retourne l'url (nom de fichier) du FXML en fonction de l'onglet actif
     * @return URL du fichier fxml
     */
    private String getFXMLRessourceCreator() {
        int index = getOngletActif();

        if(index == MATERIEL_TAB) return "create-items-view.fxml";
        if(index == PERSONNEL_TAB) return "create-personnel-view.fxml";
        if(index == LIEU_TAB) return "create-salle-view.fxml";
        else return null;
    }

    /**
     * Retourne l'url du fichier fxml en fonction du type de ressource
     * @param r ressource
     * @return url du fichier fxml
     */
    private static String getFXMLRessourceCreator(Reservable r) {
        if(r instanceof Materiel) return "create-items-view.fxml";
        if(r instanceof Personnel) return "create-personnel-view.fxml";
        if(r instanceof Lieu) return "create-salle-view.fxml";
        else return null;
    }

    /**
     * Retourne la ressource sélectionnée dans l'onglet courant
     * @return ressource
     */
    private Reservable getSelectedRessource() {
        int index = getOngletActif();

        if(index == MATERIEL_TAB) return materiel_list.getSelectionModel().getSelectedItem();
        if(index == PERSONNEL_TAB) return personnel_list.getSelectionModel().getSelectedItem();
        if(index == LIEU_TAB) return lieux_list.getSelectionModel().getSelectedItem();
        else return null;
    }

    private int getOngletActif() {
        return onglets.getSelectionModel().getSelectedIndex();
    }

    @FXML
    private void listViewEvent(MouseEvent e) {
        // On regarde si un élément est sélectionné
        if(getSelectedRessource() != null) {
            activeEditButton(true); // Activation des boutons de modifications

            //si c'est un simple click on affiche les infos sur le coté
            if (e.getClickCount() == 1){
                if (getOngletActif() == MATERIEL_TAB){
                    infos_materielController.setMateriel((Materiel) getSelectedRessource());
                    infos_materielController.affiche();
                }
            }
            // Si c'est un double click alors on ouvre l'édition
            if (e.getClickCount() == 2) {
                editRessource(getSelectedRessource());
            }

        }
        else {
            activeEditButton(false);
        }
    }

    @FXML
    private void ongletClick(MouseEvent e) {
        activeEditButton(false);
    }


    private void activeEditButton(boolean b) {
        modifier_btn.setDisable(!b);
        supprimer_btn.setDisable(!b);
    }


}
