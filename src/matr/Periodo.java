package matr;

/**
 *
 * @author jedabero
 */
public class Periodo {

    private Lista lista;

    private int totalCreditos;
    private int totalAsignaturas;
    private double promedio;

    public Periodo() {
    }

    public Periodo(Lista lista) {
        this.lista = lista;
        init();
    }

    private void init() {
        int tc = 0;
        int ta = 0;
        double p = 0d;
        Lista.Nodo x = lista.getCab();
        while (null != x) {
            tc += x.asign.getCreditos();
            p += x.asign.getCalificacion();
            ta++;
            x = x.sig;
        }
        p /= ta;

        totalCreditos = tc;
        totalAsignaturas = ta;
        promedio = p;

    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
        init();
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
        return "["+lista+"]";
    }

}
