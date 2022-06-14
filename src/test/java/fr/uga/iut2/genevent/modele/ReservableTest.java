package fr.uga.iut2.genevent.modele;

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
        personnel1.affecteEvenement(evenement);

        // Le personnel devrait encore être disponible car l'événement n'est pas confirmé
        assertTrue(personnel1.estDisponible(LocalDate.of(2022, 10, 5)), "Le personnel devrait être disponible car l'événement n'est pas confirmé");

        // Confirmation de l'événement
        evenement.confirme();
        assertFalse(personnel1.estDisponible(LocalDate.of(2022, 10, 5)), "Le personnel ne devrait pas être disponible");
        assertFalse(personnel1.estDisponible(LocalDate.of(2022, 10, 6)), "Le personnel ne devrait pas être disponible");

        assertTrue(personnel1.estDisponible(LocalDate.of(2022, 10, 7)), "Le personnel devrait être disponible à cette date car l'événement sera terminé");


    }
}