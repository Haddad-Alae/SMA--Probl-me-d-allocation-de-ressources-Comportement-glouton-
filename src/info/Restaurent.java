package info;

public class Restaurent {

    private int idRestaurent;
    private String nomRestaurent;
    private int capacité;

    public Restaurent(int idRestaurent, String nomRestaurent, int capacité) {
        super();
        this.idRestaurent = idRestaurent;
        this.nomRestaurent = nomRestaurent;
        this.capacité = capacité;
    }

    public int getIdRestaurent() {
        return idRestaurent;
    }

    public void setIdRestaurent(int idRestaurent) {
        this.idRestaurent = idRestaurent;
    }

    public String getNomRestaurent() {
        return nomRestaurent;
    }

    public void setNomRestaurent(String nomRestaurent) {
        this.nomRestaurent = nomRestaurent;
    }

    public int getCapacité() {
        return capacité;
    }

    public void setCapacité(int capacité) {
        this.capacité = capacité;
    }

    public String toString() {
        return nomRestaurent + " (Capacité : " + capacité + ")";
    }

    public static void main(String[] args) {
        Restaurent restaurent1 = new Restaurent(1, "Restaurant A", 2);
        Restaurent restaurent2 = new Restaurent(2, "Restaurant B", 1);
        Restaurent restaurent3 = new Restaurent(3, "Restaurant C", 1);
        Restaurent restaurent4 = new Restaurent(2, "Restaurant D", 12);
        Restaurent restaurent5 = new Restaurent(3, "Restaurant E", 1);


        System.out.println(restaurent1); // Affiche
        System.out.println(restaurent2); // )"
        System.out.println(restaurent3);
    
        System.out.println(restaurent4); // Affiche "Restaurant B (Capacité : 30)"
        System.out.println(restaurent5);
    }
}
