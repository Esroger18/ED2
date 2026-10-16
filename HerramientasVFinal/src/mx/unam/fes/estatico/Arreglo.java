package mx.unam.fes.estatico;
import mx.unam.fes.excepciones.IndiceFueraException;

public class Arreglo<E> {
	private int indice;
	private final Object[] arreglo;
	public Arreglo(int longitud) {
		arreglo=new Object[longitud];
	}
	
	/**
	 * Este metodo inserta un elemento a el arreglo si esta dentro del rango del arreglo. Si no esta dentro del rango del
	 * arreglo genera una excepcion fuera de rango del objeto.
	 * @param elemento
	 * @throws IndicieFueraExeption
	 */
	public void insertar(E elemento) throws IndiceFueraException {
		if(indice<arreglo.length) {
			arreglo[indice]=elemento;	
			indice++;
		}else {
			 throw new IndiceFueraException("Indice fuera del arreglo");
		}
	}

	/**
	 * Este metodo localiza un elemento recorriendo todo el arreglo. Se ocupa equals ya que al usar una clase generica 
	 * no se pueden igualar de forma primitiva los elementos de la lista, entonces se ocupa equals para poder igualar los objetos
	 * que guarda el arreglo
	 * @param elemento
	 * @return Retorna el elemento a localizar y si no lo encuentra returna -1 indicando que el elemento no esta en el arreglo
	 */
	public int localiza(E elemento) {
		for(int i=0;i<indice;i++) {
			if(arreglo[i].equals(elemento)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Este metodo recupera un elemento de el arreglo.Ssi le posicion de el elemento es menor o igual a 0 y la posicion
	 * esta dentro del indice del arreglo retorna el elemento a recuperar. Suprime advertencias del codigo 
	 * @param posicion
	 * @returnSi el elemento a rrecuperar esta dentro del indice del arreglo lo recupera y este no esta o no existe manda una
	 * excepcion fuera de indice 
	 * @throws IndicieFueraExeption
	 */
	@SuppressWarnings("unchecked")
	public E recupera(int posicion) throws IndiceFueraException {
		if(posicion>=0 && posicion<indice) {
			return (E) arreglo[posicion];
		}
		throw new IndiceFueraException("Indice fuera del arreglo");
	}

	/**
	 * Suprime un elemento seleccionando su posicion en el arreglo conviertiendo el lugar de su indice en nulo 
	 * y regresando a un espacio antes de ese indice ya que no tiene nungun elemento. De nuevo si el elemento que quieres 
	 * suprimir esta fuera del rango del elemento manda una excepcion indice fuera de rango  
	 * @param posicion
	 * @throws IndicieFueraExeption
	 */
	public void suprime(int posicion) throws IndiceFueraException{
		if(posicion>=0 && posicion<indice) {
			for(int i=posicion;i<indice-1;i++) {
				arreglo[i]=arreglo[i+1];
			}
			arreglo[indice-1]=null;
			indice--;
		}else {
			throw new IndiceFueraException("Indice fuera del arreglo");
		}
	}
	
	/**
	 * Este metodo imrpime el siguente elemento del indice seleccionado osea aunmenta en uno el indice y el elemento que tiene 
	 * guardado si este elemento siguente  esta fuera del rango del indice genera una excepcion indice fuera de rango 
	 * @param posicion
	 * @return Returna el valor siguente del indice seleccionado 
	 * @throws IndicieFueraExeption
	 */
	@SuppressWarnings("unchecked")
	public E siguiente(int posicion) throws IndiceFueraException {
		if(posicion>=0 && posicion<indice-1) {
			return (E) arreglo[posicion+1];
		}
		throw new IndiceFueraException("No existe un elemento siguiente");
	}
/**
 * Este metodo lo que returna el elemento anterior seleccionado en el arreglo 
 * @param posicion
 * @return Si el elemento seleccionado esta dentro de la longitud de el arreglo returna el elemento anterior del indice marcado 
 * @throws IndicieFueraExeption
 */
	@SuppressWarnings("unchecked")
	public E anterior(int posicion) throws IndiceFueraException {
		if(posicion>0 && posicion<indice) {
			return (E) arreglo[posicion-1];
		}
		throw new IndiceFueraException("No existe un elemento anterior");
	}

	public void limpiar() {
		for(int i=0;i<indice;i++) {
			arreglo[i]=null;
		}
		indice=0;
	}


	/**
	 * Este metodo encuentra el primer elemento de el arreglo buscando ssi el indice es meyor a 0
	 * @return Si el indice es meyor a 0 returna el elemento 
	 * @throws IndicieFueraExeption
	 */
	@SuppressWarnings("unchecked")
	public E primero() throws IndiceFueraException {
		if(indice>0) {
			return (E) arreglo[0];
		}
		throw new IndiceFueraException("El arreglo esta vacio");
	}

	/**
	 * Este metodo recorre todos los incides del arreglo imprimiendo los elementos que contiene uno por uno hasta llegar al final
	 * 
	 */
	public void imprimir() {
		for(int i=0;i<indice;i++) {
			System.out.print(arreglo[i]+",");
		}
		System.out.println();
	}

/**
 * Este metodo Asigna un elemento a un indice no ocupado o nulo del arreglo so la posicion es menor a cerp y la posicion es
 * menor que el indice de el arreglo inserta el elemento si no es asi genera una excepcion fuera del indice  
 * @param posicion
 * @param elemento
 * @throws IndicieFueraExeption
 */
	public void asignar(int posicion,E elemento) throws IndiceFueraException {
		if(posicion>=0 && posicion<indice) {
			arreglo[posicion]=elemento;
		}else {
			throw new IndiceFueraException("Indice fuera del arreglo");
		}
	}

	/**
	 * Este metodo comprueba si el arreglo contiene elementos en su interior o si esta vacio
	 *
	 * @return Si ele indice es menor que la longitud de el arreglo returna falso ya que nos dice que no esta lleno.
	 * si el indice no es menor que la longitud de el arreglo returna true indicando que el arreglo esta vacio
	 */
	public boolean vacio() {
		if (indice < arreglo.length) {
			return false; 
		}
		return true; 
	}
}