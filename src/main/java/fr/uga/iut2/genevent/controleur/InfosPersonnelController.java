package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Personnel;
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
            setType(personnel.getTypeEmploi().getValue().getLibelle());
            setNom(personnel.getNom().get());
            setMail(personnel.getMail().get());
            setTel(personnel.getNumero().get());
        }else{
            //TODO : Log personnel non selectionné
        }
    }

    private void setNom(String nom){
        tf_nom.setText(nom);
    }

    private void setType(String type){
        tf_type.setText(type);
    }

    private void setMail(String mail){
        tf_mail.setText(mail);
    }

    private void setTel(String tel){
        tf_tel.setText(tel);
    }
}
