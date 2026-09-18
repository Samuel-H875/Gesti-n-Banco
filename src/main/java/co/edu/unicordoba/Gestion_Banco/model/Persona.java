package co.edu.unicordoba.Gestion_Banco.model;

public class Persona {
    private String nombre;
    private String documento;
    private String correo;

    public Persona() {
    }

    public Persona(String n, String d, String c) {
        nombre = n;
        documento = d;
        correo = c;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String v) {
        nombre = v;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String v) {
        documento = v;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String v) {
        correo = v;
    }
}
