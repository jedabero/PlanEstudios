package matr;

/**
 *
 * @author jedabero
 */
public class Lista {

    private Nodo cab;
    private Nodo fin;

    public Lista() {
    }

    public boolean vacio() {
        return cab == null;
    }

    public boolean hayUnSoloNodo() {
        return cab == fin;
    }

    public Nodo crearNodo(Asignatura a) {
        return new Nodo(a);
    }

    public class Nodo {

        public Asignatura asign;
        public Nodo ant;
        public Nodo sig;

        Nodo() {
        }

        Nodo(Asignatura asignatura) {
            this.asign = asignatura;
        }

        @Override
        public String toString() {
            return asign.toString();
        }

    }

    public void agregarCabecera(Nodo p) {
        if (vacio()) {
            fin = p;
        } else {
            cab.ant = p;
            p.sig = cab;
        }
        cab = p;
    }

    public void agregarCabecera(Asignatura a) {
        agregarCabecera(crearNodo(a));
    }

    public void agregarFinal(Nodo p) {
        if (vacio()) {
            cab = p;
        } else {
            fin.sig = p;
            p.ant = fin;
        }
        fin = p;
    }

    public void agregarFinal(Asignatura a) {
        agregarFinal(crearNodo(a));
    }

    public Nodo buscarPorCodigo(String cod) {
        Nodo x = cab;
        while (null != x && !x.asign.getCodigo().equalsIgnoreCase(cod)) {
            x = x.sig;
        }
        return x;
    }

    public Asignatura buscarAsignaturaPorCod(String cod) {
        return buscarPorCodigo(cod).asign;
    }

    public void agregarDespuesDe(Nodo nuevo, Asignatura vieja) throws Exception {
        Nodo x = buscarPorCodigo(vieja.getCodigo());
        if (null != x) {
            Nodo z = x.sig;
            x.sig = nuevo;
            nuevo.sig = z;
            z.ant = nuevo;
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
                cab = cab.sig;
                cab.ant = null;
            }
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
                fin = fin.ant;
                fin.sig = null;
            }
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
                Nodo x = y.ant;
                Nodo z = y.sig;
                y.ant = null;
                y.sig = null;
                y = null;
                x.sig = z;
                z.ant = y;
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean eliminar(Asignatura a) {
        return eliminar(buscarPorCodigo(a.getCodigo()));
    }

    public Nodo getCab() {
        return cab;
    }

    @Override
    public String toString() {
        String m = "\n";
        if (vacio()) {
            m += "vacia";
        } else {
            Nodo x = cab;
            while (null != x) {
                m += x + "\n";
                x = x.sig;
            }
        }
        return m;
    }

}
