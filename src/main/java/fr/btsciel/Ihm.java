package fr.btsciel;

import fr.btsciel.clavier.In;

import java.sql.*;

public class Ihm {
    static ListerVoitures listerVoitures;

    static {
        try {
            listerVoitures = new ListerVoitures();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void affichage(ListerVoitures listerVoitures) {
        int compteur = 1;
        for (Voitures v : listerVoitures.getListeDesVoitures()) {
            System.out.println("ID :" + v.getId() + "|  Marque :" + v.getMarque() + "|  Modele :" + v.getModele() + "|  Ventes en $ :" + v.getVentes() + "\n");
        }
    }
    public static void main(String[] args) {
        try{
            System.out.println("""
                    1 - Afficher toutes les voitures ordonnées par le nombre de véhicules vendus.
                    
                    2 - ajouter une voiture avec 
                    """);
        int choixMenu = In.readInteger();
        switch (choixMenu) {
            case 1:
                listerVoitures.listerParVentes();
                affichage(listerVoitures);
                break;
            case 2:
                System.out.println("Ajout de véhicule");
                System.out.println("Entrez la marque:");
                String sMarque = In.readString();
                System.out.println("Entrez le modele:");
                String sModele = In.readString();
                System.out.println("Entrez les ventes:");
                int iVentes = In.readInteger();
                listerVoitures.ajouterVehicule();
                affichage(listerVoitures);
        }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
