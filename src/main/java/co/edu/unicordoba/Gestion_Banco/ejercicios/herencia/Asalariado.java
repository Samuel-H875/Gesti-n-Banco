package co.edu.unicordoba.Gestion_Banco.ejercicios.herencia;
public class Asalariado extends EmpleadoEjercicio {private double sueldo; public Asalariado(String n,double s){super(n);sueldo=s;} public double calcularPagoMensual(){return sueldo;}}
