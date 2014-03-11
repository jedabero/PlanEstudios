package matr.estudiante;

/**
 *
 * @author Jedabero
 */
public class NoSePuedeCrearEstudianteException extends Exception {

    /**
     * Creates a new instance of <code>NoSePuedeCrearEstudianteException</code>
     * without detail message.
     */
    public NoSePuedeCrearEstudianteException() {
    }

    /**
     * Constructs an instance of <code>NoSePuedeCrearEstudianteException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSePuedeCrearEstudianteException(String msg) {
        super(msg);
    }
}
