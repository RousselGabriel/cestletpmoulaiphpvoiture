package fr.btsciel;
import java.sql.*;
import java.util.ArrayList;

public class ListerVoitures {
    ArrayList<Voitures> listeDesVoitures = new ArrayList();
    private Connection con = null;

    public ListerVoitures() throws SQLException, ClassNotFoundException {
        this.con = con;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/tablevoitures","root","root");
        PreparedStatement pst = con.prepareStatement("select * from voitures");
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            Voitures voitures = new Voitures(rs.getInt("id"), rs.getString("marque")
            ,rs.getString("modele"), rs.getInt("ventes"));
            listeDesVoitures.add(voitures);
        }
        rs.close();
    }

    public void listerParVentes() throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `voitures` ORDER BY ventes DESC;");
        ResultSet rs = pst.executeQuery();
    }
    public int ajouterVehicule() throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `voitures` (`id`, `marque`, `modele`, `ventes`) VALUES (NULL, 'Peugeot', 'e-3004', '16059');");
        return pst.executeUpdate();
    } //String sMarque, String sModele, int iVentes

    public ArrayList<Voitures> getListeDesVoitures() {
        return listeDesVoitures;
    }
    public void setListeDesVoitures(ArrayList<Voitures> listeDesVoitures) {
        this.listeDesVoitures = listeDesVoitures;
    }
}
