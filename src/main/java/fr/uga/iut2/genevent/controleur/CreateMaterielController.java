package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.TypeMateriel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreateMaterielController extends Controller implements Initializable {

    @FXML private ComboBox<TypeMateriel> typeMateriel_cb;

    @FXML private TextField nom_tf;
    @FXML private Spinner<Integer> quantite_s;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajout de la liste de type de matériel
        typeMateriel_cb.setItems(FXCollections.observableList(Arrays.asList(TypeMateriel.values())));

        quantite_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000000, 1));
        quantite_s.setEditable(true);

    }

    @FXML
    private void confirmButtonClick(ActionEvent e) {
        String nom = nom_tf.getText();
        int quantite = quantite_s.getValue();
        TypeMateriel type = typeMateriel_cb.getValue();

        Materiel materiel = new Materiel(nom, type, quantite);
        getApplication().addMateriel(materiel);
        exitStage(Controller.getStageFromNode((Node) e.getTarget()));
    }
}
