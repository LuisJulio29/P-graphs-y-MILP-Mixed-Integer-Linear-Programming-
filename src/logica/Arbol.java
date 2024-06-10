package logica;

import java.util.ArrayList;
import java.util.List;

public class Arbol<T> {
    private Nodo<T> raiz;
    private List<T> elementosInsertados;

    public Arbol() {
        this.raiz = null;
        this.elementosInsertados = new ArrayList<>();
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void insertar(T elemento) {
        if (raiz == null) {
            raiz = new Nodo<>(elemento);
        } else {
            insertar(raiz, elemento);
        }
        elementosInsertados.add(elemento);
    }

    private void insertar(Nodo<T> nodo, T elemento) {
        if (nodo.getIzquierda() == null) {
            nodo.setIzquierda(new Nodo<>(elemento));
        } else if (nodo.getDerecha() == null) {
            nodo.setDerecha(new Nodo<>(elemento));
        } else {
            // This implementation just inserts in the left subtree for simplicity
            insertar(nodo.getIzquierda(), elemento);
        }
    }

    public List<T> getElementosInsertados() {
        return elementosInsertados;
    }
}

