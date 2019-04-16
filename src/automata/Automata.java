/*
 * Autómatas
 */
package automata;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Automata extends AnimationTimer {
    //private double ancho_pantalla;
    //private static final double LARGO_PANTALLA = 800.0;
    //private static final double ANCHO_PANTALLA = 600.0;

    //private Stage stage;
    private Group root = new Group();
    //private Scene mallaScene;

    private Text tiempo;
    private long lastUpdate = 0;
    private Lattice lattice;
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
        stage.getScene().setRoot(root);

        this.steps = steps;
        this.size = size;
        monta(2);
    }
    
    /**
     * Agrega los objetos en el grupo
     * @param umbral Valor umbral para la malla Sismo
     */
    private void monta(int umbral){
        //Malla
        AnchorPane ancla = new AnchorPane();
        ancla.setLayoutX(200);
        ancla.setLayoutY(0);
        lattice = new LatticeGameOfLife(size, umbral, ancla);

        //estableceBoton();

        root.getChildren().addAll(ancla, termina);
    }


    /**
     * Método auxiliar para colocar el botón y programar sus eventos
     */
    // private void estableceBoton(){
    //     termina.setLayoutX(30);
    //     termina.setLayoutY(550);
    //     //Acción de termina
    //     termina.setOnAction(new EventHandler<ActionEvent>(){
    //             @Override
    //             public void handle(ActionEvent event){
    //                 Group grafica = new Group();
    //                 Grafica g = new Grafica(stage, getMalla().getData());
    //             }
    //         });
    // }

   /**
    * Getter de <code>malla</code>
    * @return Malla malla del automata celular
    */
    private Lattice getLattice(){
        return lattice;
    }

    @Override
    public void handle(long now) {
        if (cont >= steps && steps != -1){
            //Detener ejecución
            stop();
            //termina.fire();
        } else if (now - lastUpdate >= 140000000){
            lattice.change();
            lastUpdate = now;
            cont ++;
        }
    }
}
