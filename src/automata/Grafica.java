/*
 * Autómatas
 */
package automata;

import javafx.animation.Animation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.FillRule;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.LinkedList;


public class Grafica{

    private static final double ANCHO_GRAFICA = 500.0;
    private static final double LARGO_GRAFICA = 700.0;
    private static final double ANCHO_PANTALLA = 600.0;
    private static final double LARGO_PANTALLA = 800.0;

    private Stage stage;
    private Group root;
    private Scene graficaScene;
    private LinkedList<Integer> datos;

    /**
     * Contructor de la clase
     * @param stage escenario donde se mostrará la gráfica
     * @param datos Lista con los datos que se graficarán
     */
    public Grafica(Stage stage, LinkedList<Integer> datos) {
        this.stage = stage;
        root = new Group();
        graficaScene = new Scene(root, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);
        this.stage.setScene(graficaScene);

        this.datos = datos;
        monta();
    }

    /**
     * Agrega los objetos en el grupo
     */
    private void monta(){
        //Colocación del panel ancla
        AnchorPane grafica = new AnchorPane();
        grafica.setLayoutX(50);
        grafica.setLayoutY(50);

        //Camino de la gráfica
        Path path = new Path();
        double saltoH = LARGO_GRAFICA / datos.size();
        double saltoV = ANCHO_GRAFICA/obtenerMax();
        path.getElements().add(new MoveTo(0, ANCHO_GRAFICA));
        path.setFill(Color.RED);
        for (int i=0; i<datos.size(); i++){
            path.getElements().add(new LineTo((1+i)*saltoH, ANCHO_GRAFICA-datos.get(i)*saltoV));
        }
        //Completa la gráfica para que se rellene
        path.getElements().add(new LineTo(LARGO_GRAFICA, ANCHO_GRAFICA));
        path.getElements().add(new LineTo(0,ANCHO_GRAFICA));
        grafica.getChildren().add(path);

        //Información de la gráfica
        Line abscisas = new Line(0,ANCHO_GRAFICA,LARGO_GRAFICA,ANCHO_GRAFICA);
        Line ordenadas = new Line(0,0,0,ANCHO_GRAFICA);
        Text text_max_ord = new Text(5,0,String.valueOf(obtenerMax()));
        Text text_max_abs = new Text(LARGO_GRAFICA, ANCHO_GRAFICA, String.valueOf(datos.size()));
        Text text_info_ord = new Text(-5, 300,"Estados Críticos");
        text_info_ord.getTransforms().add(new Rotate(270,-5,300));
        Text text_info_abs = new Text(300, 515, "Pasos Temporales");
        grafica.getChildren().addAll(abscisas, ordenadas, text_max_ord, text_max_abs, text_info_ord, text_info_abs );

        // Botón para salir
        Button salir = new Button("Salir");
        salir.setLayoutX(LARGO_PANTALLA-50);
        salir.setLayoutY(ANCHO_PANTALLA-30);

        salir.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    UseAutomata p = new UseAutomata();
                    p.menu(stage);
                    //stage.close();
                }
            });

        root.getChildren().addAll(grafica, salir);
    }

    /**
     * Obtiene el valor máximo de <code>datos</code>
     * @return int Valor máximo
     */
    private int obtenerMax(){
        int max=0;
        for (Integer i: datos){
            if (max<i.intValue())
                max = i.intValue();
        }
        return max;
    }
}
