
package matr;

/**
 *
 * @author Jedabero
 * @param <T>
 */
public class Nodo<T> {
    
    private T item;
    private Nodo ant;
    private Nodo sig;
    
    public Nodo(){}
    
    public Nodo(T item){
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Nodo getAnt() {
        return ant;
    }

    public void setAnt(Nodo ant) {
        this.ant = ant;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
    
    @Override
    public String toString() {
        return item.toString();
    }
    
}
