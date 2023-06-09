module genevent {
    requires commons.validator;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires json.simple;
    requires org.controlsfx.controls;

    opens fr.uga.iut2.genevent.vue to javafx.fxml;
    opens fr.uga.iut2.genevent.controleur to javafx.fxml;
    opens fr.uga.iut2.genevent.test to javafx.fxml, javafx.graphics;

    exports fr.uga.iut2.genevent;
}
