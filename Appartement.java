package TPBooking;

import java.util.List;

public class Appartement extends Hebergement {
    public Appartement(String nom, String adresse, int capacite, double prixParNuit, List<Equipement> equipement) {
        super(nom, adresse, "Appartement", capacite, prixParNuit, equipement);
    }
}