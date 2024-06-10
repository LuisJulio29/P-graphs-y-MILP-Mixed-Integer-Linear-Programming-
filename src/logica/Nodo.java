package logica;

public class Nodo<T> {
    private T elemento;
    private Nodo<T> izquierda;
    private Nodo<T> derecha;

    public Nodo(T elemento) {
        this.elemento = elemento;
        this.izquierda = null;
        this.derecha = null;
    }

    public T getElemento() {
        return elemento;
    }

    public Nodo<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }

    public int nodosCompletos(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int count = 0;
        if (nodo.getIzquierda() != null && nodo.getDerecha() != null) count++;
        count += nodosCompletos(nodo.getIzquierda());
        count += nodosCompletos(nodo.getDerecha());
        return count;
    }
}



