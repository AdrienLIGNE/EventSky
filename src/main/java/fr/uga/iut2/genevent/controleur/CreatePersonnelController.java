package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import fr.uga.iut2.genevent.util.TextUtilitaire;
import fr.uga.iut2.genevent.util.VerifUtilitaire;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreatePersonnelController extends FormulaireController<Personnel> implements Initializable {

    @FXML
    private ComboBox<TypePersonnel> type_cb;

    @FXML
    private TextField nom_tf;
    @FXML
    private TextField mail_tf;
    @FXML
    private TextField numero_tf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_cb.setItems(FXCollections.observableList(Arrays.asList(TypePersonnel.values())));
    }

    @FXML
    private void onUploadButtonClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        File fileChosen = fileChooser.showOpenDialog(nom_tf.getScene().getWindow());
        Image image = new Image(fileChosen.toURI().toString());

        //rajouter un imageview et un label pour l'image uploadee
        //iv.setImage(image);
        //lblImageName.setText(fileChosen.toURI().toString().substring(fileChosen.toURI().toString().lastIndexOf('/') + 1));
    }

    @Override
    public void setEditMode(Personnel personnel) {
        super.setEditMode(personnel);

        type_cb.setValue(personnel.getTypeEmploi().getValue());
        nom_tf.setText(personnel.getNom().getValue());
        mail_tf.setText(personnel.getMail().getValue());
        numero_tf.setText(personnel.getNumero().getValue());
    }

    @FXML
    public void confirmButtonClick(ActionEvent e) {
        String nom = nom_tf.getText();
        String mail = mail_tf.getText();
        String numero = numero_tf.getText();

        TypePersonnel type = type_cb.getValue();

        if (verifieSaisies()) {
            if (isOnEditMode()) {
                getElementModifie().setNom(nom);
                getElementModifie().setMail(mail);
                getElementModifie().setNumero(numero);
                getElementModifie().setType(type);
            } else {
                // Création d'un nouveau personnel
                Personnel personnel = new Personnel(nom,  type);
                personnel.setMail(mail);
                personnel.setNumero(numero);
                getModel().addPersonnel(personnel);
            }
            exitStage(Controller.getStageFromTarget(e.getTarget()));
        }
    }

    @Override
    public boolean verifieSaisies() {
        boolean b = true;

        //on reset les bordures
        nom_tf.setStyle("-fx-border-color: black;");
        mail_tf.setStyle("-fx-border-color: black;");
        numero_tf.setStyle("-fx-border-color: black;");
        type_cb.setStyle("-fx-border-color: black;");


        //on verifie les informations entrees

        //vérification que le nom n'existe pas déjà seulement si on n'est pas en edit mode
        //si on est en edit mode on vérifie seulement si on change le nom du personnel
        if (this.isOnEditMode()) {
            //on capitalise le nom pour le comparer
            String nom;
            try {
                nom = TextUtilitaire.capitalize(nom_tf.getText());
            } catch (IndexOutOfBoundsException e) {
                nom = "";
            }
            //on fait la vérification seulement si on a modifié le nom
            if (this.getElementModifie() == null || !nom.equals(this.getElementModifie().getNom().get())) {
                if (VerifUtilitaire.existeDejaPersonnel(nom, this.getModel().getPersonnels())) {
                    nom_tf.setStyle("-fx-border-color: red;");
                    b = false;
                }
            }
        } else {
            String nom;
            try {
                nom = TextUtilitaire.capitalize(nom_tf.getText());
            } catch (IndexOutOfBoundsException e) {
                nom = "";
            }
            if (nom.isEmpty() || VerifUtilitaire.existeDejaPersonnel(nom, this.getModel().getPersonnels())) {
                nom_tf.setStyle("-fx-border-color: red;");
                b = false;
            }
        }
        if (mail_tf.getText().isEmpty() | !VerifUtilitaire.verifMail(mail_tf.getText())) {
            mail_tf.setStyle("-fx-border-color: red;");
            b = false;
        }
        if (numero_tf.getText().isEmpty() | !VerifUtilitaire.verifTelephone(numero_tf.getText())) {
            numero_tf.setStyle("-fx-border-color: red;");
            b = false;
        }
        if (type_cb.getValue() == null) {
            type_cb.setStyle("-fx-border-color: red;");
            b = false;
        }
        return b;
    }
}
