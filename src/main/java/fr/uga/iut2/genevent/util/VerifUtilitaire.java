package fr.uga.iut2.genevent.util;

public class VerifUtilitaire {

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
}
