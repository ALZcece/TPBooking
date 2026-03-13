package TPBooking;

public class PeriodesDispo {
    public java.util.Date debut;
    public java.util.Date fin;

    //constructeur
    public PeriodesDispo(java.util.Date debut, java.util.Date fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public int nbNuits() {
        long diff = fin.getTime() - debut.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public boolean chevauche(PeriodesDispo autre) {
        return debut.before(autre.fin) && fin.after(autre.debut);
    }
}
