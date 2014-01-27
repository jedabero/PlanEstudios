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
        Nodo<Asignatura> x = lista.getCab();
        while (null != x) {
            tc += x.getItem().getCreditos();
            p += x.getItem().getNota();
            ta++;
            x = x.getSig();
        }
        p /= ta;
        totalCreditos = tc;
        totalAsignaturas = ta;
        promedio = Math.rint(p*100)/100;

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
        return "["+lista;
    }

}
