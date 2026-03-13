package TPBooking;
import java.util.Scanner;


public class Reservation {
    public int id;
    public int status; // 0: en attente, 1: confirmée, 2: annulée
    public Client client;
    public Hebergement hebergement;
    public PeriodesDispo dates;
    public java.util.Date dateReservation;
    public double prixSansReduction;
    public double prixFinal;

    public Reservation(Client client, Hebergement hebergement, PeriodesDispo dates) {
        this.id = java.util.UUID.randomUUID().hashCode();
        this.client = client;
        this.hebergement = hebergement;
        this.dates = dates;
        this.dateReservation = new java.util.Date();
        this.status = 0; // en attente par défaut
        this.prixSansReduction = calculerPrixBase();
        this.prixFinal = calculerPrixTotal();

        if (this.hebergement != null) {
            this.hebergement.reservations.add(this);
        }
        if (this.client != null) {
            this.client.ajouterReservation(this);
        }
    }

    public double calculerPrixBase() {
        long jours = (dates.fin.getTime() - dates.debut.getTime()) / (1000 * 60 * 60 * 24);
        return hebergement.prixParNuit * (double) jours;
    }

    public double calculerPrixTotal() {
        double prixBase = calculerPrixBase();
        double reduction = 0.0;
        if (client != null) {
            reduction = prixBase * client.getReduction();
        }
        return prixBase - reduction;
    }
    // lh'bergement est reservé ?
    
    
    
}
