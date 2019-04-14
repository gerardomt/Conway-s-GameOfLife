/*
 * Autómatas
 */
package automatas;

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
    private static final double LARGO_PANTALLA = 800.0;
    private static final double ANCHO_PANTALLA = 600.0;

    private Stage stage;
    private Group root = new Group();
    private Scene mallaScene = new Scene(root, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);

    private Text tiempo;
    private long lastUpdate = 0;
    private Malla malla;
    private int pasos;
    private int tamanho;
    private int cont = 0;
    private Button termina = new Button("Termina");

    /**
     * Contructor de la clase para malla de Sismos
     * @param stage escenario donde se agregarán objetos que se
     * dibujarán en pantalla
     * @param pasos Pasos temporales que durará la animación
     * @param tamanho Tamaño de la arista de la malla(cuadrada)
     * @param valor_umbral Valor umbral para la malla de sismo
     */
    public Automata(Stage stage, int pasos, int tamanho, int valor_umbral) {
        this.stage = stage;
        this.stage.setScene(mallaScene);

        this.pasos = pasos;
        this.tamanho = tamanho;
        monta(valor_umbral);
    }

    /**
     * Contructor de la clase para malla de Epidemias
     * @param stage escenario donde se agregarán los objetos que se
     * dibujarán en pantalla
     * @param pasos Pasos temporales que durará la animación
     * @param tamanho Tamanho de la arista de la malla(cuadrada)
     * @param a valor de a
     * @param g valor de g
     */
    public Automata(Stage stage, int pasos, int tamanho, int a, int g) {
        this.stage = stage;
        this.stage.setScene(mallaScene);

        this.pasos = pasos;
        this.tamanho = tamanho;
        monta(a,g);
    }

    /**
     * Contructor de la clase para malla de Epidemias
     * @param stage escenario donde se agregarán los objetos que se
     * dibujarán en pantalla
     * @param pasos Pasos temporales que durará la animación
     * @param tamanho Tamanho de la arista de la malla(cuadrada)
     * @param a valor de a
     * @param g valor de g
     */
    public Automata(Stage stage, int pasos, int tamanho, double p, double f, double g) {
        this.stage = stage;
        this.stage.setScene(mallaScene);

        this.pasos = pasos;
        this.tamanho = tamanho;
        monta(p,f,g);
    }

    /**
     * Agrega los objetos en el grupo
     * @param umbral Valor umbral para la malla Sismo
     */
    private void monta(int umbral){
        //Info malla
        AnchorPane info = new AnchorPane();
        info.setLayoutX(10);
        info.setLayoutY(200);

        Rectangle r;
        int salto = (255/umbral);
        for (int i=0; i<=umbral;i++){
            r = new Rectangle(0, i*20, 20, 20);
            r.setFill(Color.rgb(i*salto*-1+255, i*salto*-1+255, i*salto*-1+255));
            r.setStroke(Color.BLACK);
            info.getChildren().add(r);
        }
        Text text1 = new Text (22, 10, ":0");
        text1.setFill(Color.WHITE);
        Text text2 = new Text(22, (umbral+1)*20, "v. umbral("+String.valueOf(umbral)+")");
        text2.setFill(Color.WHITE);

        info.getChildren().addAll(text1,text2);

        //Malla
        AnchorPane ancla = new AnchorPane();
        ancla.setLayoutX(200);
        ancla.setLayoutY(0);
        malla = new MallaSismo(tamanho, umbral, ancla);

        estableceBoton();

        root.getChildren().addAll(info, ancla, termina);
    }

    /**
     * Monta los objeto en el grupo(epidemias)
     * @param a umbral de celdas infecciosas
     * @param g umbral de celdas inmunes
     */
    private void monta(int a, int g){
        //Información de la malla
        AnchorPane info = new AnchorPane();
        info.setLayoutX(10);
        info.setLayoutY(50);

        //Info Susceptible
        Rectangle r;
        r = new Rectangle(0.0, 0.0, 20.0, 20.0);
        r.setFill(Color.WHITE);
        r.setStroke(Color.BLACK);
        Text textS = new Text(0.0, -20, "Susceptible");
        info.getChildren().addAll(r, textS);

        //Info Infeccioso
        Text textInf = new Text(0, 40, "Infeccioso");
        int saltoA = 255/a;
        for (int i=1; i<=a;i++){
            r = new Rectangle(0, i*20+40, 20, 20);
            r.setFill(Color.rgb(255, (i)*saltoA*-1+255, (i)*saltoA*-1+255));
            r.setStroke(Color.BLACK);
            info.getChildren().add(r);
        }
        Text textInfoInf1 = new Text (22, 80, ":0");
        Text textInfoInf2 = new Text(22, (a)*20+60, "a("+String.valueOf(a)+")");

        info.getChildren().addAll(textInfoInf1, textInfoInf2, textInf);

        //Info Inmune
        Text textInm = new Text(0, 20*a+80, "Inmune");
        int saltoG = 255/g;
        for (int i=1; i<=g;i++){
            r = new Rectangle(0, i*20+(a*20+80), 20, 20);
            r.setFill(Color.rgb(i*saltoG*-1+255,255, i*saltoG*-1+255));
            r.setStroke(Color.BLACK);
            info.getChildren().add(r);
        }
        Text textInfoInm1 = new Text (22, a*20+120, ":a+1");
        Text textInfoInm2 = new Text(22, (a+g)*20+100, "g("+String.valueOf(g)+")");

        info.getChildren().addAll(textInfoInm1, textInfoInm2, textInm);

        //malla
        AnchorPane ancla = new AnchorPane();
        ancla.setLayoutX(200);
        ancla.setLayoutY(0);
        malla = new MallaEpidemias(tamanho, ancla, a, g);

        estableceBoton();

        root.getChildren().addAll(info, ancla, termina);
    }

    /**
     * Monta los objeto en el grupo(epidemias)
     * @param p probabilidad de que cresca un arbol
     * @param f probabilidad de que un arbol se incendie
     * espontaneamente
     * @param g probabilidad de que un arbol sea inmune al fuego
     */
    private void monta(double p, double f, double g){
        //Información de la malla
        AnchorPane info = new AnchorPane();
        info.setLayoutX(10);
        info.setLayoutY(50);

        //Info Celda vacia
        Rectangle r;
        r = new Rectangle(0.0, 0.0, 20.0, 20.0);
        r.setFill(Color.WHITE);
        r.setStroke(Color.BLACK);
        Text textV = new Text(0.0, -10, "Vacio");
        info.getChildren().addAll(r, textV);

        //Info Celda con árbol
        Text textA = new Text(0, 40, "Árbol");
        r = new Rectangle(0.0, 50.0, 20.0, 20.2);
        r.setFill(Color.GREEN);
        r.setStroke(Color.BLACK);
        info.getChildren().addAll(r, textA);

        //Info Celda con árbol incendiado
        Text textI = new Text(0, 90, "Árbol Incendiado");
        r = new Rectangle(0.0, 100.0, 20.0, 20.0);
        r.setFill(Color.RED);
        r.setStroke(Color.BLACK);
        info.getChildren().addAll(r, textI);

        //malla
        AnchorPane ancla = new AnchorPane();
        ancla.setLayoutX(200);
        ancla.setLayoutY(0);
        malla = new MallaIncendios(tamanho, ancla, p,f,g);

        estableceBoton();

        root.getChildren().addAll(info, ancla, termina);
    }

    /**
     * Método auxiliar para colocar el botón y programar sus eventos
     */
    private void estableceBoton(){
        termina.setLayoutX(30);
        termina.setLayoutY(550);
        //Acción de termina
        termina.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    Group grafica = new Group();
                    Grafica g = new Grafica(stage, getMalla().getDatos());
                }
            });
    }

   /**
    * Getter de <code>malla</code>
    * @return Malla malla del automata celular
    */
    private Malla getMalla(){
        return malla;
    }

    @Override
    public void handle(long now) {
        if (cont >= pasos && pasos != -1){
            //Detener ejecución
            stop();
            termina.fire();
        } else if (now - lastUpdate >= 300000000){
            malla.cambio();
            lastUpdate = now;
            cont ++;
        }
    }
}
