package matr;

import java.io.Serializable;

/**
 *
 * @author Jedabero
 */
public final class Estudiante implements Serializable {

    private final String nombre;
    private final String id;
    private final String programa;

    //private Plan plan;

    /** Constructor basico de un estudiante, a partir de su nombre, codigo y programa.
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

    public Estudiante(String... a) throws Exception {
        if (a.length > 2) {
            nombre = a[0];
            id = a[1];
            programa = a[2];
        } else {
            throw new Exception("Array incompatible");
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
    public String toString() {
        return nombre + ":" + id + ":" + programa;
    }

}
