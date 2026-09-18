package co.edu.unicordoba.Gestion_Banco.ejercicios.composicion;

import java.util.ArrayList;
import java.util.List;

public class Formulario {

    private final List<Campo> campos = new ArrayList<>();

    public Formulario() {
        campos.add(new Campo());
        campos.add(new Campo());
        campos.add(new Campo());
    }

    public boolean esValido() {
        return campos.stream()
                .allMatch(c -> !c.getValor().isBlank());
    }

    public void llenar(String n, String c, String p) {
        campos.get(0).setValor(n);
        campos.get(1).setValor(c);
        campos.get(2).setValor(p);
    }

    private class Campo {

        private String valor = "";

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }
}