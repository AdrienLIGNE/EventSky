package fr.uga.iut2.genevent.controleur;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateSalleController extends Controller{

    private static CreateSalleController controller;

    static {
        controller = new CreateSalleController();
    }

    public static CreateSalleController getController() {
        return controller;
    }

    @FXML
    private VBox root;

    @FXML
    private ImageView iv;

    @FXML
    private Label lblImageName;

    private String typeSalle;
    private String nom;
    private String adresse;
    private int capacite;

    @FXML
    private void onUploadButtonClick(MouseEvent event){
        FileChooser fileChooser = new FileChooser();

        File fileChosen = fileChooser.showOpenDialog(root.getScene().getWindow());
        Image image = new Image(fileChosen.toURI().toString());

        //rajouter un imageview et un label pour l'image uploadee

        //iv.setImage(image);
        //lblImageName.setText(fileChosen.toURI().toString().substring(fileChosen.toURI().toString().lastIndexOf('/') + 1));
    }

    @FXML
    private void onQuitButtonClick(MouseEvent event){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
