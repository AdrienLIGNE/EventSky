package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    }

    public void affiche(){
        if (this.materiel != null){
            setType(materiel.getType().getLibelle());
            setNom(materiel.getLabel().get());
            setQuantite(String.valueOf(materiel.getQuantiteDisponible()));
        }else {
            //TODO : logger pas de materiel selectionné
        }
    }

    private void setType(String type){
        tf_type.setText(type);
    }

    private void setNom(String nom){
        tf_nom.setText(nom);
    }

    private void setQuantite(String quantite) {
        tf_quantite.setText(quantite);
    }
}
