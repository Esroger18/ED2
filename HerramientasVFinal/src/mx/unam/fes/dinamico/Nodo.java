package mx.unam.fes.dinamico;

import java.io.Serializable;

/**
 * Clase genérica que representa un Nodo para estructuras de datos dinámicas 
 * (Listas, Pilas, Colas).
 * Implementa Serializable para que las estructuras construidas con él 
 * puedan guardarse en archivos o transmitirse por red si el otro proyecto lo requiere.
 * 
 * @param <T> El tipo de dato que almacenará el nodo.
 */
public class Nodo<T> implements Serializable {
    
    private T dato;             // valor que guarda el nodo
    private Nodo<T> siguiente;  // apuntador o enlace al siguiente nodo

    public Nodo() {
        this.dato = null;
        this.siguiente = null;
    }

    /**
     * Constructor que inicializa el nodo con un dato específico.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Constructor completo que inicializa el dato y el enlace al siguiente nodo.
     */
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    //getters y setters 

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

 
    @Override
    public String toString() {
        if (this.dato == null) {
            return "null";
        }
        return this.dato.toString();
    }
}