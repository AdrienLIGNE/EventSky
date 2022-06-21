package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InfosPersonnelController extends Controller{
    @FXML private Label lbl_type;
    @FXML private Label lbl_nom;
    @FXML private Label lbl_mail;
    @FXML private Label lbl_tel;

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
        lbl_nom.textProperty().bind(nom);
    }

    private void setType(ObjectProperty<TypePersonnel> type){
        lbl_type.setText(type.get().getLibelle());
        type.addListener(new ChangeListener<TypePersonnel>() {
            @Override
            public void changed(ObservableValue<? extends TypePersonnel> observableValue, TypePersonnel typePersonnel, TypePersonnel t1) {
                lbl_type.setText(t1.getLibelle());
            }
        });
    }

    private void setMail(StringProperty mail){
        lbl_mail.textProperty().bind(mail);
    }

    private void setTel(StringProperty tel){
        lbl_tel.textProperty().bind(tel);
    }
}
