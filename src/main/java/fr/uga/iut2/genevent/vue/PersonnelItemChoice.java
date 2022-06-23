package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.PersonnelItemController;
import fr.uga.iut2.genevent.modele.Personnel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PersonnelItemChoice extends ListCell<ChoixPersonnel> {

    private PersonnelItemController controller;
    private Node itemNode;

    public PersonnelItemChoice() {
        super();

        // Création du controller
        controller = new PersonnelItemController();

        // On charge le fxml de l'item
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personnel-item.fxml"));
        fxmlLoader.setController(controller);

        try {
            itemNode = fxmlLoader.load();
        }
        catch (IOException e) {
            // TODO: Logger erreur
        }
    }

    @Override
    protected void updateItem(ChoixPersonnel choixPersonnel, boolean b) {
        super.updateItem(choixPersonnel, b);

        if(choixPersonnel != null) {
            controller.setNomComplet(choixPersonnel.getPersonnel().getNom());
            controller.setTypeEmploi(choixPersonnel.getPersonnel().getTypeEmploi());
            controller.setSelectionnable();

            choixPersonnel.setCheckbox(controller.getCheckbox());
            setGraphic(itemNode);
        }
        else setGraphic(null);
    }
}
