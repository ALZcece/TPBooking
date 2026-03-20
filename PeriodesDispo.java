package TPBooking;

public class PeriodesDispo {
    public java.util.Date debut;
    public java.util.Date fin;

    //constructeur
    public PeriodesDispo(java.util.Date debut, java.util.Date fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public void valider() {
        if (debut == null || fin == null) {
            throw new IllegalArgumentException("Date de début ou de fin manquante");
        }
        if (fin.before(debut) || fin.equals(debut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }
    }

    public int nbNuits() {
        valider();
        long diff = fin.getTime() - debut.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public boolean chevauche(PeriodesDispo autre) {
        if (autre == null) return false;
        valider();
        autre.valider();
        return debut.before(autre.fin) && fin.after(autre.debut);
    }
}
