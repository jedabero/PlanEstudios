
package io;

/**
 *
 * @author Jedabero
 */
public enum IO {
    EXT(".pln"),
    USER(System.getProperty("user.home")),
    SEPARATOR(System.getProperty("file.separator"));
    
    public String p;
    private IO(String p) {
        this.p = p;
    }
    
}
