package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Materiel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Classe permettant de spécifier la quantité de matériel à allouer
 */
public class ChoixMaterielQuantite {

    public final Spinner<Integer> nb = new Spinner<Integer>();
    private Materiel mat;

    public ChoixMaterielQuantite(Materiel m) {
        nb.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        this.mat = m;
    }

    public int getQuantite() {
        return nb.getValue();
    }

    public Materiel getMateriel() {
        return mat;
    }
}
