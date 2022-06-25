package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    @FXML
    public abstract void confirmButtonClick(ActionEvent e);

    public void setEditMode(E e) {
        this.element_modifie = e;
        isModification = true;

        title.setText(title.getText().replace("Ajouter", "Modifier").replace("Créer", "Modifier"));
    }

    public void setEditMode(boolean b) {
        this.isModification = b;
    }

    public boolean isOnEditMode() {
        return isModification;
    }

    public E getElementModifie() {
        return element_modifie;
    }


    public static void displayErrorField(Node n, String msg) {
        Pane container = (Pane) n.getParent();

        Label error = new Label(msg);
        error.setPadding(new Insets(5));
        error.setBackground(new Background(new BackgroundFill(Color.color(1, 0, 0), new CornerRadii(5.0), new Insets(0))));


        container.getChildren().add(error);
    }
}
