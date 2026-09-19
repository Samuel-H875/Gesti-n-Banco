package co.edu.unicordoba.Gestion_Banco.repository;

import co.edu.unicordoba.Gestion_Banco.model.CuentaBancaria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CuentaRepository {
    private final List<CuentaBancaria> cuentas = new ArrayList<>();

    public synchronized CuentaBancaria save(CuentaBancaria cuenta) {
        if (!cuentas.contains(cuenta)) cuentas.add(cuenta);
        return cuenta;
    }

    public synchronized List<CuentaBancaria> findAll() { return new ArrayList<>(cuentas); }

    public List<CuentaBancaria> findByNumeroCliente(String numeroCliente) {
        return cuentas.stream().filter(c -> c.getNumeroCliente().equals(numeroCliente)).toList();
    }

    public Optional<CuentaBancaria> findByNumeroCuenta(String numeroCuenta) {
        return cuentas.stream().filter(c -> c.getNumeroCuenta().equals(numeroCuenta)).findFirst();
    }

    public boolean existsByNumeroCliente(String numeroCliente) { return !findByNumeroCliente(numeroCliente).isEmpty(); }

    public synchronized boolean deleteByNumeroCuenta(String numeroCuenta) {
        return cuentas.removeIf(c -> c.getNumeroCuenta().equals(numeroCuenta));
    }
}
