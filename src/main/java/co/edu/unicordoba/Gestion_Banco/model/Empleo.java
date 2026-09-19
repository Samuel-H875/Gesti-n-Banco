package co.edu.unicordoba.Gestion_Banco.model;

public class Empleo {
    private String nombre;
    private double salario;

    public Empleo() {}

    public Empleo(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
