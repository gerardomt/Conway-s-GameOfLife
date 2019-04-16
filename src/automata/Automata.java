/*
 * Autómatas
 */
package automata;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Automata extends AnimationTimer {
    private GridPane root = new GridPane();

    private long lastUpdate = 0;
    private LatticeGameOfLife lattice;
    private int steps;
    private int size;
    private int cont = 0;
    private Button termina = new Button("Termina");

    /**
     * Class Constructor
     * @param stage
     * @param steps Number of time steps
     * @param size Size of the GameOfLife's lattice
     */
    public Automata(Stage stage, int steps, int size) {
        root.setId("hbox-lattice");
        stage.getScene().setRoot(root);

        this.steps = steps;
        this.size = size;
        mount();
    }
    
    /**
     * Mount the lattice on the scene
     */
    private void mount(){
        AnchorPane anchor = new AnchorPane();
        lattice = new LatticeGameOfLife(anchor, size);

        root.add(anchor, 0, 0);
    }

    @Override
    public void handle(long now) {
        if (cont >= steps && steps != -1){
            stop();
        } else if (now - lastUpdate >= 140000000){
            lattice.change();
            lastUpdate = now;
            cont ++;
        }
    }
}
