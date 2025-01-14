package dataClases;

public class Room {

    private int ID;
    private int numero;
    private String tipo;
    private double precio;

    public Room(int ID, int numero, String tipo, double precio) {
        this.ID = ID;
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
