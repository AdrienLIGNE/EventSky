package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.TypeMateriel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.property.StringProperty;


public class InfosMaterielController extends Controller{
    @FXML
    TextField tf_type;

    @FXML
    TextField tf_nom;

    @FXML
    TextField tf_quantite;

    private Materiel materiel;

    public void setMateriel(Materiel materiel){
        this.materiel = materiel;
        affiche();
    }

    private void affiche(){
        if (this.materiel != null){
            setType(new SimpleObjectProperty<>());
            setNom(materiel.getLabel());
            setQuantite(materiel.getQuantiteDisponibleProperty());
        }else {
            //TODO : logger pas de materiel selectionné
        }
    }

    private void setType(ObjectProperty<TypeMateriel> type){
        tf_type.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypeMateriel>() {
            @Override
            public void changed(ObservableValue<? extends TypeMateriel> observableValue, TypeMateriel typeMateriel, TypeMateriel t1) {
                tf_type.setText(t1.getLibelle());
            }
        });
    }

    private void setNom(StringProperty nom){
        tf_nom.textProperty().bind(nom);
    }

    private void setQuantite(IntegerProperty quantite) {
        tf_quantite.textProperty().bind(quantite.asString());
    }
}
