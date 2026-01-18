package fr.btsciel;

/**
 * Classe représentant une voiture avec ses attributs principaux :
 * identifiant, marque, modèle et nombre de ventes.
 */
public class Voitures {
        private int id;
        private String marque;
        private String modele;
        private int ventes;
    /**
     * Constructeur complet.
     * @param id L'identifiant de la voiture.
     * @param marque La marque de la voiture.
     * @param modele Le modèle de la voiture.
     * @param ventes Le nombre de ventes.
     */
    public Voitures(int id, String marque, String modele, int ventes) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.ventes = ventes;
    }
    /**
     * Constructeur partiel (sans ID ni ventes).
     * @param marque La marque de la voiture.
     * @param modele Le modèle de la voiture.
     */
    public Voitures(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
    /**
     * Constructeur minimal (marque uniquement).
     * @param marque La marque de la voiture.
     */
    public Voitures(String marque){
        this.marque = marque;
    }
    /** @return L'identifiant de la voiture. */
    public int getId() {
        return id;
    }
    /** @param id Définit l'identifiant de la voiture. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return La marque de la voiture. */
    public String getMarque() {
        return marque;
    }
    /** @param marque Définit la marque de la voiture. */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /** @return Le modèle de la voiture. */
    public String getModele() {
        return modele;
    }
    /** @param modele Définit le modèle de la voiture. */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /** @return Le nombre de ventes. */
    public int getVentes() {
        return ventes;
    }
    /** @param ventes Définit le nombre de ventes. */
    public void setVentes(int ventes) {
        this.ventes = ventes;
    }


    /**
     * Affichage complet de l'objet Voitures.
     * @return Chaîne formatée avec ID, marque, modèle et ventes.
     */
    @Override
    public String toString() {
        return "ID :" + id + "|  Marque :" + marque + "|  Modele :" + modele + "|  Nombre de ventes :" + ventes + "\n";
    }
    /**
     * Affiche uniquement la marque et le modèle.
     * @return Chaîne contenant la marque et le modèle.
     */
    public String toStringMarqueModele() {
        return "Marque : " + marque + " | Modele : " + modele;
    }
    /**
     * Affiche uniquement la marque.
     * @return Chaîne contenant la marque uniquement.
     */
    public String toStringMarque() {
        return "Marque : " + marque;
    }


}
