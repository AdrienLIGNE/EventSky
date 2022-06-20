package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterielItemController implements Initializable {

    @FXML private Label label_nom;
    @FXML private Label label_dispo;
    @FXML private Spinner<Integer> quantite_s;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantite_s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000, 1));
        quantite_s.setEditable(true);
        quantite_s.setVisible(false);
    }

    public void setChooseQuantiteMode() {
        quantite_s.setVisible(true);
    }

    public Spinner<Integer> getQuantiteSpinner() {
        return quantite_s;
    }


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
