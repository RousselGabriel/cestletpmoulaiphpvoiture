package fr.btsciel;
import fr.btsciel.clavier.In;

import java.sql.*;

/**
 * Classe IHM (Interface Homme-Machine) de l'application.
 * <p>
 * Cette classe gère l'interaction avec l'utilisateur via la console :
 * affichage des menus, lecture des saisies utilisateur et appel des
 * méthodes de la classe {@link ListerVoitures}.
 * </p>
 */
public class Ihm {
    /**
     * Instance unique de ListerVoitures utilisée par l'application.
     */
    static ListerVoitures listerVoitures;
    //Initialisation de l'instance ListerVoitures avec connexion BDD
    static {
        try {
            listerVoitures = new ListerVoitures();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Affiche la liste complète des voitures (toutes les informations).
     *
     * @param listerVoitures Instance contenant la liste des voitures à afficher
     */
    public static void affichage(ListerVoitures listerVoitures) {
        for (Voitures v : listerVoitures.getListeDesVoitures()) {
            System.out.println("-----------------------------------------");
            System.out.println(v);
        }
    }
    /**
     * Affiche uniquement la marque et le modèle de chaque voiture.
     *
     * @param listerVoitures Instance contenant la liste des voitures à afficher
     */
    public static void affichageMarqueModel(ListerVoitures listerVoitures) {
         for (Voitures v : listerVoitures.getListeDesVoitures()){
             System.out.println("-----------------------------------------");
             System.out.println(v.toStringMarqueModele());
         }
    }
    /**
     * Affiche uniquement la marque de chaque voiture.
     *
     * @param listerVoitures Instance contenant la liste des voitures à afficher
     */
    public static void affichgeMarque(ListerVoitures listerVoitures) {
        for (Voitures v : listerVoitures.getListeDesVoitures()){
            System.out.println("-----------------------------------------");
            System.out.println(v.toStringMarque());
        }
    }

    /**
     * Vérifie et normalise la saisie de la marque d'une voiture.
     * <p>
     * La marque ne peut pas être vide et est formatée avec une majuscule
     * au début et des minuscules pour le reste.
     * </p>
     *
     * @return La marque valide saisie par l'utilisateur
     */
    public static String checkMarqueValide() {
        String sMarque;
        do {
            System.out.println("Entrez la marque de la voiture : ");
            sMarque = In.readString();
            if (sMarque == null || sMarque.trim().isEmpty()) {
                System.out.println("Entrez une chaine de caractères qui n'est pas VIDE.");
            }
        } while (sMarque == null || sMarque.trim().isEmpty());
        sMarque = sMarque.trim();
        sMarque = sMarque.substring(0,1).toUpperCase() + sMarque.substring(1).toLowerCase();
        return sMarque;
    }
    /**
     * Vérifie la saisie du modèle d'une voiture.
     * <p>
     * Le modèle ne peut pas être vide.
     * </p>
     *
     * @return Le modèle valide saisi par l'utilisateur
     */
    public static String checkModeleValide() {
        String sModele;
        do {
            System.out.println("Entrez la modele de la voiture : ");
            sModele = In.readString();
            if (sModele == null || sModele.trim().isEmpty()) {
                System.out.println("Entrez une chaine de caractères qui n'est pas VIDE.");
            }
        } while (sModele == null || sModele.trim().isEmpty());
        return sModele.trim();
    }

    /**
     * Point d'entrée de l'application.
     * <p>
     * Affiche le menu principal, récupère les choix utilisateur
     * et exécute les traitements correspondants.
     * </p>
     *
     */
    public static void main(String[] args) {
        try{
            boolean boucle = true;
            do {
                System.out.println("""
                        -----------------------------------------
                        -----------------MY-SQL------------------
                        -----------------------------------------
                        [1]  Afficher toutes les voitures
                        [2]  Classement par nombre de ventes
                        [3]  Modèles commençant par 'e'
                        [4]  Voitures avec plus de 10 000 ventes
                        [5]  Tri par marque et modèle
                        [6]  Ventes entre 10 000 et 15 000
                        [7]  Marques contenant la lettre 'o'
                        [8]  Nombre total de véhicules vendus
                        [9]  Nombre total de lignes
                        [10] Liste des marques (sans doublons)
                        [11] Voiture la plus vendue
                        [12] Podium des ventes (Top 3)
                        -----------------------------------------
                        [13] Ajouter un véhicule (modele, marque, ventes)
                        [14] Supprimer un véhicule (id)
                        [15] Modifier un véhicule (id) -> (modele, marque, ventes)
                        -----------------------------------------
                        [0] Arret du programme
                        """);
                int choixMenu = In.readInteger();
                switch (choixMenu) {
                    case 1:
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        break;
                    case 2:
                        listerVoitures.listerParVentes();
                        affichage(listerVoitures);
                        break;
                    case 3:
                        listerVoitures.listerVehiculeCommencantParE();
                        affichage(listerVoitures);
                        break;
                    case 4:
                        listerVoitures.listerVehiclePlusDe10kVentes();
                        affichage(listerVoitures);
                        break;
                    case 5:
                        listerVoitures.listerParMarqueEtModele();
                        affichageMarqueModel(listerVoitures);
                        break;
                    case 6:
                        listerVoitures.listerVentesEntre10kEt15k();
                        affichage(listerVoitures);
                        break;
                    case 7:
                        listerVoitures.listerMarquesContenantO();
                        affichage(listerVoitures);
                        break;
                    case 8:
                        int totalVentes = listerVoitures.calculTotalVentes();
                        System.out.println("Il y a eu: " + totalVentes + " véhicules vendus.");
                        break;
                    case 9:
                        int totalID = listerVoitures.nombreTotalID();
                        System.out.println("Il y a : " + totalID + " véhicules dans la base de donnée.");
                        break;
                    case 10:
                        listerVoitures.listeDesMarquesUniques();
                        affichgeMarque(listerVoitures);
                        break;
                    case 11:
                        listerVoitures.voitureLaPlusVendue();
                        affichage(listerVoitures);
                        break;
                    case 12:
                        listerVoitures.top3ventes();
                        affichage(listerVoitures);
                        break;
                    case 13:
                        String sMarqueAjout = checkMarqueValide();
                        String sModeleAjout = checkModeleValide();

                        System.out.println("Entrez le nombre de ventes de la voiture : ");
                        int iVentesAjout = In.readInteger();

                        int ajouterVoiture = listerVoitures.ajouterUneVoiture(sModeleAjout,sMarqueAjout,iVentesAjout);

                        if (ajouterVoiture == 1) {
                            System.out.println("La voiture a BIEN été ajoutée.");
                        } else {
                            System.out.println("La voiture n'a PAS été ajoutée.");
                        }
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        break;
                    case 14:
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        System.out.println("Entrez l'id du véhicule à supprimer: ");
                        int idSuppr = In.readInteger();
                        int supprimerUneVoiture = listerVoitures.supprimerUneVoiture(idSuppr);
                        if (supprimerUneVoiture == 1) {
                            System.out.println("La voiture a BIEN été supprimée.");
                        } else {
                            System.out.println("La voiture n'a PAS été supprimée.");
                        }
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        break;
                    case 15:
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        System.out.println("Entrez l'id du véhicule à modifier: ");
                        int idModif = In.readInteger();
                        String sMarqueModif = checkMarqueValide();
                        String sModelModif = checkModeleValide();
                        int ventesModif = In.readInteger();
                        int modifierUneVoiture = listerVoitures.modifierUneVoiture(sMarqueModif,sModelModif,ventesModif,idModif);
                        if (modifierUneVoiture == 1) {
                            System.out.println("La voiture a BIEN été modifiée.");
                        } else {
                            System.out.println("La voiture n'a PAS été modifiée.");
                        }
                        listerVoitures.listerParId();
                        affichage(listerVoitures);
                        break;
                    case 0:
                        System.out.println("Arret du programme");
                        listerVoitures.fermetureCon();
                        boucle = false;
                        break;
                    default:
                        System.out.println("Choix incorrect");
                        break;
                }
            }while (boucle);
        }catch(Exception e){
            System.err.println("Une erreur est survenue :");
            e.printStackTrace();
        }
    }
}
