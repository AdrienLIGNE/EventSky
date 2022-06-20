package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonnelItemController {

    @FXML private Label label_nom_complet;
    @FXML private Label label_type_emploi;

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
}
