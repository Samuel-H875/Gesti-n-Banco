package co.edu.unicordoba.Gestion_Banco.repository;

import co.edu.unicordoba.Gestion_Banco.model.Empleo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleoRepository {
    private final List<Empleo> empleos = new ArrayList<>();

    public EmpleoRepository() {
        empleos.add(new Empleo("Cajero bancario", 1800000));
        empleos.add(new Empleo("Asesor comercial", 2400000));
        empleos.add(new Empleo("Ejecutivo de cuenta", 3200000));
        empleos.add(new Empleo("Director de oficina", 5200000));
        empleos.add(new Empleo("Analista de crédito", 2800000));
        empleos.add(new Empleo("Analista de riesgos", 3500000));
        empleos.add(new Empleo("Gerente de banca empresas", 7000000));
        empleos.add(new Empleo("Científico de datos", 5500000));
        empleos.add(new Empleo("Ingeniero de software", 5000000));
        empleos.add(new Empleo("Especialista en ciberseguridad", 5800000));
        empleos.add(new Empleo("Analista de cumplimiento legal", 3300000));
    }

    public synchronized List<Empleo> findAll() {
        return new ArrayList<>(empleos);
    }

    public synchronized Optional<Empleo> findByNombre(String nombre) {
        return empleos.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public synchronized boolean existsByNombre(String nombre) {
        return findByNombre(nombre).isPresent();
    }

    public synchronized Empleo save(Empleo empleo) {
        empleos.add(empleo);
        return empleo;
    }

    public synchronized boolean update(String nombreAnterior, String nuevoNombre, double nuevoSalario) {
        Optional<Empleo> encontrado = findByNombre(nombreAnterior);
        if (encontrado.isEmpty()) return false;
        Empleo empleo = encontrado.get();
        empleo.setNombre(nuevoNombre);
        empleo.setSalario(nuevoSalario);
        return true;
    }

    public synchronized boolean deleteByNombre(String nombre) {
        return empleos.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
    }
}
