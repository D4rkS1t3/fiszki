import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
    DatabaseManager.dodajSlowoDoZestawu(1,1);
    }

//        String nazwaPliku = "C:\\Programowanie\\nauka\\portfolio\\fiszki1\\src\\slowa.txt";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku));
//             Connection connection = DatabaseManager.getConnection()) {
//
//            String insertQuery = "INSERT INTO slowo (slowo_polskie, slowo_angielskie) VALUES (?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] slowoSlownikowe = line.split(" - ");
//                if (slowoSlownikowe.length == 2) {
//                    String slowoPolskie = slowoSlownikowe[0].trim();
//                    String slowoAngielskie = slowoSlownikowe[1].trim();
//                    preparedStatement.setString(1, slowoPolskie);
//                    preparedStatement.setString(2, slowoAngielskie);
//                    preparedStatement.addBatch();
//                }
//            }
//
//            preparedStatement.executeBatch();
//            System.out.println("Dane zostały wczytane do bazy danych.");
//
//        } catch (IOException e) {
//            System.err.println("Wystąpił błąd podczas wczytywania danych z pliku.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.err.println("Wystąpił błąd podczas zapisywania danych do bazy danych.");
//            e.printStackTrace();
//        }
//    }
}
