package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MaterielItemController {

    @FXML private Label label_nom;
    @FXML private Label label_dispo;

    public void setNom(StringProperty nom) {
        this.label_nom.textProperty().bind(nom);
    }

    public void setDispo(IntegerProperty quantite) {

        label_dispo.setText("Quantité: " + quantite.get());

        quantite.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                label_dispo.setText("Quantité: " + t1);
            }
        });

    }
}
