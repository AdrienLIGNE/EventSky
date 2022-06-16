package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.LieuItemController;
import fr.uga.iut2.genevent.controleur.ManageRessourcesController;
import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;

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

            ContextMenu contextMenu = new ContextMenu();
            MenuItem modifier = new MenuItem("Modifier");

            modifier.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ManageRessourcesController.editRessource(materiel);
                }
            });

            contextMenu.getItems().add(modifier);

            setContextMenu(contextMenu);
        }
        else setGraphic(null);
    }

}
