package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import fr.uga.iut2.genevent.vue.MaterielItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageRessourcesController extends Controller implements Initializable {


    @FXML private ListView<Materiel> materiel_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        materiel_list.setItems(getApplication().getMateriels());

        materiel_list.setCellFactory(new Callback<ListView<Materiel>, ListCell<Materiel>>() {
            @Override
            public ListCell<Materiel> call(ListView<Materiel> materielListView) {
                return new MaterielItem();
            }
        });

    }

    @FXML
    public void ajoutMaterielClick(ActionEvent e){
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-items-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter du matériel");
            stage.show();
        }
        catch (IOException ex) {

        }


    }


}
