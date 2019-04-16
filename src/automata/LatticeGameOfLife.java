package automata;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Manage the specific rules of the GameOfLife lattice
 * @author gerardomt
 * @version 0.1
 */
public class LatticeGameOfLife extends Lattice{

    private int maxState;

    /**
     * Class constructor
     * @param size Size of the lattice. The lattice is a square
     * lattice.
     * @param maxState Max State of the lattice. In this case the max
     * state is always 1.
     * @param root Pane to add the lattice
     */
    public LatticeGameOfLife(int size, int maxState, Pane root){
        super(size, maxState, root);
        this.maxState = maxState;
    }

    /**
     * Set the colors of the cells on the lattice
     */
    protected void setColor(){
        Cell cell;
        for (int i=0; i<getSize(); i++){
            for (int j=0; j<getSize(); j++){
                cell = getCell(i,j);
                if (cell.getState()==1)
                    cell.getFigure().setFill(Color.WHITE);
                else
                    cell.getFigure().setFill(Color.BLACK);
            }
        }
    }

    /**
     * Perform the rules and changes to execute in each time step
     */
    public void change(){
        rules();
        update();
        setColor();
        getData().add(getCritic());
    }

    /**
     * Count the number of cells alive
     * @return The current number of cells alive
     */
    protected int getCritic(){
        int critic = 0;
        for (int i=0; i<getSize(); i++){
            for (int j=0; j<getSize(); j++){
                if (getCell(i,j).getState()==1)
                    critic++;
            }
        }
        return critic;
    }

    //Rules

    /**
     * GameOfLife's Rules
     * Any site (dead or alive) with tree living
     * neighbor sites stays alive or is born
     */
    private void rules(){
        Cell cell;
        int livingNeighbor;
        for (int i=0; i<getSize(); i++){
            for (int j=0; j<getSize(); j++){
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
