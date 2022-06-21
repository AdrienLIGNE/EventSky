package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Materiel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Classe permettant de spécifier la quantité de matériel à allouer
 */
public class ChoixMaterielQuantite {

    private Spinner<Integer> nb;
    private Materiel mat;

    // Il peut être éditable (spinner) ou fixe (pour l'affichage)
    private int quantite;
    private boolean editable;


    public ChoixMaterielQuantite(Materiel m) {
        this.mat = m;
        this.editable = true;

    }

    public ChoixMaterielQuantite(Materiel m, int quantite) {
        this.mat = m;
        this.editable = false;
        this.quantite = quantite;
    }

    public void setSpinner(Spinner<Integer> s, int max) {
        this.nb = s;

        this.nb.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, 0));
    }

    public int getQuantite() {
        if(editable)
            return nb.getValue();

        return quantite;
    }

    public Materiel getMateriel() {
        return mat;
    }

    /**
     * Permet de créer une liste de choix à partir de la liste de matériel
     * @param materiels
     * @return
     */
    public static ObservableList<ChoixMaterielQuantite> createList(ObservableList<Materiel> materiels) {
        ObservableList<ChoixMaterielQuantite> liste = FXCollections.observableArrayList();

        for(Materiel m : materiels) {
            liste.add(new ChoixMaterielQuantite(m));
        }

        return liste;
    }
}
