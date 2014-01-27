
package matr;

/**
 *
 * @author Jedabero
 */
public class Plan {
    
    private Nodo<Periodo> pri;
    private Nodo<Periodo> ult;
    
    public Plan(){
        
    }
    
    public boolean vacio() {
        return pri == null;
    }

    public boolean hayUnSoloNodo() {
        return pri == ult;
    }

    public Nodo crearNodo(Periodo a) {
        return new Nodo(a);
    }

    public void agregarPrimero(Nodo<Periodo> p) {
        if (vacio()) {
            ult = p;
        } else {
            pri.setAnt(p);
            p.setSig(pri);
        }
        pri = p;
    }

    public void agregarPrimero(Periodo a) {
        agregarPrimero(crearNodo(a));
    }

    public void agregarUltimo(Nodo p) {
        if (vacio()) {
            pri = p;
        } else {
            ult.setSig(p);
            p.setAnt(ult);
        }
        ult = p;
    }
    
    public void agregarUltimo(Periodo a) {
        agregarUltimo(crearNodo(a));
    }
    
    
    public Lista periodosConCreditos(int c) {
        Lista lista = new Lista();
        Nodo<Periodo> x = pri;
        while (null != x) {
            if (c == x.getItem().getTotalCreditos()) {
                lista.agregarFinal(x);
            }
            x = x.getSig();
        }
        return lista;
    }
    
    public Nodo<Periodo> buscar(Periodo p) {
        Nodo<Periodo> x = pri;
        while (null != x && p != x.getItem()) {
            x = x.getSig();
        }
        return x;
    }

    public void agregarDespuesDe(Nodo<Periodo> nuevo, Periodo viejo) throws Exception {
        Nodo<Periodo> x = buscar(viejo);
        if (null != x) {
            Nodo<Periodo> z = x.getSig();
            x.setSig(nuevo);
            nuevo.setSig(z);
            z.setAnt(nuevo);
        } else {
            throw new Exception("No existe el periodo buscada.");
        }
    }

    public void agregarDespuesDe(Periodo nuevo, Periodo viejo) throws Exception {
        agregarDespuesDe(crearNodo(nuevo), viejo);
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

    public boolean eliminar(Nodo y) {
        if (null != y) {
            if (pri == y) {
                return eliminarPrimero();
            } else if (ult == y) {
                return eliminarUltimo();
            } else {
                Nodo x = y.getAnt();
                Nodo z = y.getSig();
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

    public boolean eliminar(Periodo a) {
        return eliminar(buscar(a));
    }

    public Nodo<Periodo> getPri() {
        return pri;
    }

    public Nodo<Periodo> getUlt() {
        return ult;
    }

    @Override
    public String toString() {
        String m = "\n";
        if (vacio()) {
            m += "vacio";
        } else {
            Nodo x = pri;
            while (null != x) {
                m += x + "\n";
                x = x.getSig();
            }
        }
        return m;
    }
}
