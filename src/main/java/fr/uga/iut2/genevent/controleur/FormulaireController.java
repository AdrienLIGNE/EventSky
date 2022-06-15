package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller spécial qui permet de gérer des formulaires
 * @param <E> type d'élément que gère le formulaire (Evenement, Materiel, Lieu...)
 */
public abstract class FormulaireController<E> extends Controller{

    // si isModification est à true alors cela veut dire que nous somme en mode édition
    private boolean isModification;
    private E element_modifie;

    @FXML private Label title;

    /**
     * Vérifie les informations saisies par l'utilisateur
     * @return vrai si tout est correcte.
     */
    public abstract boolean verifieSaisies();

    public void setEditMode(E e) {
        this.element_modifie = e;
        isModification = true;

        title.setText(title.getText().replace("Ajouter", "Modifier"));
    }

    public boolean isOnEditMode() {
        return isModification;
    }

    public E getElementModifie() {
        return element_modifie;
    }
}
