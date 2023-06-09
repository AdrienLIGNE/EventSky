package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.TypeMateriel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.beans.property.StringProperty;


public class InfosMaterielController extends Controller{

    private static InfosMaterielController controller;

    static {
        controller = new InfosMaterielController();
    }

    public static InfosMaterielController getController() {
        return controller;
    }

    @FXML
    private Label lbl_type;

    @FXML
    private Label lbl_nom;

    @FXML
    private Label lbl_quantite;

    private Materiel materiel;

    public void setMateriel(Materiel materiel){
        this.materiel = materiel;
        affiche();
    }

    private void affiche(){
        if (this.materiel != null){
            setType(materiel.getType());
            setNom(materiel.getLabel());
            setQuantite(materiel.getQuantiteDisponibleProperty());
        }else {
            //TODO : logger pas de materiel selectionné
        }
    }

    private void setType(ObjectProperty<TypeMateriel> type){
        lbl_type.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypeMateriel>() {
            @Override
            public void changed(ObservableValue<? extends TypeMateriel> observableValue, TypeMateriel typeMateriel, TypeMateriel t1) {
                lbl_type.setText(t1.getLibelle());
            }
        });
    }

    private void setNom(StringProperty nom){
        lbl_nom.textProperty().bind(nom);
    }

    private void setQuantite(IntegerProperty quantite) {
        lbl_quantite.textProperty().bind(quantite.asString());
    }
}
