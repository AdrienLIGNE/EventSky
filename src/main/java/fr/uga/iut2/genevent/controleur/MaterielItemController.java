package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypeMateriel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MaterielItemController extends Controller implements Initializable {

    private static MaterielItemController controller;
    private static HashMap<TypeMateriel, Image> icons;

    static {
        controller = new MaterielItemController();

        icons = new HashMap<>();

        icons.put(TypeMateriel.MOBILIER, new Image(JavaFXGUI.class.getResourceAsStream("/Images/materiel-icon/mobilier.png")));
        icons.put(TypeMateriel.DECORATION, new Image(JavaFXGUI.class.getResourceAsStream("/Images/materiel-icon/décoration.png")));
        icons.put(TypeMateriel.VEHICULE, new Image(JavaFXGUI.class.getResourceAsStream("/Images/materiel-icon/véhicule.png")));
        icons.put(TypeMateriel.MATERIEL_MUSICAL, new Image(JavaFXGUI.class.getResourceAsStream("/Images/materiel-icon/matériel-musical.png")));
        icons.put(TypeMateriel.OTHER, new Image(JavaFXGUI.class.getResourceAsStream("/Images/materiel-icon/autre.png")));
    }

    public static MaterielItemController getController() {
        return controller;
    }

    @FXML private Label label_nom;
    @FXML private Label label_dispo;
    @FXML private Spinner<Integer> quantite_s;
    @FXML private ImageView icon;

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

    public void setType(ObjectProperty<TypeMateriel> type) {
        icon.setImage(icons.get(type.get()));

        type.addListener(new ChangeListener<TypeMateriel>() {
            @Override
            public void changed(ObservableValue<? extends TypeMateriel> observableValue, TypeMateriel typeMateriel, TypeMateriel t1) {
                icon.setImage(icons.get(type.get()));
            }
        });
    }


}
