package co.edu.unicordoba.Gestion_Banco.model;

public class CuentaBancaria {
    private Long id;
    private String numeroCuenta;
    private double saldo;
    private String tipo;
    private String numeroCliente;
    private boolean activa = true;

    public CuentaBancaria() {}

    public CuentaBancaria(String numeroCuenta, double saldo, String tipo, String numeroCliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipo = tipo;
        this.numeroCliente = numeroCliente;
    }

    public void depositar(double cantidad) {
        if (!activa) throw new IllegalStateException("La cuenta está bloqueada");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        saldo += cantidad;
    }

    public boolean retirar(double cantidad) {
        if (!activa || cantidad <= 0 || cantidad > saldo) return false;
        saldo -= cantidad;
        return true;
    }

    public Long getId() { return id; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String v) { numeroCuenta = v; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double v) { saldo = v; }
    public String getTipo() { return tipo; }
    public void setTipo(String v) { tipo = v; }
    public String getNumeroCliente() { return numeroCliente; }
    public void setNumeroCliente(String v) { numeroCliente = v; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean v) { activa = v; }
}
