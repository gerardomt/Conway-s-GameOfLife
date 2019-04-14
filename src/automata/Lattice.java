package automata;

import javafx.scene.layout.Pane;
import java.util.LinkedList;

public abstract class Lattice{
    // Size in pixels of the lattice
    private static final double LATTICE_SIZE  = 600.0;
    
    private multiArray<Cell> lattice;
    private Pane root;
    private LinkedList<Integer> data;
    protected int maxState;

    /**
     * Constructor de la clase
     * @param tamanho Tamaño de la arista de la malla
     * @param estadoMax Estado máximo que pueden alcanzar las celdas
     * @param root Donde se agregará la malla
     */
    public Lattice(int size, int maxState, Pane root){
        lattice = new MultiArray<Cell>(new int[] {size, size});
        data = new LinkedList<Integer>();
        this.root = root;
        this.maxState = maxState;
        initLattice();
        setCellsNeighborhood();
        initState();
        update();
    }

    /**
     * Establece el color de las celdas
     */
    protected abstract void setColor();

    /**
     * Método que se ejecutará a cada paso
     */
    public abstract void change();

    /**
     * Cuenta el número de celdas con valor umbral
     * @return int
     */
    protected abstract int getCritic();

    /**
     * Inicializa las celdas en la matriz <code>lattice</code>
     */ 
    protected void initLattice(){
        for (int i=0; i<getSize(); i++){
            for (int j=0; j<getSize(); j++){
                lattice.setElement(new int[] {i,j}, new Cell(0, maxState,
                                        SIZE_LATTICE/getSize(), i, j);
                root.getChildren().add(getCell(i,j).getFigure());
            }
        }
    }

    /**
     * Provee un estado inicial random para la lattice
     */
    protected void initState(){
        for (int i=0; i<getSize(); i++){
            for (int j=0; j<getSize(); j++){
                getCell(i,j).setState((int)(Math.random()*maxState));
            }
        }
    }

    /**
     * Establece las vecindades de las celdas
     */
    private void setCellsNeighborhood(){
        for (int i=0; i<lattice.length;i++){
            for (int j=0; j<lattice.length;j++)
                lattice.getElement(new int[] {i,j}).setNeighborhood(lattice);
        }
    }

    /**
     * Actualiza el estado de la lattice
     */
    protected void update(){
        for (int i=0; i<lattice.length;i++){
            for (int j=0;j<lattice[i].length; j++){
                lattice.getElement(new int[] {i,j}).update();
            }
        }
    }

    /**
     * Devuelve una celda específica en la lattice
     * @param i coordenada x
     * @param j coordenada j
     * @return Celda
     */
    public Cell getCell(int i, int j){
        return lattice.getElement(new int[] {i,j});
    }

    /**
     * Devuelve el tamaño de la lattice
     * @return int
     */
    public int getSize(){
        return lattice.getDimension(0);
    }

    /**
     * Getter de <code>data</code>
     * @return LinkedList<Integer> 
     */
    public LinkedList<Integer> getData(){
        return data;
    }

    /**
     * Getter de lattice
     * @return Celda[][]
     */
    public MultiArray<Cell> getLattice(){
        return lattice;
    }
}
