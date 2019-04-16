package automata;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Scanner;

/**
 * Main Class
 * @author gerardomt
 * @version 0.1
 */
public class UseAutomata extends Application {

    /**
     * Main entry
     * @param primaryState
     */
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Automata");
        primaryStage.setFullScreen(true);
        menu(primaryStage);
        primaryStage.show();
    }

    /**
     * Método auxiliar para el menú principal
     * @param primaryStage escenario
     */
    void menu(Stage primaryStage){
        GridPane grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);

        Text title = new Text("Game of Life");
        title.setId("menu-title");
        grid.add(title, 0, 0, 2, 1);

        Button startGOL = new Button("Random Start");
        startGOL.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    menuGOL(primaryStage);
                }
            });
        startGOL.setDefaultButton(true);
        HBox hbGOL = new HBox();
        hbGOL.getChildren().add(startGOL);

        grid.add(hbGOL,0,1);

        Button patterns = new Button("Patterns");
        HBox hbPatterns = new HBox();
        hbPatterns.setId("hbox-patterns");
        hbPatterns.getChildren().add(patterns);
        grid.add(hbPatterns, 1,1);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
            (UseAutomata.class.getResource("automata.css").toExternalForm());
    }

    /**
     * Second Menu of Game Of Life
     * @param primaryStage stage
     */
    private void menuGOL(Stage primaryStage){
        GridPane grid = new GridPane();

        Text title = new Text("Game Of Life");
        title.setId("menu-title");
        grid.add(title, 0, 0, 2, 1);

        Label lbSteps = new Label("Time Steps:");
        grid.add(lbSteps, 0, 1);
        TextField flSteps = new TextField("-1");
        grid.add(flSteps, 1, 1);
        Label lbSize = new Label("Lattice's Size:");
        grid.add(lbSize, 0, 2);
        TextField flSize = new TextField("70");
        grid.add(flSize, 1, 2);

        Button begin = new Button("begin");
        begin.setDefaultButton(true);
        HBox hbBegin = new HBox();
        hbBegin.getChildren().add(begin);
        hbBegin.setId("hbox-begin");
        grid.add(hbBegin, 0, 3, 2, 1);
        Text message = new Text();
        message.setId("exception-message");
        grid.add(message, 0, 4, 2, 1);
        begin.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    try {
                        int steps = Integer.parseInt(flSteps.getCharacters().toString());
                        if (steps<-1 || steps==0) throw new IllegalArgumentException("holi");
                        int size = Integer.parseInt(flSize.getCharacters().toString());
                        if (size<1) throw new IllegalArgumentException("holis");
                        Automata automata = new Automata(primaryStage, steps, size);

                        automata.start();
                    } catch(IllegalArgumentException e){
                        message.setText("Illegal Argument"+e);
                    }
                }
            });
        primaryStage.getScene().setRoot(grid);
    }

    /**
     * Método Principal
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
