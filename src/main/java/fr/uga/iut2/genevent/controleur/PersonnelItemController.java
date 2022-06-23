package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PersonnelItemController extends Controller implements Initializable {

    private static PersonnelItemController controller;

    static {
        controller = new PersonnelItemController();
    }

    public static PersonnelItemController getController() {
        return controller;
    }

    @FXML private Label label_nom_complet;
    @FXML private Label label_type_emploi;
    @FXML private CheckBox checkbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkbox.setVisible(false);
    }

    /**
     * Permet de rendre l'item sélectionnable (lors de la phase de choix de personnel
     * dans la création d'évènements)
     */
    public void setSelectionnable() {
        checkbox.setVisible(true);
    }

    public void setNomComplet(StringProperty nom) {
        this.label_nom_complet.textProperty().bind(nom);
    }

    public void setTypeEmploi(ObjectProperty<TypePersonnel> type) {
        label_type_emploi.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypePersonnel>() {
            @Override
            public void changed(ObservableValue<? extends TypePersonnel> observableValue, TypePersonnel typePersonnel, TypePersonnel t1) {
                label_type_emploi.setText(t1.getLibelle());
            }
        });
    }

    @FXML
    private void itemClick(MouseEvent e) {
        checkbox.setSelected(!checkbox.isSelected());
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }
}
