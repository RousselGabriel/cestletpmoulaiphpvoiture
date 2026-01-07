package fr.btsciel;

public class Voitures {
        private int id;
        private String marque;
        private String modele;
        private int ventes;

    public Voitures(int id, String marque, String modele, int ventes) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.ventes = ventes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getVentes() {
        return ventes;
    }

    public void setVentes(int ventes) {
        this.ventes = ventes;
    }
}
