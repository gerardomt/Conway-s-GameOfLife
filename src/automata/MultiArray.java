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
     * Set the element in the specified position of the n-dimensional
     * array.
     * @param index Array with the index of the position where the
     * element will be store
     * @param elem Element that will be stored
     * @throw IndexOutOfBoundsException if the index is out of range
     */
    public void setElement(int[] index, E elem){
        checkIndex(index);

        int i = getIndex(index);
        array[i]=elem;
    }

    /**
     * Return the position <code>i</code> of the element on the one
     * dimension array
     * @param index Array with the index of the position in the
     *     n-dimension array. Each index must be <code>0 &le; i_n &le;
     *     dimension(n)</code>
     * @return Position of the element on the 1-dimension array
     */
    private int getIndex(int[] index){
        checkIndex(index);

        int result = index[0];
        for (int i=1; i<dimensions.length; i++){
            result *= dimensions[i];
            result += index[i];
        }
        return result;
    }

    /**
     * Return the n-dimension of the multiarray
     * @param n Number of the dimension. It must be <code>0 &le; n
     * &le; dimensions</code> where dimensions:=Number of dimensions
     * in the array
     * @return Value of the n-dimension
     */
    public int getDimension(int n){ if (n<0 ||
    n>=dimensions.length) throw new IllegalArgumentException(); return
    dimensions[n]; }

    /**
     * Check that the index is valid
     * @param index Each <code>int</code> in index must be <code>0 &le; i_n &le; dimension</code>
     * @throw IndexOutOfBoundsException
     */
    private void checkIndex(int[] index){
        if (index.length != dimensions.length)
            throw new IndexOutOfBoundsException("Número de índices inválido");

        for (int i=0; i<index.length; i++)
            if (index[i]<0 || index[i]>=dimensions[i])
                throw new IndexOutOfBoundsException("Índice inválido");
    }
}
