
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
    
    
}
