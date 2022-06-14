package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservableTest {

    @org.junit.jupiter.api.Test
    void estDisponible() {

        // Création de l'événement
        Evenement evenement = new Evenement(0, LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 6), "Concert de Rock");

        // Création de personnel
        Personnel personnel1 = new Personnel("LACOMBE", "Quentin", TypePersonnel.VIGILE);

        // Le personnel n'étant pas associé à un événement, il devrait être disponible
        assertTrue(personnel1.estDisponible(LocalDate.of(2022, 10, 5)), "Le personnel devrait être disponible à cette date car il n'est associé à aucun événement");

        // On affecte le personnel à l'événement
        evenement.addPersonnel(personnel1);

        // Le personnel devrait encore être disponible car l'événement n'est pas confirmé
        assertTrue(personnel1.estDisponible(LocalDate.of(2022, 10, 5)), "Le personnel devrait être disponible car l'événement n'est pas confirmé");

        // Confirmation de l'événement
        evenement.confirme();
        assertFalse(personnel1.estDisponible(LocalDate.of(2022, 10, 5)), "Le personnel ne devrait pas être disponible");
        assertFalse(personnel1.estDisponible(LocalDate.of(2022, 10, 6)), "Le personnel ne devrait pas être disponible");

        assertTrue(personnel1.estDisponible(LocalDate.of(2022, 10, 7)), "Le personnel devrait être disponible à cette date car l'événement sera terminé");


    }

    @Test
    void getQuantiteDisponible() {

        // Création de l'événement
        Evenement evenement = new Evenement(0, LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 6), "Concert de Rock");

        Materiel materiel = new Materiel("Chaise", 15);
        evenement.addMateriel(materiel, 10);

        assertEquals(15, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 5)), "Il n'y a pas le bon nombre de chaise");

        // Confirmation de l'événement
        evenement.confirme();
        assertEquals(5, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 5)), "Il n'y a pas le bon nombre de chaise");

        // Vérification du nombre de quantité en général
        assertEquals(15, materiel.getQuantiteDisponible(), "Il n'y a pas le bon nombre de chaise");
        assertEquals(15, materiel.getQuantiteDisponible(LocalDate.of(2022, 10, 8)), "Il n'y a pas le bon nombre de chaise");

    }
}