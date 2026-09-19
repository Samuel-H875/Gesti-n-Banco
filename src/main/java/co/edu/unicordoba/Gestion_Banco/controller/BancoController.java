package co.edu.unicordoba.Gestion_Banco.controller;

import co.edu.unicordoba.Gestion_Banco.model.*;
import co.edu.unicordoba.Gestion_Banco.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BancoController {
    private final ClienteRepository clientes;
    private final CuentaRepository cuentas;
    private final EmpleadoRepository empleados;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BancoController(ClienteRepository clientes, CuentaRepository cuentas, EmpleadoRepository empleados) {
        this.clientes = clientes;
        this.cuentas = cuentas;
        this.empleados = empleados;
    }

    @GetMapping("/") public String inicio() { return "index"; }
    @GetMapping("/login") public String login() { return "login"; }
    @GetMapping("/registro") public String registro() { return "registrar"; }

    @PostMapping("/registro")
    public String registrar(@RequestParam String nombre, @RequestParam String documento, @RequestParam String correo,
                            @RequestParam String telefono, @RequestParam String password, Model m) {
        if (clientes.existsByDocumento(documento) || clientes.existsByCorreo(correo)) {
            m.addAttribute("error", "El documento o correo ya está registrado");
            return "registrar";
        }
        String numeroCliente = "C" + System.currentTimeMillis();
        Cliente cliente = new Cliente(nombre, documento, correo, numeroCliente, telefono, encoder.encode(password));
        clientes.save(cliente);
        // Cada cliente recibe una única cuenta inicial.
        cuentas.save(new CuentaBancaria(generarNumeroCuenta(), 0, "Ahorros", numeroCliente));
        m.addAttribute("mensaje", "Registro exitoso. Tu cuenta de ahorros fue creada.");
        return "login";
    }

    @PostMapping("/login")
    public String entrar(@RequestParam String correo, @RequestParam String password, HttpSession session, Model m) {
        var cliente = clientes.findByCorreo(correo);
        if (cliente.isPresent() && encoder.matches(password, cliente.get().getPassword())) {
            session.setAttribute("cliente", cliente.get());
            return "redirect:/panel";
        }
        if (correo.equals("admin@banco.com") && password.equals("Admin123*")) {
            session.setAttribute("admin", true);
            return "redirect:/admin";
        }
        m.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/panel")
    public String panel(HttpSession session, Model m) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/login";
        m.addAttribute("cliente", cliente);
        var cuentasCliente = cuentas.findByNumeroCliente(cliente.getNumeroCliente());
        m.addAttribute("cuentas", cuentasCliente);
        double totalSaldo = 0;
        for (CuentaBancaria cuenta : cuentasCliente) {
            if (cuenta != null) totalSaldo += cuenta.getSaldo();
        }
        m.addAttribute("totalSaldo", totalSaldo);
        return "panel";
    }

    @PostMapping("/operacion")
    public String operar(@RequestParam String numeroCuenta, @RequestParam double cantidad,
                         @RequestParam String accion, HttpSession session, Model m) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/login";
        var cuenta = cuentas.findByNumeroCuenta(numeroCuenta).orElse(null);
        String mensaje;
        if (cuenta == null || !cuenta.getNumeroCliente().equals(cliente.getNumeroCliente())) {
            mensaje = "Cuenta no encontrada";
        } else {
            try {
                if ("depositar".equals(accion)) { cuenta.depositar(cantidad); mensaje = "Depósito realizado correctamente"; }
                else { mensaje = cuenta.retirar(cantidad) ? "Retiro realizado correctamente" : "Saldo insuficiente o cantidad inválida"; }
            } catch (Exception e) { mensaje = e.getMessage(); }
        }
        m.addAttribute("mensaje", mensaje);
        return panel(session, m);
    }

    @PostMapping("/perfil")
    public String actualizarPerfil(@RequestParam String nombre, @RequestParam String telefono,
                                   HttpSession session, Model m) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/login";
        cliente.setNombre(nombre); cliente.setTelefono(telefono);
        session.setAttribute("cliente", cliente);
        m.addAttribute("mensaje", "Datos actualizados correctamente");
        return panel(session, m);
    }

    @GetMapping("/admin")
    public String admin(HttpSession session, Model m) {
        if (session.getAttribute("admin") == null) return "redirect:/login";
        cargarAdmin(m); return "admin";
    }

    @PostMapping("/admin/cuenta")
    public String crearCuentaAdmin(@RequestParam String numeroCliente, @RequestParam String tipo, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        if (!cuentas.existsByNumeroCliente(numeroCliente)) cuentas.save(new CuentaBancaria(generarNumeroCuenta(), 0, tipo, numeroCliente));
        return "redirect:/admin";
    }

    @PostMapping("/admin/cuenta/editar")
    public String editarCuenta(@RequestParam String numeroCuenta, @RequestParam String tipo,
                               @RequestParam boolean activa, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        cuentas.findByNumeroCuenta(numeroCuenta).ifPresent(c -> { c.setTipo(tipo); c.setActiva(activa); });
        return "redirect:/admin";
    }

    @PostMapping("/admin/cuenta/eliminar")
    public String eliminarCuenta(@RequestParam String numeroCuenta, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        cuentas.deleteByNumeroCuenta(numeroCuenta); return "redirect:/admin";
    }

    @PostMapping("/admin/cliente/editar")
    public String editarCliente(@RequestParam String numeroCliente, @RequestParam String nombre,
                                @RequestParam String correo, @RequestParam String telefono, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        clientes.findByNumeroCliente(numeroCliente).ifPresent(c -> { c.setNombre(nombre); c.setCorreo(correo); c.setTelefono(telefono); });
        return "redirect:/admin";
    }

    @PostMapping("/admin/empleado")
    public String crearEmpleado(@RequestParam String nombre, @RequestParam String documento,
                               @RequestParam String correo, @RequestParam String cargo,
                               @RequestParam double salario, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        empleados.save(new Empleado(nombre, documento, correo, cargo, salario)); return "redirect:/admin";
    }

    @PostMapping("/admin/empleado/eliminar")
    public String eliminarEmpleado(@RequestParam String documento, HttpSession s) {
        if (s.getAttribute("admin") == null) return "redirect:/login";
        empleados.deleteByDocumento(documento); return "redirect:/admin";
    }

    private void cargarAdmin(Model m) {
        m.addAttribute("clientes", clientes.findAll());
        m.addAttribute("cuentas", cuentas.findAll());
        m.addAttribute("empleados", empleados.findAll());
    }

    private String generarNumeroCuenta() { return "CTA" + System.currentTimeMillis(); }

    @GetMapping("/logout")
    public String salir(HttpSession session) { session.invalidate(); return "redirect:/"; }
}
