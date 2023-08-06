import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:C:/Programowanie/nauka/portfolio/SQLlite/mydatabase.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    public static void insertZestaw(String nazwaZestawu) throws SQLException {
        String insertQuery = "INSERT INTO zestaw (nazwa) VALUES (?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nazwaZestawu);
            preparedStatement.executeUpdate();
        }
    }

    public static void dodajSlowoDoZestawu(int idZestawu, int idSlowa) throws SQLException {
        String insertQuery = "INSERT INTO zestaw_slowo (id_zestawu, id_slowa) VALUES (?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, idZestawu);
            preparedStatement.setInt(2, idSlowa);
            preparedStatement.executeUpdate();
        }
    }

    public static void usunSlowoZZestawu(int idZestawu, int idSlowa) throws SQLException {
        String deleteQuery = "DELETE FROM zestaw_slowo WHERE id_zestawu = ? AND id_slowa = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, idZestawu);
            preparedStatement.setInt(2, idSlowa);
            preparedStatement.executeUpdate();
        }
    }

    public static void usunZestaw(int idZestawu) throws SQLException {
        String deleteZestawQuery = "DELETE FROM zestaw WHERE id = ?";
        String deleteZestawSlowoQuery = "DELETE FROM zestaw_slowo WHERE id_zestawu = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement deleteZestawStatement = connection.prepareStatement(deleteZestawQuery);
             PreparedStatement deleteZestawSlowoStatement = connection.prepareStatement(deleteZestawSlowoQuery)) {

            // Usuwamy zestaw z tabeli "zestaw"
            deleteZestawStatement.setInt(1, idZestawu);
            deleteZestawStatement.executeUpdate();

            // Usuwamy wszystkie powiązane słowa z tabeli "zestaw_slowo"
            deleteZestawSlowoStatement.setInt(1, idZestawu);
            deleteZestawSlowoStatement.executeUpdate();
        }
    }
    public static List<Zestaw> getAllZestawy() throws SQLException {
        List<Zestaw> zestawy = new ArrayList<>();

        String selectQuery = "SELECT id, nazwa FROM zestaw";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                int idZestawu = resultSet.getInt("id");
                String nazwaZestawu = resultSet.getString("nazwa");

                // Pobranie słów przypisanych do tego zestawu
                List<Slowo> slowaZestawu = getSlowaZestawu(idZestawu);

                Zestaw zestaw = new Zestaw(idZestawu, nazwaZestawu, slowaZestawu);
                zestawy.add(zestaw);
            }
        }

        return zestawy;
    }

    private static List<Slowo> getSlowaZestawu(int idZestawu) throws SQLException {
        List<Slowo> slowaZestawu = new ArrayList<>();

        String selectQuery = "SELECT s.id, s.slowo_polskie, s.slowo_angielskie " +
                "FROM slowo s " +
                "JOIN zestaw_slowo zs ON s.id = zs.id_slowa " +
                "WHERE zs.id_zestawu = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, idZestawu);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idSlowa = resultSet.getInt("id");
                String slowoPolskie = resultSet.getString("slowo_polskie");
                String slowoAngielskie = resultSet.getString("slowo_angielskie");

                Slowo slowo = new Slowo(idSlowa, slowoPolskie, slowoAngielskie);
                slowaZestawu.add(slowo);
            }
        }

        return slowaZestawu;
    }



}