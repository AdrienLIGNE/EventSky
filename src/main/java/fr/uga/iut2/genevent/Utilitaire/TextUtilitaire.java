package fr.uga.iut2.genevent.Utilitaire;

public class TextUtilitaire {

    /**
     * Met le texte avec la première lettre en majuscule
     */
    public static String capitalize(String s) {
        String[] blocks = s.split("-");

        if(blocks.length == 1) {
            return Character.toUpperCase(blocks[0].charAt(0)) + blocks[0].substring(1).toLowerCase();
        }
        else {
            for(int i = 0; i < blocks.length; i++) {
                blocks[i] = capitalize(blocks[i]);
            }
            return String.join("-", blocks);
        }
     }


}
