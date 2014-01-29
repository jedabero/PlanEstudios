package matr;

/**
 *
 * @author jedabero
 */
public class Periodo {

    private Nodo<Asignatura> cab;
    private Nodo<Asignatura> fin;

    private Periodo sig;
    private Periodo ant;

    public boolean vacio() {
        return cab == null;
    }

    public boolean hayUnSoloNodo() {
        return cab == fin;
    }

    public Nodo crearNodo(Asignatura a) {
        return new Nodo(a);
    }

    public void agregarCabecera(Nodo<Asignatura> p) {
        if (vacio()) {
            fin = p;
        } else {
            cab.setAnt(p);
            p.setSig(cab);
        }
        cab = p;
        init();
    }

    public void agregarCabecera(Asignatura a) {
        agregarCabecera(crearNodo(a));
    }

    public void agregarFinal(Nodo p) {
        if (vacio()) {
            cab = p;
        } else {
            fin.setSig(p);
            p.setAnt(fin);
        }
        fin = p;
        init();
    }

    public void agregarFinal(Asignatura a) {
        agregarFinal(crearNodo(a));
    }

    public Periodo asiganturasConCreditos(int c) {
        Periodo lista = new Periodo();
        Nodo<Asignatura> x = cab;
        while (null != x) {
            if (c == x.getItem().getCreditos()) {
                lista.agregarFinal(x);
            }
            x = x.getSig();
        }
        return lista;
    }

    public Nodo<Asignatura> buscarPorCodigo(String cod) {
        Nodo<Asignatura> x = cab;
        while (null != x && !x.getItem().getCodigo().equalsIgnoreCase(cod)) {
            x = x.getSig();
        }
        return x;
    }

    public Asignatura buscarAsignaturaPorCod(String cod) {
        return buscarPorCodigo(cod).getItem();
    }

    public void agregarDespuesDe(Nodo<Asignatura> nuevo, Asignatura vieja) throws Exception {
        Nodo<Asignatura> x = buscarPorCodigo(vieja.getCodigo());
        if (null != x) {
            Nodo<Asignatura> z = x.getSig();
            x.setSig(nuevo);
            nuevo.setSig(z);
            z.setAnt(nuevo);
            init();
        } else {
            throw new Exception("No existe la asignatura buscada.");
        }
    }

    public void agregarDespuesDe(Asignatura nueva, Asignatura vieja) throws Exception {
        agregarDespuesDe(crearNodo(nueva), vieja);
    }

    public boolean eliminarCabecera() {
        if (vacio()) {
            return false;
        } else {
            if (hayUnSoloNodo()) {
                cab = null;
                fin = null;
            } else {
                cab = cab.getSig();
                cab.setAnt(null);
            }
            init();
            return true;
        }
    }

    public boolean eliminarFinal() {
        if (vacio()) {
            return false;
        } else {
            if (hayUnSoloNodo()) {
                cab = null;
                fin = null;
            } else {
                fin = fin.getAnt();
                fin.setSig(null);
            }
            init();
            return true;
        }
    }

    public boolean eliminar(Nodo y) {
        if (null != y) {
            if (cab == y) {
                return eliminarCabecera();
            } else if (fin == y) {
                return eliminarFinal();
            } else {
                Nodo x = y.getAnt();
                Nodo z = y.getSig();
                y.setAnt(null);
                y.setSig(null);
                y = null;
                x.setSig(z);
                z.setAnt(y);
                init();
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean eliminar(Asignatura a) {
        return eliminar(buscarPorCodigo(a.getCodigo()));
    }

    public Nodo<Asignatura> getCab() {
        return cab;
    }

    public Nodo<Asignatura> getFin() {
        return fin;
    }

    private int totalCreditos;
    private int totalAsignaturas;
    private double promedio;

    private String nombre;

    public Periodo() {
        this("N/A");
    }

    public Periodo(String n) {
        nombre = n;
    }

    private void init() {
        int tc = 0;
        int ta = 0;
        double p = 0d;
        Nodo<Asignatura> x = cab;
        while (null != x) {
            tc += x.getItem().getCreditos();
            p += x.getItem().getNota();
            ta++;
            x = x.getSig();
        }
        p /= ta;
        totalCreditos = tc;
        totalAsignaturas = ta;
        promedio = Math.rint(p * 100) / 100;

    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public int getTotalAsignaturas() {
        return totalAsignaturas;
    }

    public double getPromedio() {
        return promedio;
    }

    @Override
    public String toString() {
        String m = "¬" + nombre;
        if (vacio()) {
            m += "vacia";
        } else {
            Nodo x = cab;
            while (null != x) {
                m += "\n" + x;
                x = x.getSig();
            }
        }
        return m;
    }

    public Periodo getSig() {
        return sig;
    }

    public void setSig(Periodo sig) {
        this.sig = sig;
    }

    public Periodo getAnt() {
        return ant;
    }

    public void setAnt(Periodo ant) {
        this.ant = ant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
