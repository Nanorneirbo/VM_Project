
package sample;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AdminScreen extends sample.Login {

    Image image = new Image((new File("mboard.jpg").toURI().toString()));
    BackgroundImage myBI = new BackgroundImage((image), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(myBI));

        Scene scene = new Scene(pane, 750, 500);
        primaryStage.setTitle("Welcome to Cheapo Solutions What would you like to do?"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Shutdown add product and Use CLI buttons
        Button addProduct = new Button("Add Product");
        Button shutDown = new Button("Shut Down Machine");
        Button useCLI = new Button("Use Command Line");
        Button restock = new Button("Restock products");

        restock.setShape(new Circle((100)));
        restock.setPrefSize(150,150);

        addProduct.setShape(new Circle(100));
        addProduct.setPrefSize(150, 150);


        shutDown.setShape(new Circle(100));
        shutDown.setPrefSize(150, 150);


        HBox mainBox = new HBox();
        mainBox.getChildren().add(addProduct);
        mainBox.getChildren().add(shutDown);
        mainBox.getChildren().add(restock);
        mainBox.getChildren().add(useCLI);

        mainBox.setSpacing(10);
        mainBox.setAlignment(Pos.CENTER);

        pane.setCenter(mainBox);


        addProduct.setOnAction(e -> {
            sample.AddProductScreen prodScreen = new sample.AddProductScreen();
            prodScreen.start(primaryStage);
        });
        restock.setOnAction(e -> {
            sample.RestockProducts restockScreen = new sample.RestockProducts();
            restockScreen.start(primaryStage);
        });

        shutDown.setOnAction(e ->{
            primaryStage.hide();
        });

        useCLI.setOnAction(e->{

            primaryStage.hide();
            try
            {
                sample.VendingMachine machine = new sample.VendingMachine();
                VendingMachineMenu menu = new VendingMachineMenu();
                menu.run(machine);
            }
            catch(IOException x){
             x.printStackTrace();
            }
        });
    }
}