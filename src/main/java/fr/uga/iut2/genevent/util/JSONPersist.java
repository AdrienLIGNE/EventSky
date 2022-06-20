package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.controleur.Controller;
import fr.uga.iut2.genevent.modele.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.time.LocalDate;
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
            evenement.put("nom_artiste", e.getNomArtiste().getValue());
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

            // on récupère la liste des événements

            JSONArray evenements_list = (JSONArray) jsonObject.get("evenements");

            for(int i = 0; i < evenements_list.size(); i++) {

                JSONObject evenement = (JSONObject) evenements_list.get(i);

                String nom = (String) evenement.get("nom");
                String nom_artiste = (String) evenement.get("nom_artiste");
                String date_debut = (String) evenement.get("date_debut");
                String date_fin = (String) evenement.get("date_fin");
                String lieu = (String) evenement.get("lieu");
                boolean confirme = (boolean) evenement.get("confirme");
                String type = (String) evenement.get("type");
                int nb_personnes = (int) (long) evenement.get("nb_personnes");

                Evenement e = new Evenement(LocalDate.parse(date_debut), LocalDate.parse(date_fin), nom, getTypeEvenementByName(type));

                e.setLieu(getLieuByName(app, lieu));
                e.setNomArtiste(nom_artiste);
                e.setNbPersonnes(nb_personnes);

                // on récupère la liste des matériels
                JSONArray materiel_list_evenement = (JSONArray) evenement.get("materiel");

                for(int j = 0; j < materiel_list_evenement.size(); j++) {
                    JSONObject materiel = (JSONObject) materiel_list_evenement.get(j);

                    String nom_materiel = (String) materiel.get("nom");
                    int quantite = (int) (long) materiel.get("quantite");

                    Materiel m = getMaterielByName(app, nom_materiel);
                    e.addMateriel(m, quantite);
                }

                // on récupère la liste des personnel

                JSONArray personnel_list_evenement = (JSONArray) evenement.get("personnel");

                for(int j = 0; j < personnel_list_evenement.size(); j++) {
                    String nom_personnel = (String) personnel_list_evenement.get(j);

                    Personnel p = getPersonnelByName(app, nom_personnel);
                    e.addPersonnel(p);
                }

                if(confirme) {
                    e.confirme();
                }

                app.addEvenement(e);

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

    private static TypeEvenement getTypeEvenementByName(String name) {
        for(TypeEvenement t : TypeEvenement.values()) {
            if(t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }


    /**
     * Récupère le lieu en fonction de son nom
     * @param name nom du lieu
     * @param app Modèle de données
     */
    public static Lieu getLieuByName(MainModel app, String name) {
        for (Lieu l : app.getLieux()) {
            if (l.getNom().getValue().equals(name)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Récupère le matériel en fonction de son nom
     * @param name nom du matériel
     * @param app Modèle de données
     */
    public static Materiel getMaterielByName(MainModel app, String name) {
        for (Materiel m : app.getMateriels()) {
            if (m.getLabel().getValue().equals(name)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Récupère le personnel en fonction de son nom
     * @param name nom du personnel
     * @param app Modèle de données
     */
    public static Personnel getPersonnelByName(MainModel app, String name) {
        for (Personnel p : app.getPersonnels()) {
            if (p.getNom().getValue().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
