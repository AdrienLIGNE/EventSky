package fr.uga.iut2.genevent.controleur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class LieuItemController extends Controller{

    private static LieuItemController controller;

    static {
        controller = new LieuItemController();
    }

    public static LieuItemController getController() {
        return controller;
    }

    @FXML
    private Label label_nom;
    @FXML private Label label_capacite;
    @FXML private ImageView iv;

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

    public void setImage(StringProperty lien){
        if (lien.get() != null){
            iv.setImage(new Image(lien.get()));
            lien.addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    iv.setImage(new Image(t1));
                }
            });
        }
    }

}
