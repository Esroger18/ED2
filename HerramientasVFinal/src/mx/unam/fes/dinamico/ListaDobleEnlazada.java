package mx.unam.fes.dinamico;

public class ListaDobleEnlazada<E> {
    private NodoDoble<E> cabeza;
    private NodoDoble<E> cola;
    private int longitud;

    
    public ListaDobleEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }

    /**
     * Verifica si la lista se encuentra vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Obtiene el número total de elementos presentes en la lista.
     * @return La cantidad de nodos (longitud) de la lista.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Inserta un elemento al inicio (cabeza) de la lista.
     * @param dato El elemento a agregar.
     */
    public void agregarCabeza(E dato) {
        NodoDoble<E> nuevo = new NodoDoble<>(dato);
        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        longitud++;
    }

    /**
     * Inserta un elemento al final (cola) de la lista.
     * (Equivalente al método insertar estándar).
     * @param dato El elemento a agregar.
     */
    public void agregarCola(E dato) {
        NodoDoble<E> nuevo = new NodoDoble<>(dato);
        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        longitud++;
    }

    /**
     * Elimina el elemento al inicio (cabeza) de la lista.
     * @return El dato eliminado, o null si la lista está vacía.
     */
    public E eliminarDeCabeza() {
        if (esVacia()) return null;
        
        E dato = cabeza.dato;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null; // Romper el enlace hacia atrás
        }
        longitud--;
        return dato;
    }

    /**
     * Elimina el elemento al final (cola) de la lista.
     * En una lista doble, esto no requiere un bucle gracias al apuntador anterior.
     * @return El dato eliminado, o null si la lista está vacía.
     */
    public E eliminarDeCola() {
        if (esVacia()) return null;
        
        E dato = cola.dato;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null; // Romper el enlace hacia adelante
        }
        longitud--;
        return dato;
    }

    /**
     * Recupera el elemento de un nodo dada su posición o índice (iniciando en 0).
     * @param indice La posición del elemento a recuperar.
     * @return El dato en la posición indicada, o null si el índice es inválido.
     */
    public E obtenerNodo(int indice) {
        if (indice < 0 || indice >= longitud) return null;
        
        NodoDoble<E> temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }
        return temp.dato;
    }

    /**
     * Inserta un elemento en una posición específica de la lista.
     * @param dato El elemento a insertar.
     * @param indice La posición deseada (0 a longitud).
     * @return true si se insertó con éxito, false si el índice está fuera de rango.
     */
    public boolean insertarEnIndice(E dato, int indice) {
        if (indice < 0 || indice > longitud) return false;
        
        if (indice == 0) {
            agregarCabeza(dato);
            return true;
        }
        if (indice == longitud) {
            agregarCola(dato);
            return true;
        }

        NodoDoble<E> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        NodoDoble<E> nuevo = new NodoDoble<>(dato);
        NodoDoble<E> anterior = actual.anterior;

        // Conectar el nuevo nodo en medio
        anterior.siguiente = nuevo;
        nuevo.anterior = anterior;
        nuevo.siguiente = actual;
        actual.anterior = nuevo;

        longitud++;
        return true;
    }

    /**
     * Imprime en consola todos los elementos de la lista desde la cabeza hasta la cola.
     */
    public void imprimir() {
        NodoDoble<E> temp = cabeza;
        while (temp != null) {
            System.out.println(temp.dato + " ");
            temp = temp.siguiente;
        }
    }

    /**
     * Elimina el primer nodo que coincida con el dato buscado, reasignando enlaces dobles.
     * @param dato El elemento a eliminar.
     * @return true si se encontró y eliminó, false en caso contrario.
     */
    public boolean borrar(E dato) {
        if (esVacia()) return false;

        NodoDoble<E> actual = cabeza;
        while (actual != null && !actual.dato.equals(dato)) {
            actual = actual.siguiente;
        }

        if (actual == null) return false;

        // Caso 1: Único elemento
        if (cabeza == cola && actual == cabeza) {
            cabeza = cola = null;
        } 
        // Caso 2: Es la cabeza
        else if (actual == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        } 
        // Caso 3: Es la cola
        else if (actual == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
        } 
        // Caso 4: Está en medio
        else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }

        longitud--;
        return true;
    }

    /**
     * Elimina un elemento en una posición específica de la lista.
     * @param indice El índice del elemento a borrar.
     */
    public void borrarEnIndice(int indice) {
        if (indice < 0 || indice >= longitud || esVacia()) return;

        if (indice == 0) {
            eliminarDeCabeza();
            return;
        }
        if (indice == longitud - 1) {
            eliminarDeCola();
            return;
        }

        NodoDoble<E> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        actual.anterior.siguiente = actual.siguiente;
        actual.siguiente.anterior = actual.anterior;
        longitud--;
    }
    
    /**
     * Localiza la posición de un elemento en la lista.
     * @param dato El elemento a buscar.
     * @return El índice del elemento, o -1 si no se encuentra.
     */
    public int localiza(E dato) {
        NodoDoble<E> actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return indice;
            }
            actual = actual.siguiente;
            indice++;
        }
        return -1;
    }

    /**
     * Limpia la lista eliminando todas sus referencias.
     */
    public void limpiar() {
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }
}