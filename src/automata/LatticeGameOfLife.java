package automata;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * Manage the specific rules of the GameOfLife lattice
 * @author gerardomt
 * @version 0.1
 */
public class LatticeGameOfLife{
    private int cellsHeight, cellsWidth;
    private MultiArray<Cell> lattice;
    private Pane root;
    private int maxState;
    private double cellSize;

    /**
     * Class constructor
     * @param size Size of the lattice. The lattice is a square
     * lattice.
     * @param root Pane to add the lattice
     */
    public LatticeGameOfLife(Pane root, int cellsHeight){
        double width = Screen.getPrimary().getVisualBounds().getWidth()-20.0;
        double height = Screen.getPrimary().getVisualBounds().getHeight()-40;

        cellSize = height/cellsHeight;
        this.cellsHeight = cellsHeight;
        this.cellsWidth = (int)(width/cellSize);
        lattice = new MultiArray<Cell>(new int[] {cellsWidth,cellsHeight});
        this.root = root;
        this.maxState = 2;

        initLattice();
        setCellsNeighborhood();
        update();
    }

    /**
     * Perform the rules and changes to execute in each time step
     */
    public void change(){
        rules();
        update();
    }

    /**
     * Inicialize the GameOfLife's lattice.
     */ 
    protected void initLattice(){
        Cell cell;
        for (int i=0; i<getCellsWidth(); i++){
            for (int j=0; j<getCellsHeight(); j++){
                cell = new Cell(0, 2, cellSize, i, j);
                lattice.setElement(new int[] {i,j}, cell);
                cell.setState((int)(Math.random()*(maxState)));
                root.getChildren().add(getCell(i,j).getFigure());
            }
        }
    }

    /**
     * Set the neighborhood of the cells
     */
    private void setCellsNeighborhood(){
        for (int i=0; i<getCellsWidth();i++){
            for (int j=0; j<getCellsHeight();j++)
                lattice.getElement(new int[] {i,j}).setNeighborhood(lattice);
        }
    }

    /**
     * Update the lattice
     */
    protected void update(){
        Cell cell;
        for (int i=0; i<getCellsWidth();i++){
            for (int j=0;j<getCellsHeight(); j++){
                cell = getCell(i,j);
                cell.update();
                if (cell.getState()==1)
                    cell.getFigure().setFill(Color.WHITE);
                else
                    cell.getFigure().setFill(Color.BLACK);
            }
        }
    }

    /**
     * Return a specific cell on the lattice
     * @param i Cooddinate on x-axis
     * @param j Coordinate on y-axis
     * @return Specific cell
     */
    public Cell getCell(int i, int j){
        return lattice.getElement(new int[] {i,j});
    }

    /**
     * Devuelve el tamaño de la lattice
     * @return int
     */
    public int getCellsHeight(){
        return cellsHeight;
    }

    public int getCellsWidth(){
        return cellsWidth;
    }

    //Rules

    /**
     * GameOfLife's Rules
     */
    private void rules(){
        Cell cell;
        int livingNeighbor;
        for (int i=0; i<getCellsWidth(); i++){
            for (int j=0; j<getCellsHeight(); j++){
                cell = getCell(i,j);
                livingNeighbor = 0;
                for (Cell c: cell.getNeighborhood()){
                    if (c.getState()==1)
                        livingNeighbor++;
                }
                if (livingNeighbor<2)
                    cell.setTempState(0);
                else if (livingNeighbor>3)
                    cell.setTempState(0);
                else if (livingNeighbor==3 && cell.getState()==0)
                    cell.setTempState(1);
            }
        }
    }
}
