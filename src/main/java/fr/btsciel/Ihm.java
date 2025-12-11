package fr.btsciel;

import java.sql.*;

public class Ihm {
    public static void main(String[] args) {
        try {
            int result = 0;
            System.out.println("Test connector");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connector is installed");
            System.out.println("Try to connect to MySQL");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb", "bob", "bob");
            PreparedStatement pst = con.prepareStatement("select * from users");
            ResultSet rs = pst.executeQuery();
            System.out.println("Connection established");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            pst = con.prepareStatement("INSERT INTO users (id, name, firstname) VALUES (NULL, 'Tonnot', 'bob');");
            result = pst.executeUpdate();
            if( result > 0) {
                System.out.println("L'enregistrement a été ajouté");
            } else {
                System.out.println("L'enregistrement n'a pas été ajouté");
            }
            pst = con.prepareStatement("select * from users");
            rs = pst.executeQuery();
            System.out.println("Connection established");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
