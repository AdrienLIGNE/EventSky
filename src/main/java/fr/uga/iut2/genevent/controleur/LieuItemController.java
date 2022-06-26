package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypeLieu;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;


public class LieuItemController extends Controller{

    private static LieuItemController controller;
    private static HashMap<TypeLieu, Image> icons;

    static {
        controller = new LieuItemController();

        icons = new HashMap<>();

        icons.put(TypeLieu.GYMNASE, new Image(JavaFXGUI.class.getResourceAsStream("/Images/lieux-icon/gymnase.png")));
        icons.put(TypeLieu.PLEIN_AIR, new Image(JavaFXGUI.class.getResourceAsStream("/Images/lieux-icon/plein-air.png")));
        icons.put(TypeLieu.SALLE, new Image(JavaFXGUI.class.getResourceAsStream("/Images/lieux-icon/salle.png")));
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

    public void setType(ObjectProperty<TypeLieu> type) {
        iv.setImage(icons.get(type.get()));

        type.addListener(new ChangeListener<TypeLieu>() {
            @Override
            public void changed(ObservableValue<? extends TypeLieu> observableValue, TypeLieu typeLieu, TypeLieu t1) {
                iv.setImage(icons.get(type.get()));
            }
        });
    }

}
