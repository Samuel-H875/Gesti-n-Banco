package co.edu.unicordoba.Gestion_Banco.ejercicios.herencia;
public class Comisionista extends EmpleadoEjercicio {private double base,ventas,porcentaje;public Comisionista(String n,double b,double v,double p){super(n);base=b;ventas=v;porcentaje=p;}public double calcularPagoMensual(){return base+ventas*porcentaje;}}
