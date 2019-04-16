package automata;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * A single cell of the lattice
 */
public class Cell{

    protected Rectangle figure = new Rectangle();
    private Cell[] neighborhood;
    private int state;
    private int tempState;
    private int maxState;
    private int posX;
    private int posY;

    /**
     * Class constructor
     * @param state Initial state of the cell
     * @param maxState max state that cell can reach
     * @param edge Length of the edge of the cell
     * @param x Position of the cell on the lattice with respect to
     * x-axis
     * @param y Position of the cell on the lattice with respect to
     * y-axis
     */
    public Cell(int state, int maxState, double edge, int x, int y){
        setState(state);
        this.maxState = maxState;
        posX = x;
        posY = y;
        figure.setWidth(edge);
        figure.setHeight(edge);
        figure.setX(x*edge);
        figure.setY(y*edge);
        figure.setStroke(Color.BLACK);
    }

    /**
     * Set the neighborhood of the cell with respect its position on the lattice
     * @param lattice Lattice where the cell is
     */
    protected void setNeighborhood(MultiArray<Cell> lattice){
        neighborhood = new Cell[8];
        int newPosX=-1;
        int newPosY=-1;
        for (int i=0; i<8; i++){
            switch (i){
            case 0: //NW
                newPosX=posX-1;
                newPosY=posY-1;
                break;
            case 1: //N
                newPosX=posX;
                newPosY=posY-1;
                break;
            case 2: //NE
                newPosX=posX+1;
                newPosY=posY-1;
                break;
            case 3: //E
                newPosX=posX+1;
                newPosY=posY;
                break;
            case 4: //SE
                newPosX=posX+1;
                newPosY=posY+1;
                break;
            case 5: //S
                newPosX=posX;
                newPosY=posY+1;
                break;
            case 6: //SW
                newPosX=posX-1;
                newPosY=posY+1;
                break;
            case 7: //W
                newPosX=posX-1;
                newPosY=posY;
                break;
            }
            if (newPosX<0) newPosX=lattice.getDimension(0)-1;
            if (newPosY<0) newPosY=lattice.getDimension(0)-1;
            if (newPosX>=lattice.getDimension(0)) newPosX=0;
            if (newPosY>=lattice.getDimension(0)) newPosY=0;
            neighborhood[i] = lattice.getElement(new int[] {newPosX, newPosY});
        }
    }

    /**
     * Getter <code>state</code>
     * @return Discrete value of the cell
     */
    public int getState(){
        return state;
    }

    /**
     * Getter of <code>figure</code>
     * @return Figure that represent the cell
     */
    public Rectangle getFigure() {
        return figure;
    }

    /**
     * Setter of <code>state</code>
     * @param state The new state value should be
     * <code>0&le;state&le;maxState</code>. If state is less than
     * zero <code>this.state</code> is set as 0. If
     * <code>state</code> is greater than <code>maxState</code>,
     * then <codo>this.state</code> is set as
     * <code>maxState</code>
     */
    public void setState(int state){
        if (state < 0)
            this.state = 0;
        else if (state > maxState)
            this.state = maxState;
        else
            this.state = state;
        this.tempState = state;
    }

    /**
     * Setter of <code>stateTemp</code>. Set the cell's value in
     * the next time step without modify the current state.
     * @param state
     */
    public void setTempState(int state){
        this.tempState=state;
    }

    /**
     * If <code>tempState</code> has been modified in the current
     * time step <code>state = tempState</code>
     */
    public void update(){
        if (tempState != state)
            setState(tempState);
    }

    /**
     * Getter of <code>neighborhood</code>
     * @return Cell[] neighborhood of the cell
     */
    public Cell[] getNeighborhood(){
        return neighborhood;
    }
}
