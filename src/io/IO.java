package io;

/**
 *
 * @author Jedabero
 */
public enum IO {

    EXT(".plan"),
    EXTO(".plano"),
    OS(System.getProperty("os.name")),
    USER(System.getProperty("user.home")),
    LS(System.getProperty("line.separator")),
    FS(System.getProperty("file.separator"));

    public String p;

    private IO(String p) {
        this.p = p;
    }

}
