package automata;

/**
 * n-Dimensional array
 * @author gerardomt
 * @version 0.1
 */
public class MultiArray<E>{
    private int[] dimensions;
    private Object[] array;

    /**
     * Class Constructor
     * @param dimensions Arrays with the n-dimencions of the array
     */
    public MultiArray(int[] dimensions){
        this.dimensions = dimensions;
        int n = 1;
        for (int s:dimensions){
            if (s<=0) throw new IndexOutOfBoundsException();
            n *= s;
        }
        array = new Object[n]; 
    }

    /**
     * Return the element in the specified position.
     * @param index Array the index of the element.
     * @return The element in the specified position.
     * @throw IndexOutOfBoundsException If the index is out of range
     */
    public E getElement(int[] index){
        checkIndex(index);
        
        int i = getIndex(index);
        return (E)array[i];
    }

    /**
     * Set the element in the specified position of the n-dimensional array.
     * @param index Array with the index of the position where the element will be store
     * @param elem
     */
    public void getElement(int[] index, E elem){
        checkIndex(index);
        
        int i = getIndex(index);
        array[i]=elem;
    }

    /**
     * Devuelve la posicion <code>i</code> del elemento en el arreglo
     * de una dimension.
     * @param indices arreglo con los indices donde esta el elemento
     *     en el arreglo multidimensional.  se debe cumplir que cada
     *     índice es positivo y menor que el tamaño de la dimensión
     *     correspondiente.
     * @return la posicion del elemento en el arreglo de una
     *     dimension.
     */
    public int getIndex(int[] index){
        checkIndex(index);

        int result = index[0];
        for (int i=1; i<dimensions.length; i++){
            result *= dimensions[i];
            result += index[i];
        }
        return result;
    }

    public int getDimension(int n){
        if (n<0 || n>=dimensions.length) throw new IllegalArgumentException();
        return dimensions[n]

    /**
     * Revisa que los índices pasados a los métodos sean validos
     * @param indices Índices que se provarán
     */
    private void checkIndex(int[] index){        
        if (index.length != dimensions.length)
            throw new IndexOutOfBoundsException("Número de índices inválido");
        
        for (int i=0; i<index.length; i++)
            if (index[i]<0 || index[i]>=dimension[i])
                throw new IndexOutOfBoundsException("Índice inválido");
    }
}
