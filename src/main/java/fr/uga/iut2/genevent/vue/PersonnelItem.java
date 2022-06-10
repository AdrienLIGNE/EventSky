package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.EvenementItemController;
import fr.uga.iut2.genevent.controleur.PersonnelItemController;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Personnel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PersonnelItem extends ListCell<Personnel> {

    private PersonnelItemController controller;
    private Node itemNode;

    public PersonnelItem() {
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
    protected void updateItem(Personnel personnel, boolean b) {
        super.updateItem(personnel, b);

        if(personnel != null) {
            controller.setNomComplet(personnel.getNom() + " " + personnel.getPrenom());
            controller.setTypeEmploi(personnel.getTypeEmploi());
            setGraphic(itemNode);
        }
    }

}
