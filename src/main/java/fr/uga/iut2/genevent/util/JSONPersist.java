package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.MainModel;
import fr.uga.iut2.genevent.modele.Materiel;
import fr.uga.iut2.genevent.modele.Personnel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONPersist {

    /**
     * Permet de sauvegarder l'état en JSON
     * @param app
     */
    public static void sauvegardeEtat(MainModel app) {


        JSONObject jsonObject = new JSONObject();


        // On commence par sauvegarder la liste de matériel
        JSONArray materiel_list = new JSONArray();

        for(Materiel m : app.getMateriels()) {

            JSONObject materiel = new JSONObject();

            materiel.put("nom", m.getLabel().getValue());
            materiel.put("quantite", m.getQuantiteDisponible());
            materiel.put("type", m.getType());



            materiel_list.add(materiel);
        }

        jsonObject.put("materiels", materiel_list);


        // puis on sauvegarde la liste de personnel
        JSONArray personnel_list = new JSONArray();

        for(Personnel p : app.getPersonnels()) {

            JSONObject personnel = new JSONObject();

            personnel.put("nom", p.getNom().getValue());
            personnel.put("mail", p.getMail().getValue());
            personnel.put("numero", p.getNumero().getValue());
            personnel.put("type", p.getTypeEmploi().getValue());

            personnel_list.add(personnel);
        }

        jsonObject.put("personnel", personnel_list);

        // Sauvegarde de la liste de lieux
        JSONArray lieux_list = new JSONArray();

        for(Lieu l : app.getLieux()) {

            JSONObject lieu = new JSONObject();

            lieu.put("nom", l.getNom().getValue());




            lieux_list.add(lieu);
        }

        jsonObject.put("lieux", lieux_list);

        System.out.println(jsonObject.toJSONString());
    }

    public static void restaureEtat(MainModel app) {

    }

}
