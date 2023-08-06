import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySets extends JPanel {
    private List<Zestaw> zestawy; // Lista zawierająca obiekty reprezentujące zestawy
    private JFrame frame;
    private JPanel zestawyPanel;
    private JScrollPane scrollPane;
    private Color lightBlue = new Color(173, 216, 230);

    public MySets() {
        zestawy = new ArrayList<>(); // Inicjalizacja listy zestawów

        // Tworzenie głównego okna
        frame = new JFrame("Moje Fiszki");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tworzenie panelu głównego, który będzie przechowywał nagłówek i zestawyPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(lightBlue); // Ustawienie tła dla głównego panelu
        mainPanel.setOpaque(true);

        // Tworzenie panelu nagłówka
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(lightBlue);
        headerPanel.setOpaque(true);
        createHeader(headerPanel);
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Tworzenie panelu, który będzie przechowywał zestawy
        zestawyPanel = new JPanel();
        zestawyPanel.setLayout(new BoxLayout(zestawyPanel, BoxLayout.Y_AXIS));
        refreshZestawy();

        // Dodawanie paneli nagłówka i zestawów do głównego panelu
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Dodawanie głównego panelu do okna
        frame.add(mainPanel);

        // Wyśrodkowanie okna na ekranie
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private void createHeader(JPanel panel) {
        // Tworzenie nagłówka "Lista Zestawów:"
        JLabel headerLabel = new JLabel("Lista Zestawów:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Zwiększenie rozmiaru czcionki
        headerLabel.setBackground(lightBlue);
        headerLabel.setOpaque(true);
        panel.add(headerLabel, BorderLayout.NORTH);
    }

    private void refreshZestawy() {
        try {
            zestawy = DatabaseManager.getAllZestawy(); // Pobranie listy zestawów z bazy danych
            System.out.println("Zestawy z bazy danych:");
            for (Zestaw zestaw : zestawy) {
                System.out.println(zestaw.getNazwa());
            }
            displayZestawy(zestawy); // Wyświetlenie zestawów
        } catch (SQLException e) {
            e.printStackTrace();
            // Obsługa błędu (np. wyświetlenie okna dialogowego z informacją o błędzie)
        }
    }

    private void displayZestawy(List<Zestaw> zestawy) {
        System.out.println("Displaying zestawy...");

        for (Zestaw zestaw : zestawy) {
            System.out.println("Creating panel for: " + zestaw.getNazwa());
            JPanel panel = createZestawPanel(zestaw);
            zestawyPanel.add(panel);
        }

        // Teraz możemy dodać panel z zestawami do JScrollPane i ustawić go na obszarze CENTER
        scrollPane = new JScrollPane(zestawyPanel);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setPreferredSize(new Dimension(400, 300));

    }

    private JPanel createZestawPanel(Zestaw zestaw) {
        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.setBorder(new LineBorder(Color.BLACK));
        // Wyświetlenie nazwy zestawu w JLabel
        JLabel nazwaLabel = new JLabel(zestaw.getNazwa());
        Font fontt=new Font("Arial",Font.BOLD,27);
        nazwaLabel.setFont(fontt);
        nazwaLabel.setHorizontalAlignment(JLabel.CENTER);
        nazwaLabel.setVerticalAlignment(JLabel.CENTER);
        nazwaLabel.setBackground(lightBlue);
        nazwaLabel.setOpaque(true);
        panel.add(nazwaLabel);

        // Dodanie przycisków "Edytuj zestaw" i "Usuń zestaw"
        JButton edytujButton = new JButton("Edytuj zestaw");
        JButton usunButton = new JButton("Usuń zestaw");

        // Dodanie akcji do przycisków (np. wywołanie odpowiednich funkcji po kliknięciu)
        edytujButton.addActionListener(e -> {
            // Tu wykonaj akcję edytowania zestawu
        });

        usunButton.addActionListener(e -> {
            // Tu wykonaj akcję usuwania zestawu
        });

        panel.add(edytujButton);
        panel.add(usunButton);
        return panel;
    }

    // ... pozostałe metody ...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MySets mySetsPanel = new MySets();

        });
    }
}
