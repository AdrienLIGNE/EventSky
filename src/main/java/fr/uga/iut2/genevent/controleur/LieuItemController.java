package fr.uga.iut2.genevent.controleur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LieuItemController {

    @FXML
    private Label label_nom;
    @FXML private Label label_capacite;

    public void setNom(StringProperty nom) {
        this.label_nom.textProperty().bind(nom);
    }

    public void setCapacite(IntegerProperty capacite) {

        label_capacite.setText("Capacité: " + capacite.get() + " personnes");

        capacite.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                label_capacite.setText("Capacité: " + t1 + " personnes");
            }
        });
    }

}
