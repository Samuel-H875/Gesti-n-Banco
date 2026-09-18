package co.edu.unicordoba.Gestion_Banco.ejercicios.herencia;
public abstract class EmpleadoEjercicio {protected String nombre; public EmpleadoEjercicio(String n){nombre=n;} public abstract double calcularPagoMensual(); public double bonificacion(){return 100000;} public String getNombre(){return nombre;}}
