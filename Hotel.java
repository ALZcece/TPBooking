package TPBooking;

import java.util.List;

public class Hotel extends Hebergement {
    public Hotel(String nom, String adresse, int capacite, double prixParNuit, List<Equipement> equipement) {
        super(nom, adresse, "Hotel", capacite, prixParNuit, equipement);
    }
}