package mx.unam.fes.dinamico;

import java.io.Serializable;

/**
 * Clase genérica que representa una Cola dinámica basada en Nodos.
 * @param <T> 
 */
public class Cola<T> implements Serializable {
    private Nodo<T> frente;     // próximo en salir
    private Nodo<T> finalCola;  // último en entrar
    private int tamano;         // contador 

   
    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return this.frente == null;
    }

    public void meterAcola(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (estaVacia()) {
            
            this.frente = nuevoNodo;
            this.finalCola = nuevoNodo;
        } else {
          
            this.finalCola.setSiguiente(nuevoNodo);
            this.finalCola = nuevoNodo;
        }
        this.tamano++;
    }

   
    public T sacarDeCola() {
        if (estaVacia()) {
            System.err.println("Subdesbordamiento - Cola vacía");
            return null; 
        }

       T dato = this.frente.getDato();

        this.frente = this.frente.getSiguiente();

        if (this.frente == null) {
            this.finalCola = null;
        }

        this.tamano--;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return this.frente.getDato();
    }

    public int getTamano() {
        return this.tamano;
    }


    public void vaciar() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }
}