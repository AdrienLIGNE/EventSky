package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.LieuItemController;
import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MaterielItem extends ListCell<Materiel> {

    private MaterielItemController controller;
    private Node itemNode;

    public MaterielItem() {
        super();

        // Création du controller
        controller = new MaterielItemController();

        // On charge le fxml de l'item
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("materiel-item.fxml"));
        fxmlLoader.setController(controller);

        try {
            itemNode = fxmlLoader.load();
        }
        catch (IOException e) {
            // TODO: Logger erreur
        }
    }

    @Override
    protected void updateItem(Materiel materiel, boolean b) {
        super.updateItem(materiel, b);

        if(materiel != null) {
            controller.setNom(materiel.getLabel());
            controller.setDispo(materiel.getQuantiteDisponibleProperty());
            setGraphic(itemNode);
        }
    }

}
