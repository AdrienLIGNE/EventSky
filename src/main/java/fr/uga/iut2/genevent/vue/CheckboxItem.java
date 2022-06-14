package fr.uga.iut2.genevent.vue;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CheckboxItem extends ListCell<ChoixMaterielQuantite> {

    public CheckboxItem() {
        super();


    }

    @Override
    protected void updateItem(ChoixMaterielQuantite materiel, boolean b) {
        super.updateItem(materiel, b);

        if(materiel != null) {
            HBox test = new HBox();
            Label label = new Label(materiel.getMateriel().getLabel());

            test.getChildren().addAll(label, materiel.nb);

            setGraphic(test);

        }

    }


}
