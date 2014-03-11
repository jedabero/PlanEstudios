package matr.estudiante;

import java.io.Serializable;
import java.util.Objects;

/**
 * Diseño minimo basico de un estudiante.
 *
 * @author Jedabero
 */
public final class Estudiante implements Serializable {

    private final String nombre;
    private final String id;
    private final String programa;

    //private Plan plan;
    /**
     * Constructor basico de un estudiante, a partir de su nombre, codigo y
     * programa.
     *
     * @param nombre
     * @param id
     * @param programa
     */
    public Estudiante(String nombre, String id, String programa) {
        this.nombre = nombre;
        this.id = id;
        this.programa = programa;
    }

    public Estudiante(String... a) throws NoSePuedeCrearEstudianteException {
        if (a.length > 2) {
            nombre = a[0];
            id = a[1];
            programa = a[2];
        } else {
            throw new NoSePuedeCrearEstudianteException();
        }
    }

    public Estudiante() {
        this("N.N.", "0", "N/A");
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getPrograma() {
        return programa;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.programa);
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
        final Estudiante other = (Estudiante) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.programa, other.programa);
    }

    @Override
    public String toString() {
        return nombre + ":" + id + ":" + programa;
    }

}
