package matr;

import io.IO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Jedabero
 */
public class Plan implements Serializable {

    public enum TIPO {

        PENSUM,
        PLAN
    }

    private Estudiante est;
    private final TIPO tipoPlan;

    private final ArrayList<Periodo> listaPeriodos;

    public Plan() {
        this(null);
    }

    public Plan(Estudiante estudiante) {
        if (null != estudiante) {
            tipoPlan = TIPO.PLAN;
            est = estudiante;
        } else {
            tipoPlan = TIPO.PENSUM;
        }

        listaPeriodos = new ArrayList<>();

    }

    public boolean vacio() {
        return listaPeriodos.isEmpty();
    }

    public boolean hayUnSoloNodo() {
        return 1 == listaPeriodos.size();
    }

    public void agregar(Periodo p) {
        listaPeriodos.add(p);
    }

    public Plan periodosConCreditos(int c) {
        Plan lista = new Plan();
        for (Periodo x : listaPeriodos) {
            if (c == x.getTotalCreditosMatriculados()) {
                lista.agregar(x);
            }
        }
        return lista;
    }

    public Periodo buscar(Periodo p) {
        int index = listaPeriodos.indexOf(p);
        return (-1 < index) ? listaPeriodos.get(index) : null;
    }

    public Periodo buscarPeriodoAnteriorA(Periodo p) {
        int index = listaPeriodos.indexOf(p);
        return (0 < index) ? listaPeriodos.get(index - 1) : null;
    }

    public Periodo buscarPeriodoPorNombre(String nombre) {
        for (Periodo periodo : listaPeriodos) {
            if (periodo.getNombre().equalsIgnoreCase(nombre)) {
                return periodo;
            }
        }
        return null;
    }

    public boolean eliminar(Periodo p) {
        return listaPeriodos.contains(p) && listaPeriodos.remove(p);
    }

    public Asignatura buscarAsignaturaPorCodigo(String cod) {
        Asignatura asig = null;
        for (Periodo periodo : listaPeriodos) {
            asig = periodo.buscarAsignaturaPorCodigo(cod);
            if (null != asig) {
                return asig;
            }
        }
        return asig;
    }

    public Estudiante getEstudiante() {
        return est;
    }

    public void setEstudiante(Estudiante est) {
        this.est = est;
    }

    public int getTotalCreditos() {
        int totalCreditos = 0;
        for (Periodo periodo : listaPeriodos) {
            totalCreditos += periodo.getTotalCreditosMatriculados();
        }
        return totalCreditos;
    }

    public int getTotalPeriodos() {
        return listaPeriodos.size();
    }

    public ArrayList<Periodo> getListaPeriodos() {
        return listaPeriodos;
    }

    public Plan reorganizarAsignaturas() {
        Plan plan = new Plan(this.getEstudiante());
        if (!vacio()) {
            for (Periodo p : listaPeriodos) {
                for (Asignatura a : p.getListaAsignaturas()) {
                    String per = a.getPeriodo().trim();
                    Periodo periodoPer;
                    Periodo periodoNivel;

                    if (!per.isEmpty() && null == (periodoPer = plan.buscarPeriodoPorNombre(per))) {
                        plan.agregar(new Periodo(per, plan));
                    } else if (per.isEmpty() && null == (periodoNivel = plan.buscarPeriodoPorNombre(a.getNivel()))) {
                        plan.agregar(new Periodo(a.getNivel(), plan));
                    }

                    if (null != (periodoPer = plan.buscarPeriodoPorNombre(per))) {
                        periodoPer.agregar(a);
                    } else if (null != (periodoNivel = plan.buscarPeriodoPorNombre(a.getNivel()))) {
                        periodoNivel.agregar(a);
                    }

                }
            }
        }

        return plan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.tipoPlan);
        hash = 11 * hash + Objects.hashCode(this.listaPeriodos);
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
        final Plan other = (Plan) obj;
        if (this.tipoPlan != other.tipoPlan) {
            return false;
        }
        return Objects.equals(this.listaPeriodos, other.listaPeriodos);
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder(tipoPlan.toString()).append(IO.LS.p);
        m.append((null != est) ? est : "").append(IO.LS.p);
        if (vacio()) {
            m.append("vacio");
        } else {
            for (Periodo x : listaPeriodos) {
                m.append(x).append(IO.LS.p);
            }
        }
        return m.toString();
    }
}
