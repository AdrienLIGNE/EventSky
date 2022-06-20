package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.logging.Level;

public class JSONPersist {

    /**
     * Permet de sauvegarder l'état en JSON
     * @param app
     */
    public static void sauvegardeEtat(MainModel app) {

        // Si le fichier existe déjà, on le vide
        File f = new File("persistence/data.json");

        if (f.exists()) {
            f.delete();
        }

        JSONObject jsonObject = new JSONObject();


        // On commence par sauvegarder la liste de matériel
        JSONArray materiel_list = new JSONArray();

        for(Materiel m : app.getMateriels()) {

            JSONObject materiel = new JSONObject();

            materiel.put("nom", m.getLabel().getValue());
            materiel.put("quantite", m.getQuantiteDisponible());
            materiel.put("type", m.getType().toString());



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
            personnel.put("type", p.getTypeEmploi().getValue().toString());

            personnel_list.add(personnel);
        }

        jsonObject.put("personnel", personnel_list);

        // Sauvegarde de la liste de lieux
        JSONArray lieux_list = new JSONArray();

        for(Lieu l : app.getLieux()) {

            JSONObject lieu = new JSONObject();

            lieu.put("nom", l.getNom().getValue());
            lieu.put("adresse", l.getAdresse().getValue());
            lieu.put("complement", l.getComplementAdresse().getValue());
            lieu.put("code_postal", l.getCodePostal().getValue());
            lieu.put("ville", l.getVille().getValue());
            lieu.put("capacite", l.getCapacite().getValue());
            lieu.put("type", l.getType().getValue().toString());


            lieux_list.add(lieu);
        }

        jsonObject.put("lieux", lieux_list);

        // Sauvegarde de la liste des événements

        JSONArray evenements_list = new JSONArray();

        for(Evenement e : app.getEvenements()) {

            JSONObject evenement = new JSONObject();

            evenement.put("nom", e.getNomEvenement().getValue());
            evenement.put("date_debut", e.getDateDebut().toString());
            evenement.put("date_fin", e.getDateFin().toString());
            evenement.put("lieu", e.getLieu().getNom().getValue());
            evenement.put("confirme", e.isConfirmed());
            evenement.put("type", e.getType().getValue().toString());
            evenement.put("nb_personnes", e.getNbPersonnes().getValue());
            evenement.put("lieu", e.getLieu().getNom().getValue());

            // On sauvegarde la liste du matériel
            JSONArray materiel_list_evenement = new JSONArray();

            for(Materiel m : e.getMateriel()) {
                JSONObject materiel = new JSONObject();

                materiel.put("nom", m.getLabel().getValue());
                materiel.put("quantite", m.getQuantiteAffecte(e));
                materiel_list_evenement.add(materiel);
            }

            evenement.put("materiel", materiel_list_evenement);

            // On sauvegarde la liste du personnel
            JSONArray personnel_list_evenement = new JSONArray();

            for(Personnel p : e.getPersonnel()) {
                personnel_list_evenement.add(p.getNom().getValue());
            }

            evenement.put("personnel", personnel_list_evenement);

            evenements_list.add(evenement);
        }

        jsonObject.put("evenements", evenements_list);


        try{
            FileWriter fileWriter = new FileWriter("persistence/save.json");

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        }
        catch (IOException e) {
            Main.LOGGER.log(Level.SEVERE, "Impossibe de sauvegarder les données");
        }

    }

    /**
     * Récupère l'état en JSON
     * @param app
     */
    public static void restaureEtat(MainModel app) {

            // On vérifie si le fichier existe
            File file = new File("persistence/save.json");
            if(!file.exists()) {
                Main.LOGGER.log(Level.SEVERE, "Impossible de restaurer l'état");
                return;
            }

            else {
                Main.LOGGER.log(Level.INFO, "Restauration de l'état");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = null;
            try {
                Object obj = parser.parse(new FileReader("persistence/save.json"));
                jsonObject = (JSONObject) obj;
            }
            catch (Exception e) {
                Main.LOGGER.log(Level.SEVERE, "Impossibe de restaurer les données");
            }

            // On récupère la liste de matériel
            JSONArray materiel_list = (JSONArray) jsonObject.get("materiels");

            for(int i = 0; i < materiel_list.size(); i++) {

                JSONObject materiel = (JSONObject) materiel_list.get(i);

                String nom = (String) materiel.get("nom");
                int quantite = (int) (long) materiel.get("quantite");
                String type = (String) materiel.get("type");


                Materiel m = new Materiel(nom, getTypeMaterielByName(type), quantite);
                app.addMateriel(m);
            }

            // On récupère la liste de personnel
            JSONArray personnel_list = (JSONArray) jsonObject.get("personnel");

            for(int i = 0; i < personnel_list.size(); i++) {

                JSONObject personnel = (JSONObject) personnel_list.get(i);

                String nom = (String) personnel.get("nom");
                String mail = (String) personnel.get("mail");
                String numero = (String) personnel.get("numero");
                String type = (String) personnel.get("type");

                Personnel p = new Personnel(nom, getTypePersonnelByName(type));
                p.setMail(mail);
                p.setNumero(numero);
                app.addPersonnel(p);
            }

            // On récupère la liste de lieux
            JSONArray lieux_list = (JSONArray) jsonObject.get("lieux");

            for(int i = 0; i < lieux_list.size(); i++) {

                JSONObject lieu = (JSONObject) lieux_list.get(i);

                String nom = (String) lieu.get("nom");
                String adresse = (String) lieu.get("adresse");
                String complement = (String) lieu.get("complement");
                String code_postal = (String) lieu.get("code_postal");
                String ville = (String) lieu.get("ville");
                int capacite = (int) (long) lieu.get("capacite");
                String type = (String) lieu.get("type");

                Lieu l = new Lieu(nom, capacite, getTypeLieuByName(type));

                l.setAdresse(adresse);
                l.setComplementAdresse(complement);
                l.setCodePostal(code_postal);
                l.setVille(ville);
                app.addLieu(l);
            }

    }



    /**
     * Récupère le type de matériel en fonction du nom
     * @param name nom du type
     * @return
     */
    private static TypeMateriel getTypeMaterielByName(String name) {
        for(TypeMateriel t : TypeMateriel.values()) {
            if(t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

    private static TypePersonnel getTypePersonnelByName(String name) {
        for(TypePersonnel t : TypePersonnel.values()) {
            if(t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

    private static TypeLieu getTypeLieuByName(String name) {
        for(TypeLieu t : TypeLieu.values()) {
            if(t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

}
