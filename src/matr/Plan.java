package matr;

import java.io.Serializable;

/**
 *
 * @author Jedabero
 */
public class Plan implements Serializable {

    public enum tipo {

        PENSUM,
        PLAN
    }

    private Estudiante est;
    private tipo tipoPlan;

    private Periodo pri;
    private Periodo ult;

    public Plan() {
        this(null);
    }

    public Plan(Estudiante estudiante) {
        if (null != estudiante) {
            tipoPlan = tipo.PLAN;
            est = estudiante;
        } else {
            tipoPlan = tipo.PENSUM;
        }
        totalCreditos = 0;
        totalPeriodos = 0;
    }

    public boolean vacio() {
        return pri == null;
    }

    public boolean hayUnSoloNodo() {
        return (pri == ult) && !vacio();
    }

    public void agregarPrimero(Periodo p) {
        if (vacio()) {
            ult = p;
        } else {
            pri.setAnt(p);
            p.setSig(pri);
        }
        pri = p;
        totalPeriodos++;
        totalCreditos += p.getTotalCreditosMatriculados();
    }

    public void agregarUltimo(Periodo p) {
        if (vacio()) {
            pri = p;
        } else {
            ult.setSig(p);
            p.setAnt(ult);
        }
        ult = p;
        totalPeriodos++;
        totalCreditos += p.getTotalCreditosMatriculados();
    }

    public Plan periodosConCreditos(int c) {
        Plan lista = new Plan();
        Periodo x = pri;
        while (null != x) {
            if (c == x.getTotalCreditosMatriculados()) {
                lista.agregarUltimo(x);
            }
            x = x.getSig();
        }
        return lista;
    }

    public Periodo buscar(Periodo p) {
        Periodo x = pri;
        while (null != x && p != x) {
            x = x.getSig();
        }
        return x;
    }

    public void agregarDespuesDe(Periodo nuevo, Periodo viejo) throws Exception {
        Periodo x = buscar(viejo);
        if (null != x) {
            Periodo z = x.getSig();
            x.setSig(nuevo);
            nuevo.setSig(z);
            z.setAnt(nuevo);

            totalPeriodos++;
            totalCreditos += nuevo.getTotalCreditosMatriculados();
        } else {
            throw new Exception("No existe el periodo buscada.");
        }
    }

    public boolean eliminarPrimero() {
        if (vacio()) {
            return false;
        } else {
            Periodo temp = pri;
            if (hayUnSoloNodo()) {
                pri = null;
                ult = null;
            } else {
                pri = pri.getSig();
                pri.setAnt(null);
            }
            totalPeriodos--;
            totalCreditos -= temp.getTotalCreditosMatriculados();
            return true;
        }
    }

    public boolean eliminarUltimo() {
        if (vacio()) {
            return false;
        } else {
            Periodo temp = pri;
            if (hayUnSoloNodo()) {
                pri = null;
                ult = null;
            } else {
                ult = ult.getAnt();
                ult.setSig(null);
            }
            totalPeriodos--;
            totalCreditos += temp.getTotalCreditosMatriculados();
            return true;
        }
    }

    public boolean eliminar(Periodo p) {
        Periodo y = buscar(p);
        if (null != y) {
            if (pri == y) {
                return eliminarPrimero();
            } else if (ult == y) {
                return eliminarUltimo();
            } else {
                Periodo x = y.getAnt();
                Periodo z = y.getSig();
                y.setAnt(null);
                y.setSig(null);
                x.setSig(z);
                z.setAnt(x);
                totalPeriodos--;
                totalCreditos -= y.getTotalCreditosMatriculados();
                return true;
            }
        } else {
            return false;
        }
    }

    public Periodo getPri() {
        return pri;
    }

    public Periodo getUlt() {
        return ult;
    }

    public Estudiante getEstudiante() {
        return est;
    }

    public void setEstudiante(Estudiante est) {
        this.est = est;
    }

    private int totalPeriodos;
    private int totalCreditos;

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public int getTotalPeriodos() {
        return totalPeriodos;
    }

    @Override
    public String toString() {
        StringBuilder m = new StringBuilder(tipoPlan.toString()).append("\n");
        m.append((null != est) ? est : "").append("\n");
        if (vacio()) {
            m.append("vacio");
        } else {
            Periodo x = pri;
            while (null != x) {
                m.append(x).append("\n");
                x = x.getSig();
            }
        }
        return m.toString();
    }
    
    public void reorganizarasignaturas() {
        Periodo p = pri;
        //TODO jojojo
        
        
        
    }
    
}
