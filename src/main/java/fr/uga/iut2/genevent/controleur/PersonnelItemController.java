package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonnelItemController {

    @FXML private Label label_nom_complet;
    @FXML private Label label_type_emploi;

    public void setNomComplet(StringProperty nom) {
        // TODO: bind le nom
        this.label_nom_complet.textProperty().bind(nom);
    }

    public void setTypeEmploi(TypePersonnel type) {
        // TODO: bind
        this.label_type_emploi.setText(type.getLibelle());
    }
}
