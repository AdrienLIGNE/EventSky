package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MainApplicationTest {

    @Test
    void testGetLieuDisponible() {
        MainModel app = new MainModel();

        Lieu lieu1 = new Lieu(0, "Test");
        Lieu lieu2 = new Lieu(0, "Test1");
        Lieu lieu3 = new Lieu(0, "Test2");
        Lieu lieu4 = new Lieu(0, "Test3");

        app.addLieu(lieu1);
        app.addLieu(lieu2);
        app.addLieu(lieu3);
        app.addLieu(lieu4);

        LocalDate debut = LocalDate.of(2022, 10, 5);
        LocalDate fin = LocalDate.of(2022, 10, 6);

        // Création de l'événement
        Evenement evenement = new Evenement(0, debut, fin, "Concert de Rock");

        evenement.setLieu(lieu1);
        evenement.confirme();

        for(Lieu l : app.getLieuxDisponibles(LocalDate.of(2022, 10, 5))) {
            assertTrue(l.estDisponible(LocalDate.of(2022, 10, 5)), "Le lieu ne devrait pas être disponible.");
        }

    }

}