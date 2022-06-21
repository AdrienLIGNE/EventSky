package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.LieuItemController;
import fr.uga.iut2.genevent.controleur.ManageRessourcesController;
import fr.uga.iut2.genevent.controleur.MaterielItemController;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MaterielItem extends ListCell<Materiel> {

    private MaterielItemController controller;
    private Node itemNode;
    private Evenement evenement;

    public MaterielItem() {
        super();
        init();
    }

    public MaterielItem(Evenement evenement) {
        super();
        this.evenement = evenement;
        init();
    }

    private void init() {

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

            // On regarde si l'evenement est null pour savoir si il faut afficher tout le matériel disponible ou seulement celui de l'évenement
            if(evenement == null) {
                controller.setDispo(materiel.getQuantiteDisponibleProperty());
            }
            else {
                controller.setDispo(new SimpleIntegerProperty(materiel.getQuantiteAffecte(evenement)));
            }

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
