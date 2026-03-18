package TPBooking;

import java.util.List;

public class ChambreDHote extends Hebergement {
    public ChambreDHote(String nom, String adresse, int capacite, double prixParNuit, List<Equipement> equipement) {
        super(nom, adresse, "Chambre d'hôte", capacite, prixParNuit, equipement);
    }
}