package co.edu.unicordoba.Gestion_Banco.model;

public class Cliente extends Persona {

    private Long id;
    private String numeroCliente;
    private String telefono;
    private String password;

    public Cliente() {
    }

    public Cliente(String n, String d, String c, String nc, String t, String p) {
        super(n, d, c);
        this.numeroCliente = nc;
        this.telefono = t;
        this.password = p;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}