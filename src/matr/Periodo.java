package matr;

import io.IO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 *
 * @author jedabero
 */
public class Periodo implements Serializable {

    private final ArrayList<Asignatura> listaAsignaturas;

    private final Plan plan;

    public ArrayList<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    private final String nombre;

    public Periodo() {
        this("N/A", null);
    }

    public Periodo(String n, Plan plan) {
        nombre = n;
        this.plan = plan;

        listaAsignaturas = new ArrayList<>();

    }

    public boolean vacio() {
        return listaAsignaturas.isEmpty();
    }

    public boolean hayUnSolo() {
        return 1 == listaAsignaturas.size();
    }

    public void agregar(Asignatura a) {
        listaAsignaturas.add(a);
    }

    public Periodo asiganturasConCreditos(int c) {
        Periodo lista = new Periodo();
        for (Asignatura x : listaAsignaturas) {
            if (c == x.getCreditos()) {
                lista.agregar(x);
            }
        }
        return lista;
    }

    public Asignatura buscarAsignaturaPorCodigo(String cod) {
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(cod)) {
                return asignatura;
            }
        }
        return null;
    }

    public boolean eliminar(Asignatura a) {
        return listaAsignaturas.contains(a) && listaAsignaturas.remove(a);
    }

    public int getTotalCreditosMatriculados() {
        int totalCreditosMatriculados = 0;
        for (Asignatura asignatura : listaAsignaturas) {
            totalCreditosMatriculados += asignatura.getCreditos();
        }
        return totalCreditosMatriculados;
    }

    public int getTotalAsignaturas() {
        return listaAsignaturas.size();
    }

    public double getPromedio() {
        double promedio = 0;
        for (Asignatura asignatura : listaAsignaturas) {
            promedio += asignatura.getNota();
        }
        promedio *= 1000;
        int redondeo = (int) (promedio / getTotalAsignaturas());
        promedio = ((double) redondeo) / 1000;
        return promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public Plan getPlan() {
        return plan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.listaAsignaturas);
        hash = 59 * hash + Objects.hashCode(this.plan);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.listaAsignaturas, other.listaAsignaturas)) {
            return false;
        }
        if (!Objects.equals(this.plan, other.plan)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("(").append(nombre);
        if (vacio()) {
            m.append("vacia");
        } else {
            for (Asignatura x : listaAsignaturas) {
                m.append(IO.LS.s).append(x);
            }
        }
        return m.toString();
    }

}
