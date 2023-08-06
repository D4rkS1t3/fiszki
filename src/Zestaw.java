import java.util.List;

public class Zestaw {
    private int id;
    private String nazwa;
    private List<Slowo> slowa;

    public Zestaw(int id, String nazwa, List<Slowo> slowa) {
        this.id = id;
        this.nazwa = nazwa;
        this.slowa = slowa;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public List<Slowo> getSlowa() {
        return slowa;
    }

    @Override
    public String toString() {
        return nazwa; // Ta metoda pozwoli nam na wyświetlanie nazwy zestawu w liście wyboru (JComboBox)
    }
}