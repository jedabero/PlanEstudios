package matr;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jedabero
 */
public class Periodo implements Serializable {

    private final ArrayList<Asignatura> listaAsignaturas;

    private final Plan plan;
    
    public ArrayList<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    private String nombre;

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

    public boolean hayUnSoloNodo() {
        return 1 == listaAsignaturas.size();
    }

    public Nodo crearNodo(Asignatura a) {
        return new Nodo(a);
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

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder("(").append(nombre);
        if (vacio()) {
            m.append("vacia");
        } else {
            for (Asignatura x : listaAsignaturas) {
                m.append("\n").append(x);
            }
        }
        return m.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Plan getPlan() {
        return plan;
    }
    
}
