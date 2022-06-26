package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.LieuItemController;
import fr.uga.iut2.genevent.controleur.PersonnelItemController;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Personnel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class LieuItem extends ListCell<Lieu> {

    private LieuItemController controller;
    private Node itemNode;

    public LieuItem() {
        super();

        // Création du controller
        controller = new LieuItemController();

        // On charge le fxml de l'item
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lieu-item.fxml"));
        fxmlLoader.setController(controller);

        try {
            itemNode = fxmlLoader.load();
        }
        catch (IOException e) {
            // TODO: Logger erreur
        }
    }

    @Override
    protected void updateItem(Lieu lieu, boolean b) {
        super.updateItem(lieu, b);

        if(lieu != null) {
            controller.setNom(lieu.getNom());
            controller.setCapacite(lieu.getCapacite());
            controller.setImage(lieu.getLien_image());
            setGraphic(itemNode);
        }
        else setGraphic(null);
    }

}
