package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeLieu;
import fr.uga.iut2.genevent.util.VerifUtilitaire;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Classe permettant de gérer la création/modification d'un nouveau lieu
 */
public class CreateLieuController extends FormulaireController<Lieu> implements Initializable {

    private static CreateLieuController controller;

    static {
        controller = new CreateLieuController();
    }

    public static CreateLieuController getController() {
        return controller;
    }

    @FXML private ComboBox<TypeLieu> type_cb;

    @FXML private TextField nom_tf;
    @FXML private TextField adresse_tf;
    @FXML private TextField adresse2_tf;
    @FXML private TextField code_postal_tf;
    @FXML private TextField ville_tf;
    @FXML private Spinner<Integer> capacite_s;
    @FXML private Button btn_upload;

    private String lien_image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_cb.setItems(FXCollections.observableList(Arrays.asList(TypeLieu.values())));
        capacite_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000, 1));
        capacite_s.setEditable(true);
    }

    @FXML
    private void onUploadButtonClick(MouseEvent event){
        FileChooser fileChooser = new FileChooser();

        File fileChosen = fileChooser.showOpenDialog(nom_tf.getScene().getWindow());
        lien_image = fileChosen.toURI().toString();


        btn_upload.setText(fileChosen.toURI().toString().substring(fileChosen.toURI().toString().lastIndexOf('/') + 1));
    }

    @Override
    public void setEditMode(Lieu lieu) {
        super.setEditMode(lieu);

        // Restauration des valeurs
        type_cb.setValue(lieu.getType().getValue());
        nom_tf.setText(lieu.getNom().getValue());
        adresse_tf.setText(lieu.getAdresse().getValue());
        adresse2_tf.setText(lieu.getComplementAdresse().getValue());
        code_postal_tf.setText(lieu.getCodePostal().getValue());
        ville_tf.setText(lieu.getVille().getValue());
        capacite_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000, lieu.getCapacite().getValue()));
    }

    @Override
    public void confirmButtonClick(ActionEvent e) {

        TypeLieu type = type_cb.getValue();
        String nom = nom_tf.getText();
        String adresse[] = new String[] {adresse_tf.getText(), adresse2_tf.getText()};
        String code_postal = code_postal_tf.getText();
        String ville = ville_tf.getText();
        int capcite_max = capacite_s.getValue();


        if(verifieSaisies()) {
            if(isOnEditMode()) {
                getElementModifie().setType(type);
                getElementModifie().setNom(nom);
                getElementModifie().setCapacite(capcite_max);
                getElementModifie().setAdresse(adresse[0]);
                getElementModifie().setComplementAdresse(adresse[1]);
                getElementModifie().setVille(ville);
                getElementModifie().setCodePostal(code_postal);

                Main.LOGGER.log(Level.INFO, "Modification du lieu : " + getElementModifie().getNom().get());
            }
            else {
                Lieu lieu = new Lieu(nom, capcite_max, type);
                lieu.setAdresse(adresse[0]);
                lieu.setComplementAdresse(adresse[1]);
                lieu.setCodePostal(code_postal);
                lieu.setVille(ville);

                getModel().addLieu(lieu);
                Main.LOGGER.log(Level.INFO, "Création du lieu : " + lieu.getNom().get());
            }

            lien_image = null;
            exitStage(Controller.getStageFromTarget(e.getTarget()));
        }

    }

    @Override
    public boolean verifieSaisies() {
        boolean valide = true;

        //on reset les bordures en noir
        type_cb.setStyle("-fx-border-color: none;");
        nom_tf.setStyle("-fx-border-color: none;");
        adresse_tf.setStyle("-fx-border-color: none;");
        code_postal_tf.setStyle("-fx-border-color: none;");
        ville_tf.setStyle("-fx-border-color: none;");

        if (type_cb.getValue() == null){

            type_cb.setStyle("-fx-border-color: red; -fx-text-fill: red;");
            valide = false;

            VerifUtilitaire.createPopOver(type_cb, " Choisir un type ");
        }

        //vérification que le nom n'existe pas déjà seulement si on n'est pas en edit mode
        //si on est en edit mode on vérifie seulement si on change le nom du matériel
        if (nom_tf.getText().isEmpty()){
            nom_tf.setStyle("-fx-border-color: red;");
            valide = false;

            VerifUtilitaire.createPopOver(nom_tf, " Champ obligatoire ");
        }else {
            if (this.isOnEditMode()) {
                if (this.getElementModifie() == null || !nom_tf.getText().equals(this.getElementModifie().getNom().get())) {
                    if (VerifUtilitaire.existeDejaLieu(nom_tf.getText(), this.getModel().getLieux())) {
                        nom_tf.setStyle("-fx-border-color: red;");
                        valide = false;

                        VerifUtilitaire.createPopOver(nom_tf, " Nom déjà pris ");
                    }
                }
            } else {
                if (VerifUtilitaire.existeDejaLieu(nom_tf.getText(), this.getModel().getLieux())) {
                    nom_tf.setStyle("-fx-border-color: red;");
                    valide = false;

                    VerifUtilitaire.createPopOver(nom_tf, " Nom déjà pris ");
                }
            }
        }

        if (adresse_tf.getText().isEmpty()){
            adresse_tf.setStyle("-fx-border-color: red;");
            valide = false;

            VerifUtilitaire.createPopOver(adresse_tf, " Champ obligatoire ");
        }

        if (code_postal_tf.getText().isEmpty()){
            code_postal_tf.setStyle("-fx-border-color: red;");
            valide = false;

            VerifUtilitaire.createPopOver(code_postal_tf, " Champ obligatoire ");
        }else if (!VerifUtilitaire.verifFormatCodePostal(code_postal_tf.getText())){
            code_postal_tf.setStyle("-fx-border-color: red;");
            valide = false;

            VerifUtilitaire.createPopOver(code_postal_tf, " Format invalide ");
        }

        if (ville_tf.getText().isEmpty()){
            ville_tf.setStyle("-fx-border-color: red;");
            valide = false;

            VerifUtilitaire.createPopOver(ville_tf, " Champ obligatoire ");
        }
        return valide;
    }

    private String getImage(TypeLieu type){
        String lien = null;
        if (type.getLibelle().equals(TypeLieu.SALLE.getLibelle())){
            lien = "file:src/main/resources/Images/lieux/salle.png";
        }else if (type.getLibelle().equals(TypeLieu.GYMNASE.getLibelle())){
            lien = "file:src/main/resources/Images/lieux/gymnase.png";
        }else if (type.getLibelle().equals(TypeLieu.PLEIN_AIR.getLibelle())){
            lien = "file:src/main/resources/Images/lieux/plein-air.png";
        }
        return lien;
    }
}
