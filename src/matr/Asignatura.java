package matr;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jedabero
 */
public class Asignatura implements Serializable {

    private boolean puedeMatr;

    private final String nombre;
    private final String codigo;
    private final int creditos;
    private double nota;
    private boolean aprobada;

    private boolean matriculada;
    private String periodo;
    private String nivel;

    private final Asignatura requisito;
    private final Asignatura corequisito;

    /**
     * Crea una asignatura con todos los parametros necesarios.
     *
     * @param nombre the value of nombre
     * @param codigo the value of codigo
     * @param creditos the value of creditos
     * @param periodo the value of periodo
     * @param nivel the value of periodo
     * @param requisito the value of requisito
     * @param corequisito the value of corequisito
     * @param nota the value of nota
     */
    public Asignatura(String nombre, String codigo, int creditos,
            String periodo, String nivel, Asignatura requisito, Asignatura corequisito, double nota) {
        this.periodo = periodo;
        this.nivel = nivel;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.nota = nota;
        this.requisito = requisito;
        this.corequisito = corequisito;

        aprobada = nota >= 3.0;
        puedeMatr = aprobada ? false : ((null != requisito) ? requisito.aprobada : true);

    }

    public Asignatura(boolean matriculada, String periodo, String nivel,
            String nombre, String cod, int cred,
            Asignatura req, Asignatura coreq) {
        this(nombre, cod, cred, periodo, nivel, req, coreq, 0d);
        this.matriculada = matriculada;
    }

    public Asignatura(String nombre, String cod, int cred) {
        this(nombre, cod, cred, "N/A", "N/A", null, null, 0d);
    }

    public Asignatura() {
        this("N/A", "N/A", 0, "N/A", "N/A", null, null, 0d);
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
        this.matriculada = (puedeMatr && matriculada);
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
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

    public Asignatura getCorequisito() {
        return corequisito;
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
    public int hashCode() {
        int hash = 31;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.codigo);
        hash = 53 * hash + this.creditos;
        hash = 53 * hash + Objects.hashCode(this.requisito);
        hash = 53 * hash + Objects.hashCode(this.corequisito);
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
        final Asignatura other = (Asignatura) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (this.creditos != other.creditos) {
            return false;
        }
        if (!Objects.equals(this.requisito, other.requisito)) {
            return false;
        }
        return Objects.equals(this.corequisito, other.corequisito);
    }

    @Override
    public String toString() {
        return "{" + nombre + ":" + codigo + ":" + creditos
                + ":" + periodo + ":" + nivel
                + ":" + ((requisito != null) ? requisito.codigo : "No")
                + ":" + ((corequisito != null) ? corequisito.codigo : "No")
                + ":" + nota + ":" + matriculada + "}";
    }

}
