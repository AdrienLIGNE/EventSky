package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.TypePersonnel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MaterielItemController {

    @FXML private Label label_nom;
    @FXML private Label label_dispo;

    public void setNom(String nom) {
        // TODO: bind le nom
        this.label_nom.setText(nom);
    }

    public void setDispo(int quantite) {
        // TODO: bind
        this.label_dispo.setText("Quantité: " + quantite);
    }
}
