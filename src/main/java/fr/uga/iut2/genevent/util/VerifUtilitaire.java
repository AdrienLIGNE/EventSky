package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Reservable;

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
        if (s2[1].split("\\.")[0].isEmpty() | s2[1].split("\\.")[1].isEmpty()) {
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
}
