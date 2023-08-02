import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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



}