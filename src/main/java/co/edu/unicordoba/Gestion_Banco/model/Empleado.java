package co.edu.unicordoba.Gestion_Banco.model;
public class Empleado extends Persona {
    private String cargo; private double salario;
    public Empleado() {}
    public Empleado(String nombre,String documento,String correo,String cargo,double salario){super(nombre,documento,correo);this.cargo=cargo;this.salario=salario;}
    public String getCargo(){return cargo;} public double getSalario(){return salario;}
    public void setCargo(String v){cargo=v;} public void setSalario(double v){salario=v;}
}