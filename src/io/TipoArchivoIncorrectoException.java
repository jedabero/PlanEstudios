
package io;

/**
 *
 * @author Jedabero
 */
public class TipoArchivoIncorrectoException extends Exception {

    /**
     * Creates a new instance of <code>TipoArchivoIncorrectoException</code>
     * without detail message.
     */
    public TipoArchivoIncorrectoException() {
    }

    /**
     * Constructs an instance of <code>TipoArchivoIncorrectoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TipoArchivoIncorrectoException(String msg) {
        super(msg);
    }
}
