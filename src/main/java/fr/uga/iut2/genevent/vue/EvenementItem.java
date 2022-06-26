package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.EvenementItemController;
import fr.uga.iut2.genevent.modele.Evenement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

/**
 * Cette classe permet de représenter chaque évènements sous forme d'item dans une liste (ListView)
 */
public class EvenementItem extends ListCell<Evenement> {

    private EvenementItemController controller;
    private Node itemNode;

    public EvenementItem() {
        super();

        // Création du controller
        controller = new EvenementItemController();

        // On charge le fxml de l'item
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("event-item.fxml"));
        fxmlLoader.setController(controller);

        try {
            itemNode = fxmlLoader.load();
        }
        catch (IOException e) {
            // TODO: Logger erreur
        }
    }

    @Override
    protected void updateItem(Evenement evenement, boolean b) {
        super.updateItem(evenement, b);

        if(evenement != null) {
            controller.setTitre(evenement.getNomEvenement());
            controller.setDates(evenement.getDateDebut(), evenement.getDateFin());
            controller.setType(evenement.getType());
            setGraphic(itemNode);
        }
        else setGraphic(null);
    }
}
