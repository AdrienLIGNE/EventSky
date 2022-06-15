package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.TypeMateriel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreatePersonnelController extends FormulaireController<Personnel> implements Initializable {

    @FXML private ComboBox<TypePersonnel> type_cb;

    @FXML private TextField nom_tf;
    @FXML private TextField mail_tf;
    @FXML private TextField numero_tf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_cb.setItems(FXCollections.observableList(Arrays.asList(TypePersonnel.values())));
    }

    @Override
    public void setEditMode(Personnel personnel) {
        super.setEditMode(personnel);

        type_cb.setValue(personnel.getTypeEmploi());
        nom_tf.setText(personnel.getNom());
        mail_tf.setText(personnel.getMail());
        numero_tf.setText(personnel.getNumero());
    }

    @FXML
    private void confirmButtonClick(ActionEvent e) {
        String nom = nom_tf.getText();
        String mail = mail_tf.getText();
        String numero = numero_tf.getText();

        TypePersonnel type = type_cb.getValue();

        // TODO: Vérifier les informations saisies

        if(isOnEditMode()) {
            getElementModifie().setNom(nom);
            getElementModifie().setMail(mail);
            getElementModifie().setNumero(numero);
            getElementModifie().setType(type);
        }
        else {
            Personnel personnel = new Personnel(nom, "", type);
            personnel.setMail(mail);
            personnel.setNumero(numero);
            getApplication().addPersonnel(personnel);
        }

        exitStage(Controller.getStageFromNode((Node) e.getTarget()));
    }

    @Override
    public boolean verifieSaisies() {
        return true;
    }
}
