package co.edu.unicordoba.Gestion_Banco.repository;

import co.edu.unicordoba.Gestion_Banco.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpleadoRepository {
    private final List<Empleado> empleados = new ArrayList<>();

    public synchronized Empleado save(Empleado empleado) {
        if (!empleados.contains(empleado)) empleados.add(empleado);
        return empleado;
    }

    public synchronized List<Empleado> findAll() { return new ArrayList<>(empleados); }
    public synchronized boolean deleteByDocumento(String documento) { return empleados.removeIf(e -> e.getDocumento().equals(documento)); }
}
