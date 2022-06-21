package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InfosPersonnelController extends Controller{
    @FXML private TextField tf_type;
    @FXML private TextField tf_nom;
    @FXML private TextField tf_mail;
    @FXML private TextField tf_tel;

    private Personnel personnel;


    public void setPersonnel(Personnel personnel){
        this.personnel = personnel;
        affiche();
    }

    private void affiche(){
        if (this.personnel != null){
            setType(personnel.getTypeEmploi());
            setNom(personnel.getNom());
            setMail(personnel.getMail());
            setTel(personnel.getNumero());
        }else{
            //TODO : Log personnel non selectionné
        }
    }

    private void setNom(StringProperty nom){
        tf_nom.textProperty().bind(nom);
    }

    private void setType(ObjectProperty<TypePersonnel> type){
        tf_type.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypePersonnel>() {
            @Override
            public void changed(ObservableValue<? extends TypePersonnel> observableValue, TypePersonnel typePersonnel, TypePersonnel t1) {
                tf_type.setText(t1.getLibelle());
            }
        });
    }

    private void setMail(StringProperty mail){
        tf_mail.textProperty().bind(mail);
    }

    private void setTel(StringProperty tel){
        tf_tel.textProperty().bind(tel);
    }
}
