package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import fr.uga.iut2.genevent.modele.Reservable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;

import java.util.Collection;

/**
 * classe contenant des méthodes utiles notamment pour les verifications de formulaire
 */
public class VerifUtilitaire {

    /**
     * Méthode permettant de vérifier le format du mail
     * @param s mail
     * @return vrai si le format est bon, faux sinon
     */
    public static boolean verifMail(String s) {
        String[] s2 = s.split("@");

        //on vérifie qu'il y a bien un @ (un seul) dans le mail et que le premier membre n'est pas vide
        if (s2.length != 2 | s2[0].isEmpty()) {
            return false;
        }

        //on vérifie qu'il y a un point (un seul) après le @ et que le deuxieme membre n'est pas vide
        if (s2[1].isEmpty() | s2[1].split("\\.").length != 2) {
            return false;
        }

        //on vérifie si les membres avant et après le point sont pas vides
        if (s2[1].split("\\.")[0].isEmpty() | (s2[1].split("\\.")[1].isEmpty() | !s2[1].split("\\.")[1].matches("com|fr|be|it|gouv|uk|org|cn|net|de|eu|bg|jp|hu"))) {
            return false;
        }
        return true;
    }


    /**
     * méthode permettant de vérifier le format d'un numérode téléphone
     * (10 chiffres et commence bien par 0)
     * @param s chaine à vérifier
     * @return vrai si le format est bon, faux sinon
     */
    public static boolean verifTelephone(String s) {
        //on check que c'est bien des nombres
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        //on check que le numéro a bien une longueur de 10
        if (s.length() != 10) {
            return false;
        }

        //on regarde si le numéro commence bien par 0
        if (!s.substring(0, 1).equals("0")) {
            return false;
        }

        return true;
    }

    /**
     * Méthode permettant de savoir si l'objet que l'on veut créer n'existe pas déjà, en comparant le nom du nouvel objet et les noms des objets dejà créés
     * @param nom nom du nouvel objet
     * @param materiel liste d'objets déjà existants
     * @return vrai si cet objet existe deja, faux sinon
     */
    public static boolean existeDejaMateriel(String nom, Collection<Materiel> materiel){
        boolean existe = false;
        for (Materiel objet : materiel){
            if (objet.getLabel().get().equals(nom)){
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Méthode permettant de savoir si le lieu que l'on veut créer n'existe pas déjà, en comparant le nom du nouvel lieu et les noms des lieux dejà créés
     * @param nom nom du nouveau lieu
     * @param lieux lieux deja existants
     * @return vrai si ce lieu existe deja, faux sinon
     */
    public static boolean existeDejaLieu(String nom, Collection<Lieu> lieux){
        boolean existe = false;
        for (Lieu lieu : lieux){
            if (lieu.getNom().get().equals(nom)){
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Méthode permettant de savoir si le personnel que l'on veut créer n'existe pas déjà, en comparant le nom de la nouvelle personne et les noms du personnel dejà créé
     * @param nom nom du nouveau personnel
     * @param personnel personnel deja existant
     * @return vrai si cette personne existe deja, faux sinon
     */
    public static boolean existeDejaPersonnel(String nom, Collection<Personnel> personnel){
        boolean existe = false;
        for (Personnel personne : personnel){
            if (personne.getNom().get().equals(nom)){
                existe = true;
            }
        }
        return existe;
    }

    /**
     * méthode permettant de savoir si le format du code postal est bon.
     * le code postal est bon si il a le format d'un code postal de la france métropolitaine : 6 chiffres et les 2 premiers chiffres représentent un numéro de département
     * @param code code postal à vérifier
     * @return vrai si il a le bon format, faux sinon
     */
    public static boolean verifFormatCodePostal(String code){
        boolean verifie = true;
        if (code.length() != 5){
            verifie = false;
        }if (!code.matches("^(0[1-9]|[1-8]\\d|9[0-5])\\d{3}$")){
            verifie = false;
        }
        return  verifie;
    }


    public static void createPopOver(Node node, String msg){
        Label label = new Label(msg);
        label.setStyle("-fx-text-fill: red;");

        PopOver popOver = new PopOver(label);
        popOver.arrowIndentProperty().bind(new SimpleDoubleProperty(1));
        popOver.arrowSizeProperty().bind(new SimpleDoubleProperty(5));
        popOver.show(node);

        //lorsqu'on clique sur le champ, on fait disparaitre le popover (sinon il disparait pas tout de suite)
        node.setOnMouseClicked(MouseEvent -> {
            popOver.hide();
        });

        // pour empêcher le crash lors de la fermeture de la fenêtre lorsque le popover est toujours ouvert
        // (sinon ça crash car le popover disparait pas instantanément, et quand la fenetre n'existe plus ça déclenche une erreur)
        popOver.setFadeOutDuration(Duration.ZERO);
    }
}
