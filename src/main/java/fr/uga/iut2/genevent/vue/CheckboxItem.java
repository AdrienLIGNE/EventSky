package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.Materiel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CheckboxItem extends ListCell<TestMateriel> {

    public CheckboxItem() {
        super();


    }

    @Override
    protected void updateItem(TestMateriel materiel, boolean b) {
        super.updateItem(materiel, b);

        if(materiel != null) {
            HBox test = new HBox();
            Label label = new Label(materiel.getMateriel().getLabel());

            test.getChildren().addAll(label, materiel.nb);

            setGraphic(test);

        }

    }


}
