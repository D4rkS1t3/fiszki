public class Slowo {
    private int id;
    private String slowoPolskie;
    private String slowoAngielskie;

    public Slowo(int id, String slowoPolskie, String slowoAngielskie) {
        this.id = id;
        this.slowoPolskie = slowoPolskie;
        this.slowoAngielskie = slowoAngielskie;
    }

    public int getId() {
        return id;
    }

    public String getSlowoPolskie() {
        return slowoPolskie;
    }

    public String getSlowoAngielskie() {
        return slowoAngielskie;
    }

    @Override
    public String toString() {
        return slowoPolskie + " - " + slowoAngielskie; // Ta metoda pozwoli nam na wyświetlanie słów w liście wyboru (JComboBox)
    }
}