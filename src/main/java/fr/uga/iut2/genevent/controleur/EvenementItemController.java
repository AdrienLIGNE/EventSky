package fr.uga.iut2.genevent.controleur;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe controleur permettant de manipuler les items représentant chaque évènements
 */
public class EvenementItemController {

    @FXML private Label label_titre;
    @FXML private Label label_date;

    public void setTitre(StringProperty titre) {
        this.label_titre.textProperty().bind(titre);
    }

    public void setDates(LocalDate dateDebut, LocalDate dateFin) {
        if(dateDebut == null | dateFin == null) {
            label_date.setText("Inconnus");
        }
        else {
            label_date.setText(dateDebut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                + "  -  " + dateFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

}
