package matr;

/**
 *
 * @author jedabero
 */
public class Asignatura {

    private boolean puedeMatr;
    
    private String nombre;
    private String codigo;
    private int creditos;
    private double nota;
    private boolean aprobada;

    private boolean matriculada;
    private String periodo;
    
    private Asignatura requisito;
    private Asignatura corequisito;

    /**
     * Crea una asignatura con todos los parametros necesarios.
     *
     * @param nombre the value of nombre
     * @param codigo the value of codigo
     * @param creditos the value of creditos
     * @param periodo the value of periodo
     * @param requisito the value of requisito
     * @param corequisito the value of corequisito
     * @param nota the value of nota
     */
    public Asignatura(String nombre, String codigo, int creditos, String periodo, Asignatura requisito, Asignatura corequisito, double nota) {
        this.periodo = periodo;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.nota = nota;
        this.requisito = requisito;
        this.corequisito = corequisito;

        aprobada = nota >= 3.0;
        puedeMatr = aprobada ? false : ((null != requisito) ? requisito.aprobada : true);

    }

    public Asignatura(boolean matriculada, String periodo,
            String nombre, String cod, int cred,
            Asignatura req, Asignatura coreq) {
        this(nombre, cod, cred, periodo, req, coreq, 0d);
        this.matriculada = matriculada;
    }

    public Asignatura(String nombre, String cod, int cred) {
        this(nombre, cod, cred, "N/A", null, null, 0d);
    }

    public Asignatura() {
        this("N/A", "N/A", 0, "N/A", null, null, 0d);
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public void updateAprobada() {
        this.aprobada = nota >= 3.0;
    }

    public boolean isMatriculada() {
        return matriculada;
    }

    public void setMatriculada(boolean matriculada) {
        this.matriculada = puedeMatr?matriculada:false;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
        updateAprobada();
    }

    public Asignatura getRequisito() {
        return requisito;
    }

    public void setRequisito(Asignatura requisito) {
        this.requisito = requisito;
    }

    public Asignatura getCorequisito() {
        return corequisito;
    }

    public void setCorequisito(Asignatura corequisito) {
        this.corequisito = corequisito;
    }

    public void updatePuedeMatr() {
        puedeMatr = aprobada ? false : ((null != requisito) ? requisito.aprobada : true);
    }
    
    public boolean puedeMatr() {
        return puedeMatr;
    }

    public void update() {
        updateAprobada();
        updatePuedeMatr();
    }
    
    @Override
    public String toString() {
        return "{"+nombre + ":" + codigo + ":" + creditos + ":" + periodo
                + ":" + ((requisito != null) ? requisito.codigo : "No")
                + ":" + ((corequisito != null) ? corequisito.codigo : "No")
                + ":" + nota + ":" + matriculada +  "}";
    }

}
