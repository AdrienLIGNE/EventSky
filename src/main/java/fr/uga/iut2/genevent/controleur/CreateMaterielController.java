package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.TypeMateriel;
import fr.uga.iut2.genevent.util.VerifUtilitaire;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreateMaterielController extends FormulaireController<Materiel> implements Initializable {

    private static CreateMaterielController controller;

    static {
        controller = new CreateMaterielController();
    }

    public static CreateMaterielController getController() {
        return controller;
    }

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
    private void onUploadButtonClick(MouseEvent event){
        FileChooser fileChooser = new FileChooser();

        File fileChosen = fileChooser.showOpenDialog(nom_tf.getScene().getWindow());
        Image image = new Image(fileChosen.toURI().toString());

        //rajouter un imageview et un label pour l'image uploadee
        //iv.setImage(image);
        //lblImageName.setText(fileChosen.toURI().toString().substring(fileChosen.toURI().toString().lastIndexOf('/') + 1));
    }

    @Override
    public void setEditMode(Materiel materiel) {
        super.setEditMode(materiel);

        typeMateriel_cb.setValue(materiel.getType().getValue());
        nom_tf.setText(materiel.getLabel().get());
        quantite_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000000, materiel.getQuantiteDisponible()));
    }

    @FXML
    public void confirmButtonClick(ActionEvent e) {
        String nom = nom_tf.getText();
        int quantite = quantite_s.getValue();
        TypeMateriel type = typeMateriel_cb.getValue();

        if (verifieSaisies()) {
            if (isOnEditMode()) {
                getElementModifie().setLabel(nom);
                getElementModifie().setQuantiteDisponible(quantite);
                getElementModifie().setType(type);
            } else {
                Materiel materiel = new Materiel(nom, type, quantite);
                getModel().addMateriel(materiel);
            }

            exitStage(Controller.getStageFromTarget(e.getTarget()));
        }
    }

    @Override
    public boolean verifieSaisies() {
        boolean b = true;
        String ancienNom = null;

        if (this.getElementModifie() != null) {
            ancienNom = this.getElementModifie().getLabel().get();
        }

        //on reset les bordures
        nom_tf.setStyle("-fx-border-color: none;");
        typeMateriel_cb.setStyle("-fx-border-color: none;");

        //vérification des champs

        //vérification que le nom n'existe pas déjà seulement si on n'est pas en edit mode
        //si on est en edit mode on vérifie seulement si on change le nom du matériel
        if (nom_tf.getText().isEmpty()){
            nom_tf.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(nom_tf, " Champ obligatoire ");
        }else {
            if (this.isOnEditMode()) {
                if (!nom_tf.getText().equals(ancienNom) | ancienNom == null) {
                    if (VerifUtilitaire.existeDejaMateriel(nom_tf.getText(), this.getModel().getMateriels())) {
                        nom_tf.setStyle("-fx-border-color: red;");
                        b = false;

                        VerifUtilitaire.createPopOver(nom_tf, " Nom déjà pris ");
                    }
                }
            } else {
                if (VerifUtilitaire.existeDejaMateriel(nom_tf.getText(), this.getModel().getMateriels())) {
                    nom_tf.setStyle("-fx-border-color: red;");
                    b = false;

                    VerifUtilitaire.createPopOver(nom_tf, " Nom déjà pris ");
                }
            }
        }

        //verification du combobox
        if (typeMateriel_cb.getValue() == null){
            typeMateriel_cb.setStyle("-fx-border-color: red;");
            b = false;

            VerifUtilitaire.createPopOver(typeMateriel_cb, " Choisir un type ");

        }
        return b;
    }
}
