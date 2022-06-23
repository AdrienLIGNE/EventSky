package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 * Classe permettant de lier le checkbox avec le personnel pour le choix lors de la création d'évènements
 */
public class ChoixPersonnel {

    private Personnel personnel;
    private CheckBox choix;
    private boolean select; // true si la valeur par défaut est sélectionnée

    public ChoixPersonnel(Personnel p) {
        this.personnel = p;
    }

    public void setDefaultSelect() {
        this.select = true;
    }

    public void setCheckbox(CheckBox c) {
        this.choix = c;
        this.choix.setSelected(select);
    }

    public boolean isSelected() {
        return choix.isSelected();
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    /**
     * Sélectionne la checkbox
     */
    public void check() {
        choix.setSelected(true);
    }

    /**
     * Permet de créer une liste de choix à partir de la liste de personnel
     * @param personnels
     * @return
     */
    public static ObservableList<ChoixPersonnel> createList(ObservableList<Personnel> personnels) {
        ObservableList<ChoixPersonnel> liste = FXCollections.observableArrayList();

        for(Personnel p : personnels) {
            liste.add(new ChoixPersonnel(p));
        }

        return liste;
    }
}
