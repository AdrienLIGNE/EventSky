package fr.uga.iut2.genevent.controleur;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LieuItemController {

    @FXML
    private Label label_nom;
    @FXML private Label label_capacite;

    public void setNom(StringProperty nom) {
        //TODO: bind
        this.label_nom.textProperty().bind(nom);
    }

    public void setCapacite(String capacite) {
        this.label_capacite.setText(capacite);
    }

}
