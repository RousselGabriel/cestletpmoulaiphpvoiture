package fr.btsciel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsable de la gestion des interactions avec la base de données
 * concernant les voitures. Elle permet d'effectuer des requêtes diverses
 * (consultation, ajout, modification, suppression) sur la table `voitures`.
 */
public class ListerVoitures {

    ArrayList<Voitures> listeDesVoitures = new ArrayList<>();
    private Connection con = null;

    /**
     * Remplit la liste des voitures à partir d'un ResultSet complet.
     * @param rs Le ResultSet contenant les lignes de la table voitures.
     * @throws SQLException En cas d'erreur lors de la lecture du ResultSet.
     */
    private void remplirLaListe(ResultSet rs) throws SQLException {
        while(rs.next()) {
            Voitures voitures = new Voitures(
                    rs.getInt("id"),
                    rs.getString("marque"),
                    rs.getString("modele"),
                    rs.getInt("ventes"));
            listeDesVoitures.add(voitures);
        }
    }
    /**
     * Remplit la liste des voitures avec uniquement la colonne `marque`.
     * @param rs Le ResultSet contenant les marques distinctes.
     * @throws SQLException En cas d'erreur lors de la lecture du ResultSet.
     */
    private void remplirLaListeMarque(ResultSet rs) throws SQLException {
        while(rs.next()) {
            Voitures voitures = new Voitures(rs.getString("marque"));
            listeDesVoitures.add(voitures);
        }
    }
    /**
     * Remplit la liste des voitures avec les colonnes `marque` et `modele`.
     * @param rs Le ResultSet contenant les marques et modèles.
     * @throws SQLException En cas d'erreur lors de la lecture du ResultSet.
     */
    private void remplirLaListeMarqueModele(ResultSet rs) throws SQLException {
        while(rs.next()) {
            Voitures voitures = new Voitures(
                    rs.getString("marque"),
                    rs.getString("modele"));
            listeDesVoitures.add(voitures);
        }
    }
    /**
     * Ferme les ressources SQL (ResultSet et PreparedStatement).
     * @param rs Le ResultSet à fermer.
     * @param pst Le PreparedStatement à fermer.
     * @throws SQLException En cas d'échec de fermeture.
     */
    private void fermetureRsPst(ResultSet rs, PreparedStatement pst) throws SQLException {
        if (rs != null){
            rs.close();
        }
        if (pst != null){
            pst.close();
        }
    }


    /**
     * Constructeur : initialise la connexion à la base et liste toutes les voitures.
     * @throws SQLException Erreur de connexion à la base.
     * @throws ClassNotFoundException Driver JDBC non trouvé.
     */
    public ListerVoitures() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/Roussel_tablevoitures?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","root");
        listerParId();

    }
    /**
     * Liste toutes les voitures sans tri.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerParId() throws  SQLException {
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("select * from voitures");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Liste les voitures triées par nombre de ventes décroissant.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerParVentes() throws SQLException {
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` ORDER BY ventes DESC;");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        rs.close();
        pst.close();
    }
    /**
     * Liste les voitures dont le modèle commence par la lettre 'e'.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerVehiculeCommencantParE() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` WHERE `modele` LIKE 'e%';");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Liste les voitures ayant plus de 10 000 ventes.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerVehiclePlusDe10kVentes() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` WHERE `ventes` > 10000;");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Liste les paires marque / modèle de toutes les voitures.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerParMarqueEtModele() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT `marque`,`modele` FROM `voitures`;");
        ResultSet rs = pst.executeQuery();
        remplirLaListeMarqueModele(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Liste les voitures ayant entre 10 000 et 15 000 ventes inclus.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerVentesEntre10kEt15k() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` WHERE `ventes` BETWEEN 10000 AND 15000;");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Liste les voitures dont la marque contient la lettre 'o'.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listerMarquesContenantO() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` WHERE `marque` LIKE '%o%';");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Calcule le total de toutes les ventes dans la base.
     * @return Le nombre total de ventes enregistrées.
     * @throws SQLException En cas d'erreur SQL.
     */
    public int calculTotalVentes() throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT SUM(`ventes`) AS total_ventes FROM `voitures`;");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("total_ventes");
        } else {
            return 0;
        }
    }
    /**
     * Compte le nombre total de voitures enregistrées.
     * @return Le nombre total de lignes dans la table.
     * @throws SQLException En cas d'erreur SQL.
     */
    public int nombreTotalID() throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS total_ID FROM `voitures`;");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("total_ID");
        } else {
            return 0;
        }
    }
    /**
     * Liste toutes les marques uniques de la base.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void listeDesMarquesUniques() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT DISTINCT `marque` FROM `voitures`;");
        ResultSet rs = pst.executeQuery();
        remplirLaListeMarque(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Récupère la voiture ayant le plus grand nombre de ventes.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void voitureLaPlusVendue() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM voitures ORDER BY ventes DESC LIMIT 1;");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }
    /**
     * Récupère les trois voitures ayant le plus de ventes.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void top3ventes() throws SQLException{
        listeDesVoitures.clear();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM voitures ORDER BY ventes DESC LIMIT 3;");
        ResultSet rs = pst.executeQuery();
        remplirLaListe(rs);
        fermetureRsPst(rs,pst);
    }


    /**
     * Ajoute une nouvelle voiture dans la base.
     * @param sModele Le modèle de la voiture.
     * @param sMarque La marque de la voiture.
     * @param iVentes Le nombre de ventes initial.
     * @return 1 si l'ajout a réussi, 0 sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    public int ajouterUneVoiture (String sModele, String sMarque, int iVentes) throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO voitures(id, marque, modele, ventes) VALUES (NULL,?,?,?)");
        pst.setString(1, sMarque);
        pst.setString(2, sModele);
        pst.setInt(3, iVentes);
        int ajouterVoiture = pst.executeUpdate();
        pst.close();
        return ajouterVoiture;
    }
    /**
     * Supprime une voiture via son ID.
     * @param id L'identifiant de la voiture à supprimer.
     * @return 1 si la suppression a réussi, 0 sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    public int supprimerUneVoiture (int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("DELETE FROM `voitures` WHERE `id` = ?;");
        pst.setInt(1, id);
        int supprimerUneVoiture = pst.executeUpdate();
        pst.close();
        return supprimerUneVoiture;
    }
    /**
     * Modifie une voiture existante.
     * @param sMarque Nouvelle marque.
     * @param sModele Nouveau modèle.
     * @param iVentes Nouveau nombre de ventes.
     * @param id ID de la voiture à modifier.
     * @return 1 si la modification a réussi, 0 sinon.
     * @throws SQLException En cas d'erreur SQL.
     */
    public int modifierUneVoiture (String sMarque, String sModele, int iVentes, int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("UPDATE `voitures` SET `modele`= ? ,`marque`= ?,`ventes` = ? WHERE `voitures`.`id`= ?;");
        pst.setString(1, sModele);
        pst.setString(2, sMarque);
        pst.setInt(3,iVentes);
        pst.setInt(4,id);
        int modifierUneVoiture = pst.executeUpdate();
        pst.close();
        return modifierUneVoiture;
    }
    /**
     * Ferme proprement la connexion à la base de données.
     * @throws SQLException En cas d'erreur lors de la fermeture.
     */
    public void fermetureCon() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * Accède à la liste courante des voitures stockée en mémoire.
     * @return La liste des objets Voitures.
     */
    public ArrayList<Voitures> getListeDesVoitures() {
        return listeDesVoitures;
    }
}
