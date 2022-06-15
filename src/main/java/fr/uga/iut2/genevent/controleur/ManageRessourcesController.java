package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.Reservable;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import fr.uga.iut2.genevent.vue.MaterielItem;
import fr.uga.iut2.genevent.vue.PersonnelItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageRessourcesController extends Controller implements Initializable {

    private final static int MATERIEL_TAB = 0;
    private final static int PERSONNEL_TAB = 1;
    private final static int LIEU_TAB = 2;

    @FXML private ListView<Materiel> materiel_list;
    @FXML private ListView<Personnel> personnel_list;
    @FXML private ListView<Lieu> lieux_list;

    @FXML private TabPane onglets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        materiel_list.setItems(getModel().getMateriels());
        personnel_list.setItems(getModel().getPersonnels());

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
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource(getFXMLRessourceCreator()));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modification du matériel");
            stage.show();

            FormulaireController controller =  fxmlLoader.getController();
            controller.setEditMode(getSelectedRessource());
        }
        catch (IOException ex) {

        }
    }

    @FXML
    private void supprimerRessourceClick() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation requise");
        confirm.setHeaderText("Supprimer  ?");
        confirm.setContentText("Une fois confirmer, vous ne pourrez plus revenir en arrière !");

        Optional<ButtonType> result = confirm.showAndWait();

        if(result.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
            // On supprime

        }
    }


    /**
     * Retourne l'url (nom de fichier) du FXML en fonction de l'onglet actif
     * @return URL du fichier fxml
     */
    private String getFXMLRessourceCreator() {
        int index = onglets.getSelectionModel().getSelectedIndex();

        if(index == MATERIEL_TAB) return "create-items-view.fxml";
        if(index == PERSONNEL_TAB) return "create-personnel-view.fxml";
        if(index == LIEU_TAB) return "create-salle-view.fxml";
        else return null;
    }

    /**
     * Retourne la ressource sélectionnée dans l'onglet courant
     * @return ressource
     */
    private Reservable getSelectedRessource() {
        int index = onglets.getSelectionModel().getSelectedIndex();

        if(index == MATERIEL_TAB) return materiel_list.getSelectionModel().getSelectedItem();
        if(index == PERSONNEL_TAB) return personnel_list.getSelectionModel().getSelectedItem();
        if(index == LIEU_TAB) return lieux_list.getSelectionModel().getSelectedItem();
        else return null;
    }


}
