package co.edu.unicordoba.Gestion_Banco.interfaces;

public interface OperacionesBancarias {

    void depositar(double cantidad);

    boolean retirar(double cantidad);

    double consultarSaldo();
}
