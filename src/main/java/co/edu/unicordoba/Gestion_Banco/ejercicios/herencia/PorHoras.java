package co.edu.unicordoba.Gestion_Banco.ejercicios.herencia;
public class PorHoras extends EmpleadoEjercicio {private double tarifa;private int horas;public PorHoras(String n,double t,int h){super(n);tarifa=t;horas=h;}public double calcularPagoMensual(){return tarifa*horas;}}
