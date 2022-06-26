package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PersonnelItemController extends Controller implements Initializable {

    private static PersonnelItemController controller;
    private static HashMap<TypePersonnel, Image> icons;

    static {
        controller = new PersonnelItemController();

        icons = new HashMap<>();

        icons.put(TypePersonnel.BARMAN, new Image(JavaFXGUI.class.getResourceAsStream("/Images/personnel-icon/barman.png")));
        icons.put(TypePersonnel.MENAGE, new Image(JavaFXGUI.class.getResourceAsStream("/Images/personnel-icon/agent-de-ménage.png")));
        icons.put(TypePersonnel.VIGILE, new Image(JavaFXGUI.class.getResourceAsStream("/Images/personnel-icon/agent-de-sécurité.png")));

    }

    public static PersonnelItemController getController() {
        return controller;
    }

    @FXML private Label label_nom_complet;
    @FXML private Label label_type_emploi;
    @FXML private CheckBox checkbox;
    @FXML private ImageView icon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkbox.setVisible(false);
    }

    /**
     * Permet de rendre l'item sélectionnable (lors de la phase de choix de personnel
     * dans la création d'évènements)
     */
    public void setSelectionnable() {
        checkbox.setVisible(true);
    }

    public void setNomComplet(StringProperty nom) {
        this.label_nom_complet.textProperty().bind(nom);
    }

    public void setTypeEmploi(ObjectProperty<TypePersonnel> type) {
        label_type_emploi.setText(type.get().getLibelle());
        icon.setImage(icons.get(type.get()));

        type.addListener(new ChangeListener<TypePersonnel>() {
            @Override
            public void changed(ObservableValue<? extends TypePersonnel> observableValue, TypePersonnel typePersonnel, TypePersonnel t1) {
                label_type_emploi.setText(t1.getLibelle());
                icon.setImage(icons.get(type.get()));
            }
        });
    }

    @FXML
    private void itemClick(MouseEvent e) {
        checkbox.setSelected(!checkbox.isSelected());
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }
}
