package co.edu.unicordoba.Gestion_Banco.ejercicios.herencia;
import java.util.*;
public class MainHerencia{public static void main(String[] a){List<EmpleadoEjercicio> l=List.of(new Asalariado("Ana",2000000),new PorHoras("Luis",15000,80),new Comisionista("Marta",1000000,5000000,.05));double total=0;for(EmpleadoEjercicio e:l)total+=e.calcularPagoMensual()+e.bonificacion();System.out.println("Nómina: "+total);}}
