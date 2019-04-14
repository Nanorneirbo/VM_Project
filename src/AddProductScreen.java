package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.AdminScreen;

import java.io.File;
import java.io.IOException;

public class AddProductScreen extends sample.Login {

    Image image = new Image((new File("trucks.jpg").toURI().toString()));
    BackgroundImage myBI = new BackgroundImage((image),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(myBI));
        sample.VendingMachine vend = new sample.VendingMachine();

        Scene scene = new Scene(pane, 1280, 720);
        primaryStage.setTitle("Add a new product"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        Label title = new Label("Add a new product to the machine:");
        title.setPrefSize(1500,75);
        title.setFont(Font.font("Verdana", 20));
        title.setStyle("-fx-font-weight: bold");
        title.setAlignment(Pos.CENTER);

        pane.setTop(title);

        TextField name = new TextField();
        TextField location = new TextField();
        TextField price = new TextField();
        TextField quantity = new TextField();
        Label nameLabel = new Label("Name of Product: ");
        nameLabel.setTextFill(Color.WHITE);
        Label locationLabel = new Label("Location of Product: ");
        locationLabel.setTextFill(Color.WHITE);
        Label priceLabel = new Label("Price of product");
        priceLabel.setTextFill(Color.WHITE);
        Label quantityLabel = new Label("Quantity of product");
        quantityLabel.setTextFill(Color.WHITE);

        Button addProduct = new Button("Add Product");

        GridPane grid = new GridPane();
        grid.add(nameLabel, 2, 2);
        grid.add(locationLabel, 2, 3);
        grid.add(priceLabel, 2, 4);
        grid.add(quantityLabel, 2, 5);

        grid.add(name,3,2);
        grid.add(location,3,3);
        grid.add(price,3,4);
        grid.add(quantity,3,5);
        grid.setAlignment(Pos.CENTER);
        grid.add(addProduct,4,6);
        pane.setAlignment(grid, Pos.CENTER);
        pane.setCenter(grid);

        Button mainmenu = new Button("Main Menu");
        mainmenu.setPrefSize(150,150);
        mainmenu.setShape(new Circle(150));
        Button logout = new Button("Log Out");
        logout.setPrefSize(150,150);
        logout.setShape(new Circle(150));
        Button useCLI = new Button("useCLI");
        useCLI.setPrefSize(150,150);
        useCLI.setShape(new Circle(150));
        Button shutdown = new Button("ShutDown Machine");
        shutdown.setPrefSize(150,150);
        shutdown.setShape(new Circle(150));

        VBox rightBox = new VBox();
        rightBox.setSpacing(40);
        rightBox.getChildren().add(mainmenu);
        rightBox.getChildren().add(logout);
        rightBox.getChildren().add(useCLI);
        rightBox.getChildren().add(shutdown);
        pane.setRight(rightBox);

       //Popup to allow for a product already existing
        Popup popup = new Popup();
        VBox popBox = new VBox();
        Button okButton = new Button("OK");


        addProduct.setOnAction(e->{
        boolean notADouble = true;
    int i;

    sample.Product product = new sample.Product(name.getText(), location.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()));
    boolean locationDouble = false;
    int k =0;

    for(i=0; i<vend.getProducts().size();i++) {
        if (vend.getProducts().get(i).getLocation().equalsIgnoreCase(product.getLocation())) {
            locationDouble = true;
            k = i;
        }


        if (vend.getProducts().get(i).getName().equalsIgnoreCase(product.getName())) {

                Label alreadyExists = new Label("\t There is already a product called " + product.getName() + " please reload Location " + vend.getProducts().get(i).getLocation()+ " rather than add a new location");
                alreadyExists.setAlignment(Pos.CENTER);
                alreadyExists.setStyle("-fx-font-weight: bold");

                popup.getContent().clear();
                popBox.getChildren().clear();
                popup.getContent().addAll(new Rectangle(500, 50, Color.RED));
                popup.getContent().add(popBox);
                popBox.setSpacing(5);
                popBox.getChildren().add(alreadyExists);
                popBox.getChildren().add(okButton);
                popBox.setAlignment(Pos.BOTTOM_CENTER);
                popup.show(primaryStage);
                notADouble = false;

            }

    }
            if(locationDouble){
                Label locationFull = new Label("Location " + product.getLocation() + " Already contains " + vend.getProducts().get(k).getName() + " Select another location! ");
                locationFull.setAlignment(Pos.CENTER);
                locationFull.setStyle("-fx-font-weight: bold");


                popup.getContent().clear();
                popBox.getChildren().clear();
                popup.getContent().addAll(new Rectangle(500, 50, Color.RED));
                popup.getContent().add(popBox);
                popBox.setSpacing(5);
                popBox.getChildren().add(locationFull);
                popBox.getChildren().add(okButton);
                popBox.setAlignment(Pos.BOTTOM_CENTER);
                popup.show(primaryStage);

            }
            if ((notADouble == true) && (locationDouble==false)) {
                Label confirmAdd = new Label(product.getName() + " has been added to the machine in Location: " + product.getLocation());
                pane.setBottom(confirmAdd);
                vend.addProduct(product);
            }
});

        okButton.setOnAction(e->{
            popup.hide();

        });

        mainmenu.setOnAction(e->{
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);

        });

        logout.setOnAction(e->{
            sample.Login login = new sample.Login();
            login.start(primaryStage);
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

        shutdown.setOnAction(e->{

            primaryStage.hide();

        });




    }


}
