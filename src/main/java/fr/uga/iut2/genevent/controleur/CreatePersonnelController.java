package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import fr.uga.iut2.genevent.util.TextUtilitaire;
import fr.uga.iut2.genevent.util.VerifUtilitaire;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;


public class CreatePersonnelController extends FormulaireController<Personnel> implements Initializable {

    private static CreatePersonnelController controller;

    static {
        controller = new CreatePersonnelController();
    }

    public static CreatePersonnelController getController() {
        return controller;
    }

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

    /**
     * Méthode qui permet de vérifier que le formulaire a été rempli correctement, et encadre les champs mal remplis en rouge et affiche un message pour l'utilisateur s'il est mal rempli
     * @return true si tout le formulaire est bien rempli, false sinon
     */
    @Override
    public boolean verifieSaisies() {
        boolean b = true;



        //on reset les bordures
        nom_tf.setStyle("-fx-border-color: none;");
        mail_tf.setStyle("-fx-border-color: none;");
        numero_tf.setStyle("-fx-border-color: none;");
        type_cb.setStyle("-fx-border-color: none;");


        //on verifie les informations entrees

        //vérification que le nom n'existe pas déjà seulement si on n'est pas en edit mode
        //si on est en edit mode on vérifie seulement si on change le nom du personnel
        if (this.isOnEditMode()) {
            //on capitalise le nom pour le comparer
            String nom;
            try {
                nom = TextUtilitaire.capitalize(nom_tf.getText());
            } catch (StringIndexOutOfBoundsException e) {
                nom = "";
                nom_tf.setStyle("-fx-border-color: red;");
                b = false;

                VerifUtilitaire.createPopOver(nom_tf, " Champ obligatoire ");
            }
            //on fait la vérification seulement si on a modifié le nom
            if (this.getElementModifie() == null || !nom.equals(this.getElementModifie().getNom().get())) {
                if (VerifUtilitaire.existeDejaPersonnel(nom, this.getModel().getPersonnels())) {
                    nom_tf.setStyle("-fx-border-color: red;");
                    b = false;

                    VerifUtilitaire.createPopOver(nom_tf, " Nom déjà pris ");
                }
            }
        } else {
            String nom;
            try {
                nom = TextUtilitaire.capitalize(nom_tf.getText());
            } catch (IndexOutOfBoundsException e) {
                nom = "";
            }
            if (nom.isEmpty()) {
                nom_tf.setStyle("-fx-border-color: red;");
                b = false;

               VerifUtilitaire.createPopOver(nom_tf," Champ obligatoire ");

            }else if (VerifUtilitaire.existeDejaPersonnel(nom, this.getModel().getPersonnels())){
                nom_tf.setStyle("-fx-border-color: red;");
                b = false;

                VerifUtilitaire.createPopOver(nom_tf," Nom déjà pris ");
            }
        }

        //verification du mail
        if (mail_tf.getText().isEmpty()) {
            mail_tf.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(mail_tf, " Champ obligatoire ");
        }else if(!VerifUtilitaire.verifMail(mail_tf.getText())){
            mail_tf.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(mail_tf, " Format invalide ");
        }

        //verification du numero
        if (numero_tf.getText().isEmpty()) {
            numero_tf.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(numero_tf, " Champ obligatoire ");
        }else if(!VerifUtilitaire.verifTelephone(numero_tf.getText())){
            numero_tf.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(numero_tf, " Format invalide ");
        }

        //verification du type
        if (type_cb.getValue() == null) {
            type_cb.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(type_cb, " Choisir un type ");
        }
        return b;
    }
}
