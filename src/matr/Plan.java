
package matr;

/**
 *
 * @author Jedabero
 */
public class Plan {
    
    public enum tipo{
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
    
    public Plan(Estudiante estudiante){
        if (null != estudiante) {
            tipoPlan = tipo.PLAN;
            est = estudiante;
        } else {
            tipoPlan = tipo.PENSUM;
        }
    }
    
    public boolean vacio() {
        return pri == null;
    }

    public boolean hayUnSoloNodo() {
        return pri == ult;
    }

    public void agregarPrimero(Periodo p) {
        if (vacio()) {
            ult = p;
        } else {
            pri.setAnt(p);
            p.setSig(pri);
        }
        pri = p;
    }

    public void agregarUltimo(Periodo p) {
        if (vacio()) {
            pri = p;
        } else {
            ult.setSig(p);
            p.setAnt(ult);
        }
        ult = p;
    }
    
    public Plan periodosConCreditos(int c) {
        Plan lista = new Plan();
        Periodo x = pri;
        while (null != x) {
            if (c == x.getTotalCreditos()) {
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
        } else {
            throw new Exception("No existe el periodo buscada.");
        }
    }

    public boolean eliminarPrimero() {
        if (vacio()) {
            return false;
        } else {
            if (hayUnSoloNodo()) {
                pri = null;
                ult = null;
            } else {
                pri = pri.getSig();
                pri.setAnt(null);
            }
            return true;
        }
    }

    public boolean eliminarUltimo() {
        if (vacio()) {
            return false;
        } else {
            if (hayUnSoloNodo()) {
                pri = null;
                ult = null;
            } else {
                ult = ult.getAnt();
                ult.setSig(null);
            }
            return true;
        }
    }

    public boolean eliminar(Periodo y) {
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
                y = null;
                x.setSig(z);
                z.setAnt(y);
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

    @Override
    public String toString() {
        String m = tipoPlan+"\n"+((null!=est)?est:"");
        if (vacio()) {
            m += "vacio";
        } else {
            Periodo x = pri;
            while (null != x) {
                m += x + "\n";
                x = x.getSig();
            }
        }
        return m;
    }
}
