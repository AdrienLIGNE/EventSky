package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeLieu;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfosLieuController extends Controller {

    private static InfosLieuController controller;

    static {
        controller = new InfosLieuController();
    }

    public static InfosLieuController getController() {
        return controller;
    }

    @FXML private Label lbl_type;
    @FXML private Label lbl_nom;
    @FXML private Label lbl_adresse;
    @FXML private Label lbl_infos;
    @FXML private Label lbl_code;
    @FXML private Label lbl_ville;
    @FXML private Label lbl_capacite;

    private Lieu lieu;

    public void setLieu(Lieu lieu){
        this.lieu = lieu;
        affiche();
    }

    private void affiche(){
        if (this.lieu != null) {
            setType(lieu.getType());
            setNom(lieu.getNom());
            setAdresse(lieu.getAdresse());
            if (lieu.getComplementAdresse() == null || lieu.getComplementAdresse().get().isEmpty()) {
                setInfos(new SimpleStringProperty(""));
            } else {
                setInfos(lieu.getComplementAdresse());
            }
            setCode(lieu.getCodePostal());
            setVille(lieu.getVille());
            setCapacite(lieu.getCapacite());
        }else{
            //TODO : LOG pas de lieu sélectionné
        }
    }


    private void setType(ObjectProperty<TypeLieu> type){
        lbl_type.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypeLieu>() {
            @Override
            public void changed(ObservableValue<? extends TypeLieu> observableValue, TypeLieu typeLieu, TypeLieu t1) {
                lbl_type.setText(t1.getLibelle());
            }
        });
    }

    private void setNom(StringProperty nom){
        lbl_nom.textProperty().bind(nom);
    }

    private void setAdresse(StringProperty adresse){
        lbl_adresse.textProperty().bind(adresse);
    }

    private void setInfos(StringProperty infos){
        lbl_infos.textProperty().bind(infos);
    }

    private void setCode(StringProperty code){
        lbl_code.textProperty().bind(code);
    }

    private void setVille(StringProperty ville){
        lbl_ville.textProperty().bind(ville);
    }

    private void setCapacite(IntegerProperty capacite){
        lbl_capacite.textProperty().bind(capacite.asString());
    }
}
