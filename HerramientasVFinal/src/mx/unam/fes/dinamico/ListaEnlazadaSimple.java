package mx.unam.fes.dinamico;
import mx.unam.fes.excepciones.IndiceFueraException;

public class ListaEnlazadaSimple<E> {
	private Nodo<E> cabeza, cola;
	private int longitud = 0;

	public ListaEnlazadaSimple() {
		cabeza = cola = null;
	}

	public boolean esVacia() {
		return cabeza == null;
	}

	public int getLongitud() {
		return longitud;
	}

	public void agregarCabeza(E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		if (cola == null) {
			cola = cabeza;
		}
		longitud++;
	}

	public void agregarCola(E dato) {
		if (!esVacia()) {
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola.getSiguiente();
		} else {
			cola = cabeza = new Nodo<E>(dato);
		}
		longitud++;
	}

	public E eliminarDeCabeza() {
		E dato = null;
		if (!esVacia()) {
			dato = cabeza.getDato();
			if (cabeza == cola) {
				cabeza = cola = null;
			} else {
				cabeza = cabeza.getSiguiente();
			}
			longitud--;
		}
		return dato;
	}

	public E eliminarDeCola() {
		E dato = null;
		if (!esVacia()) {
			dato = cola.getDato();
			if (cabeza == cola) {
				cabeza = cola = null;
			} else {
				Nodo<E> temp;
				for (temp = cabeza; temp.getSiguiente() != cola; temp = temp.getSiguiente())
					;
				cola = temp;
				cola.setSiguiente(null);
			}
			longitud--;
		}
		return dato;
	}

	// Devuelve el dato en la posicion "indice" (empieza en 0).
	// Si el indice no existe lanza IndicieFueraExeption.
	public E obtenerNodo(int indice) throws IndiceFueraException {
		Nodo<E> temp = cabeza;
		for (int contador = 0; contador < indice && temp != null; contador++, temp = temp.getSiguiente())
			;
		if (indice < 0 || temp == null) {
			throw new IndiceFueraException("Indice fuera de la lista: " + indice);
		}
		return temp.getDato();
	}

	// Igual que en clase: cambia el dato que hay en esa posicion
	public void insertarEnIndice(E dato, int indice) throws IndiceFueraException {
		Nodo<E> temp = cabeza;
		for (int contador = 0; contador < indice && temp != null; contador++, temp = temp.getSiguiente())
			;
		if (indice < 0 || temp == null) {
			throw new IndiceFueraException("Indice fuera de la lista: " + indice);
		}
		temp.setDato(dato);
	}

	public void imprimir() {
		for (Nodo<E> temp = cabeza; temp != null; temp = temp.getSiguiente()) {
			System.out.println(temp.getDato() + " ");
		}
	}

	public void borrar(E dato) {
		if (!esVacia()) {
			if (cabeza == cola && dato.equals(cabeza.getDato())) {
				cabeza = cola = null;
				longitud--;
			} else if (dato.equals(cabeza.getDato())) {
				cabeza = cabeza.getSiguiente();
				longitud--;
			} else {
				Nodo<E> predesor, tmp;
				for (predesor = cabeza, tmp = cabeza.getSiguiente(); tmp != null
						&& !tmp.getDato().equals(dato); predesor = predesor.getSiguiente(), tmp = tmp.getSiguiente())
					;
				if (tmp != null) {
					predesor.setSiguiente(tmp.getSiguiente());
					if (tmp == cola) {
						cola = predesor;
					}
					longitud--;
				}
			}
		}
	}

	public void borrarEnIndice(int indice) throws IndiceFueraException{
		if (indice < 0 || indice >= longitud) {
			throw new IndiceFueraException("Indice fuera de la lista: " + indice);
		}
		if (cabeza == cola) {
			cabeza = cola = null;
		} else if (indice == 0) {
			cabeza = cabeza.getSiguiente();
		} else {
			Nodo<E> predesor = cabeza;
			for (int contador = 1; contador < indice; contador++) {
				predesor = predesor.getSiguiente();
			}
			Nodo<E> tmp = predesor.getSiguiente();
			predesor.setSiguiente(tmp.getSiguiente());
			if (tmp == cola) {
				cola = predesor;
			}
		}
		longitud--;
	}
}
