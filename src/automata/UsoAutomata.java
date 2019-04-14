package automatas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Scanner;

public class UsoAutomata extends Application {

    /**
     * Crear y corre el escenario
     * @param primaryState escenario principal
     * @throws exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Automata");
        menu(primaryStage);
        primaryStage.show();
    }

    /**
     * Método auxiliar para el menú principal
     * @param primaryStage escenario
     */
    void menu(Stage primaryStage){
        double ANCHO_PANTALLA = 300.0;
        double LARGO_PANTALLA = 300.0;
        Group menu = new Group();
        Scene menuScene = new Scene(menu, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);

        Text titulo = new Text(0,100,"AUTÓMATA");
        titulo.setStroke(Color.WHITE);
        titulo.setFont(new Font(30));
        titulo.setFill(Color.WHITE);

        Button bSismo = new Button("Simulación Sismo");
        bSismo.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    menuSismo(primaryStage);
                }
            });

        Button bEpidemias = new Button("Propagación de Epidemias");
        bEpidemias.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    menuEpidemias(primaryStage);
                }
            });

        Button bIncendio = new Button("Incendio Forestal");
        bIncendio.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    menuIncendio(primaryStage);
                }
            });


        VBox vbox = new VBox(20, titulo, bSismo,bEpidemias, bIncendio);
        vbox.setPrefWidth(LARGO_PANTALLA);
        vbox.setPrefHeight(ANCHO_PANTALLA);
        vbox.setAlignment(Pos.CENTER);

        menu.getChildren().add(vbox);

        primaryStage.setScene(menuScene);
    }

    /**
     * Método auxiliar para menu de sismo
     * @param primaryStage stage
     */
    private void menuEpidemias(Stage primaryStage){
        double ANCHO_PANTALLA = 450.0;
        double LARGO_PANTALLA = 400.0;
        Group menu = new Group();
        Scene menuScene = new Scene(menu, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);

        Text titulo = new Text(0,100,"Propagación de Epidemias");
        titulo.setStroke(Color.WHITE);
        titulo.setFont(new Font(30));
        titulo.setFill(Color.WHITE);

        TextField textPasos = new TextField("-1");
        TextField textTamanho = new TextField("30");
        TextField textA = new TextField("3");
        TextField textG = new TextField("4");
        Label lblPasos = new Label("Número de pasos temporales: ");
        lblPasos.setTextFill(Color.WHITE);
        Label lblTamanho = new Label("Tamaño de la arista de la malla: ");
        lblTamanho.setTextFill(Color.WHITE);
        Label lblA = new Label("Valor A: ");
        lblA.setTextFill(Color.WHITE);
        Label lblG = new Label("Valor G: ");
        lblG.setTextFill(Color.WHITE);

        Button comenzar = new Button("Comenzar");
        comenzar.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    try {
                        int pasos = Integer.parseInt(textPasos.getCharacters().toString());
                        if (pasos<-1 || pasos==0) throw new IllegalArgumentException();
                        int tamanho = Integer.parseInt(textTamanho.getCharacters().toString());
                        int a = Integer.parseInt(textA.getCharacters().toString());
                        int g = Integer.parseInt(textG.getCharacters().toString());
                        if (tamanho<1 || a<1 || g<1) throw new IllegalArgumentException();
                        Automata automata = new Automata(primaryStage, pasos, tamanho, a,g);

                        automata.start();
                    } catch(IllegalArgumentException e){
                        mensaje(menu, "Los valores no son válidos", 50, ANCHO_PANTALLA-50);

                    }
                }
            });

        VBox vbox = new VBox(20, titulo, lblPasos, textPasos, lblTamanho, textTamanho, lblA, textA,lblG, textG, comenzar);
        vbox.setPrefWidth(LARGO_PANTALLA);
        vbox.setPrefHeight(ANCHO_PANTALLA);
        vbox.setAlignment(Pos.CENTER);

        menu.getChildren().add(vbox);

        primaryStage.setScene(menuScene);
    }

    /**
     * Método auxiliar para menu de sismo
     * @param primaryStage stage
     */
    private void menuSismo(Stage primaryStage){
        double ANCHO_PANTALLA = 400.0;
        double LARGO_PANTALLA = 400.0;
        Group menu = new Group();
        Scene menuScene = new Scene(menu, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);

        Text titulo = new Text(0,100,"Simulación Sismo");
        titulo.setStroke(Color.WHITE);
        titulo.setFont(new Font(30));
        titulo.setFill(Color.WHITE);

        TextField textPasos = new TextField("-1");
        TextField textTamanho = new TextField("30");
        TextField textUmbral = new TextField("4");
        Label lblPasos = new Label("Número de pasos temporales (-1 para pasos indefinidos): ");
        lblPasos.setTextFill(Color.WHITE);
        Label lblTamanho = new Label("Tamaño de la arista de la malla: ");
        lblTamanho.setTextFill(Color.WHITE);
        Label lblUmbral = new Label("Valor umbral: ");
        lblUmbral.setTextFill(Color.WHITE);

        Button comenzar = new Button("Comenzar");
        comenzar.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    try {
                        int pasos = Integer.parseInt(textPasos.getCharacters().toString());
                        if (pasos<-1 || pasos==0) throw new IllegalArgumentException();
                        int tamanho = Integer.parseInt(textTamanho.getCharacters().toString());
                        int umbral = Integer.parseInt(textUmbral.getCharacters().toString());
                        if (umbral<2 || tamanho<1) throw new IllegalArgumentException();
                        Automata automata = new Automata(primaryStage, pasos, tamanho, umbral);

                        automata.start();
                    
                    }catch(IllegalArgumentException e){
                        mensaje(menu, "Los valores no son válidos", 50, ANCHO_PANTALLA-50);
                    }
                }
            });

        VBox vbox = new VBox(20, titulo, lblPasos, textPasos, lblTamanho, textTamanho, lblUmbral, textUmbral, comenzar);
        vbox.setPrefWidth(LARGO_PANTALLA);
        vbox.setPrefHeight(ANCHO_PANTALLA);
        vbox.setAlignment(Pos.CENTER);

        menu.getChildren().add(vbox);

        primaryStage.setScene(menuScene);
    }

        /**
     * Método auxiliar para menu de sismo
     * @param primaryStage stage
     */
    private void menuIncendio(Stage primaryStage){
        double ANCHO_PANTALLA = 520.0;
        double LARGO_PANTALLA = 400.0;
        Group menu = new Group();
        Scene menuScene = new Scene(menu, LARGO_PANTALLA, ANCHO_PANTALLA, Color.DARKSLATEGRAY);

        Text titulo = new Text(0,100,"Incendios Forestales");
        titulo.setStroke(Color.WHITE);
        titulo.setFont(new Font(30));
        titulo.setFill(Color.WHITE);

        TextField textPasos = new TextField("-1");
        TextField textTamanho = new TextField("30");
        TextField textP = new TextField("0.5");
        TextField textF = new TextField("0.5");
        TextField textG = new TextField("0.5");
        Label lblPasos = new Label("Número de pasos temporales (-1 para pasos indefinidos): ");
        lblPasos.setTextFill(Color.WHITE);
        Label lblTamanho = new Label("Tamaño de la arista de la malla: ");
        lblTamanho.setTextFill(Color.WHITE);
        Label lblP = new Label("Prob. de que cresca un árbol[0.0-1.0] : ");
        lblP.setTextFill(Color.WHITE);
        Label lblF = new Label("Prob. de que un árbol se incendie [0.0-1.0]: ");
        lblF.setTextFill(Color.WHITE);

        Label lblG = new Label("Prob. de que un árbol sea inmune [0.0-1.0]: ");
        lblG.setTextFill(Color.WHITE);

        Button comenzar = new Button("Comenzar");
        comenzar.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    try{
                        
                        int pasos = Integer.parseInt(textPasos.getCharacters().toString());
                        if (pasos<-1 || pasos==0) throw new IllegalArgumentException();
                        int tamanho = Integer.parseInt(textTamanho.getCharacters().toString());
                        double p = Double.parseDouble(textP.getCharacters().toString());
                        double f = Double.parseDouble(textF.getCharacters().toString());
                        double g = Double.parseDouble(textG.getCharacters().toString());
                        if (tamanho<1 || p<0.0 || p>1.0 || f<0.0 || f>1.0 || g<0.0 || g>1.0) throw new IllegalArgumentException();
                        Automata automata = new Automata(primaryStage, pasos, tamanho, p,f,g);

                        automata.start();
                    }catch(IllegalArgumentException e){
                        mensaje(menu, "Los valores no son válidos", 50, ANCHO_PANTALLA-50);
                    }
                }
            });

        VBox vbox = new VBox(20, titulo, lblPasos, textPasos, lblTamanho, textTamanho, lblP, textP,lblF, textF, lblG, textG, comenzar);
        vbox.setPrefWidth(LARGO_PANTALLA);
        vbox.setPrefHeight(ANCHO_PANTALLA);
        vbox.setAlignment(Pos.CENTER);

        menu.getChildren().add(vbox);

        primaryStage.setScene(menuScene);
    }

    /**
     * Método auxiliar para mostrar mensajes ocasionales en el escenario
     * @param group Grupo donde se agregará el mensaje
     * @param mensaje Mensaje que se mostrará
     * @param x pos. x del mensaje
     * @param y pos. y del mensaje
     */
    private void mensaje(Group group, String mensaje, double x, double y){
        Text warning = new Text(mensaje);
        warning.setFill(Color.RED);
        warning.setX(x);
        warning.setY(y);
        group.getChildren().add(warning);
    }
        

    /**
     * Método Principal
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    }
