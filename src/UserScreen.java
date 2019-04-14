package sample;

//Ronan O'Brien - 13050044
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


public class UserScreen extends sample.Login {


    Image image = new Image((new File("gear.jpg").toURI().toString()));
    BackgroundImage myBI = new BackgroundImage((image), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    @Override
    public void start(Stage primaryStage) {


        DecimalFormat df = new DecimalFormat("#0.00");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.setBackground(new Background(myBI));

        Background buttonBackground = new Background(new BackgroundFill(Color.MEDIUMPURPLE,CornerRadii.EMPTY,Insets.EMPTY));

        Scene scene = new Scene(pane,1280,720);

        sample.Customer customer = new sample.Customer(Username);
        primaryStage.setTitle("Main Menu " ); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


        Label title = new Label( "\t" + Username.toUpperCase() + " --> \t Your Credit is: " + df.format(customer.getCredit()) + "\n\n Push the Big Red Button - You know you want to :-)");
        title.setFont(new Font("Verdana",20));
        title.setShape(new Rectangle(200,50));
        title.setTextFill(Color.WHITE);
        title.setBackground(new Background(new BackgroundFill(Color.MEDIUMPURPLE,CornerRadii.EMPTY,Insets.EMPTY)));
        title.setStyle("-fx-font-weight: bold");

        pane.add(title, 1, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.TOP);

        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);


        Button buyProduct = new Button("Buy a Product");
        buyProduct.setPrefSize(200,200);
        //buyProduct.setShape(new Circle(200));
        buyProduct.setBackground(new Background(new BackgroundFill(Color.ORANGERED,CornerRadii.EMPTY,Insets.EMPTY)));

        //pane.add(buyProduct, 0, 2);
        GridPane.setHalignment(buyProduct, HPos.RIGHT);
        hBox.getChildren().add(buyProduct);
        buyProduct.setBorder(new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        Button seeProducts = new Button( "See all Products");
        seeProducts.setBackground(buttonBackground);
        //pane.add(seeProducts, 1, 5);
        seeProducts.setPrefSize(150,150);
        seeProducts.setShape(new Circle(200));
        GridPane.setHalignment(seeProducts, HPos.LEFT);
        //hBox.getChildren().add(seeProducts);
        pane.add(seeProducts,0,4);
        pane.add(hBox, 1, 3);

        sample.SeeProductList seeProductScreen = new sample.SeeProductList();
        sample.BuyProductsScreen buyProductsScreen = new sample.BuyProductsScreen();
        seeProducts.setOnAction(e ->{
            //open a products page which shows all of them
            //this should show me the location of each product as well as the price.
            seeProductScreen.start(primaryStage);

        });

        buyProduct.setOnAction(e ->{

            buyProductsScreen.start(primaryStage);

        });

        Button useCLI = new Button("Use C-L-I");
        useCLI.setPrefSize(150,150);
        useCLI.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        useCLI.setBackground(buttonBackground);
        useCLI.setShape(new Circle(200));
        pane.add(useCLI,2,4);
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
