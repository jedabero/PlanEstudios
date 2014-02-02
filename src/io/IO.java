package io;

/**
 *
 * @author Jedabero
 */
public enum IO {

    OS(System.getProperty("os.name")),
    EXT(".plan"),
    EXTO(".plano"),
    USER(System.getProperty("user.home")),
    FS(System.getProperty("file.separator")),
    LS(System.getProperty("line.separator"));

    public String p;

    private IO(String p) {
        this.p = p;
    }

}
