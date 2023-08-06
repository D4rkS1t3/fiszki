import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private static final Dimension FRAME_SIZE = new Dimension(800, 600);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Utworzenie okna głównego
        JFrame frame = new JFrame("Fiszki");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE);

        // Utworzenie panelu z obrazkiem tła
        ImagePanel tloPanel = new ImagePanel();
        ImageIcon backgroundImage = new ImageIcon("src/tlo.jpg");
        tloPanel.setBackgroundImage(backgroundImage.getImage());

        // Utworzenie panelu menu z przyciskami
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1)); // 4 wiersze, 1 kolumna, odstęp 10 pikseli
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(270,200,260,250);


        // Utworzenie przycisków i dodanie ich do panelu menu
        JButton mojeZestawyButton = new JButton("Moje zestawy");
        JButton utworzZestawButton = new JButton("Utwórz zestaw");
        JButton utworzLosowyZestawButton = new JButton("Utwórz losowy zestaw");
        JButton zakonczButton = new JButton("Zakończ");
        menuPanel.add(mojeZestawyButton);
        menuPanel.add(utworzZestawButton);
        menuPanel.add(utworzLosowyZestawButton);
        menuPanel.add(zakonczButton);

        // Dodanie panelu menu na panelu z obrazkiem tła
        tloPanel.setLayout(null);
        tloPanel.add(menuPanel, BorderLayout.SOUTH);

        // Dodanie panelu z obrazkiem tła do okna głównego
        frame.add(tloPanel);

        mojeZestawyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kod do wykonania po kliknięciu przycisku "Moje zestawy"
                System.out.println("Kliknięto przycisk 'Moje zestawy'");
            }
        });

        utworzZestawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kod do wykonania po kliknięciu przycisku "Utwórz zestaw"
                System.out.println("Kliknięto przycisk 'Utwórz zestaw'");
            }
        });

        utworzLosowyZestawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kod do wykonania po kliknięciu przycisku "Utwórz losowy zestaw"
                System.out.println("Kliknięto przycisk 'Utwórz losowy zestaw'");
            }
        });

        zakonczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kod do wykonania po kliknięciu przycisku "Zakończ"
                System.exit(0); // Wyjście z aplikacji
            }
        });

        // Wyśrodkowanie okna na ekranie
        frame.setLocationRelativeTo(null);

        // Wyświetlenie okna
        frame.setVisible(true);
    }
}

// Klasa rozszerzająca JPanel i umożliwiająca ustawienie obrazka jako tła
class ImagePanel extends JPanel {
    private Image backgroundImage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Rysowanie obrazka tła
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Rysowanie napisu "FISZKI" na obrazku tła
            g.setColor(Color.BLACK); // Zmiana koloru napisu na czarny
            g.setFont(new Font("COURIER NEW", Font.ROMAN_BASELINE, 86)); // Zwiększenie rozmiaru czcionki
            FontMetrics fontMetrics = g.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth("FISZKI");
            int stringHeight = fontMetrics.getHeight();
            int x = (getWidth() - stringWidth) / 2;
            int y = (getHeight() - stringHeight) / 2-110;
            g.drawString("FISZKI", x, y);
        }
    }

    // Metoda do ustawienia obrazka jako tła
    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
        repaint();
    }
}
