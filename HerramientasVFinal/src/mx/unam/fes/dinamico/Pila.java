package mx.unam.fes.dinamico;

import java.io.Serializable;

/**
 * Clase genérica que representa una Pila (Stack) dinámica basada en Nodos.
 * Implementa el principio LIFO (Last-In, First-Out).
 * 
 * @param <T> El tipo de dato que almacenará la pila.
 */
public class Pila<T> implements Serializable {

    // Puntero que indica el último elemento insertado (la cima de la pila)
    private Nodo<T> tope;
    
    // Contador opcional para saber cuántos elementos hay en la pila
    private int tamano;

    /**
     * Constructor que inicializa una pila vacía.
     */
    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    /**
     * Operación "Pila_vacía".
     * Determina si la estructura no tiene elementos.
     * 
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.tope == null;
    }

    /**
     * Operación "Pone" o "Apilar" (Push).
     * Agrega un nuevo elemento en el TOPE de la pila.
     * 
     * @param dato El valor a insertar.
     */
    public void apilar(T dato) {
        // Creamos el nuevo nodo con el dato a insertar
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        // El 'siguiente' del nuevo nodo será el que actualmente es el tope
        nuevoNodo.setSiguiente(this.tope);

        // Ahora el tope de la pila pasa a ser el nuevo nodo
        this.tope = nuevoNodo;
        
        this.tamano++;
    }

    /**
     * Operación "Quita" o "Desapilar" (Pop).
     * Elimina y devuelve el elemento que se encuentra en el TOPE de la pila.
     * 
     * @return El dato del elemento eliminado, o null si hay subdesbordamiento.
     */
    public T desapilar() {
        if (estaVacia()) {
            System.err.println("Subdesbordamiento - Pila vacía");
            return null; // O se puede lanzar una excepción
        }

        // Respaldamos el dato que está en el tope para poder devolverlo
        T dato = this.tope.getDato();

        // Movemos el puntero tope al elemento que está debajo de él
        this.tope = this.tope.getSiguiente();

        this.tamano--;
        return dato;
    }

    /**
     * Permite observar el elemento en el TOPE de la pila sin sacarlo de ella.
     * Muy útil para la evaluación de expresiones (postfijas/prefijas) que menciona el libro.
     * 
     * @return El dato en la cima de la pila, o null si está vacía.
     */
    public T verTope() {
        if (estaVacia()) {
            return null;
        }
        return this.tope.getDato();
    }

    /**
     * Devuelve la cantidad de elementos actuales en la pila.
     * 
     * @return Entero con el tamaño de la pila.
     */
    public int getTamano() {
        return this.tamano;
    }

    /**
     * Método auxiliar para vaciar la pila completamente.
     */
    public void vaciar() {
        this.tope = null;
        this.tamano = 0;
    }
}