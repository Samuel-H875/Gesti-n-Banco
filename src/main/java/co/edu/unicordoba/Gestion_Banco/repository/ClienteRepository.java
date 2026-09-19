package co.edu.unicordoba.Gestion_Banco.repository;

import co.edu.unicordoba.Gestion_Banco.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    public synchronized Cliente save(Cliente cliente) {
        if (!clientes.contains(cliente)) clientes.add(cliente);
        return cliente;
    }

    public synchronized List<Cliente> findAll() { return new ArrayList<>(clientes); }

    public Optional<Cliente> findByCorreo(String correo) {
        return clientes.stream().filter(c -> c.getCorreo().equalsIgnoreCase(correo)).findFirst();
    }

    public Optional<Cliente> findByNumeroCliente(String numeroCliente) {
        return clientes.stream().filter(c -> c.getNumeroCliente().equals(numeroCliente)).findFirst();
    }

    public boolean existsByDocumento(String documento) {
        return clientes.stream().anyMatch(c -> c.getDocumento().equals(documento));
    }

    public boolean existsByCorreo(String correo) { return findByCorreo(correo).isPresent(); }

    public synchronized boolean deleteByNumeroCliente(String numeroCliente) {
        return clientes.removeIf(c -> c.getNumeroCliente().equals(numeroCliente));
    }
}
