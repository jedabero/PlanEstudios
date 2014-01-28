
package matr;

/**
 *
 * @author Jedabero
 */
public class Estudiante {
    
    private String nombre;
    private String id;
    private String programa;
    
    //private Plan plan;

    public Estudiante(String nombre, String id, String programa) {
        this.nombre = nombre;
        this.id = id;
        this.programa = programa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Override
    public String toString() {
        return nombre+""+id+""+programa;
    }
    
}
