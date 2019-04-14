package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BuyProductsScreen extends sample.UserScreen {


    @Override
    public void start(Stage primaryStage){
        int i;


        Background paneBackGround = new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY));

        // create a new vending machine
        sample.VendingMachine vend = new sample.VendingMachine();
        sample.UserScreen userscreen = new sample.UserScreen();
        sample.SeeProductList seeList = new sample.SeeProductList();

        //pull in the two arraylists
        ArrayList<Button> buttonProducts = vend.getbuttons();
        // Set up a new customer pass it the username so it will pull in the credit values
        sample.Customer customer = new sample.Customer(Username);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 12.5));
        //pane.setPrefSize(600,400);
        pane.setStyle("-fx-border-color: black;");
        pane.setBackground(paneBackGround);


        Scene scene = new Scene(pane,1280,720);

        primaryStage.setTitle("Welcome to Ronans Vending Machine by Cheapo Solutions Buy a Product:"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        DecimalFormat df = new DecimalFormat("#.00");

        Label title = new Label("Welcome " + Username.toUpperCase() + " - current credit is : € " + df.format(customer.getCredit()));
        title.setTextFill(Color.BLUE);
        title.setStyle("-fx-font-weight: bold");
        //title.setWrapText(true);
       // title.setAlignment(Pos.TOP_CENTER);
        title.setFont(new Font("Verdana", 36));
        title.setPrefSize(1000, 100);


        //Set initial credit balance
        VBox titleBox = new VBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefWidth(200);
        pane.setTop(titleBox);

        //central flow - pane will take a variable number of buttons
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(buttonProducts);
        flowPane.setHgap(5);
        flowPane.setVgap(5);

        pane.setCenter(flowPane);

        // Set up a format for only 2 digits
    // logout and Main menu button set ups
        Background buttonBackGround = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        Button logout = new Button("Logout");
        Button userScreen = new Button ( "Main Menu");
        Button useCLI = new Button("Use C-L-I");
        useCLI.setPrefSize(100,100);
        useCLI.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        useCLI.setBackground(buttonBackGround);
        useCLI.setShape(new Circle(200));



        logout.setPrefSize(100,100);
        userScreen.setPrefSize(100,100);
        logout.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        userScreen.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Button SeeProductList = new Button("See Products");
        SeeProductList.setPrefSize(100,100);
        SeeProductList.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        SeeProductList.setShape(new Circle(200));



        SeeProductList.setBackground(buttonBackGround);

        userScreen.setBackground(buttonBackGround);
        logout.setBackground(buttonBackGround);
        logout.setShape(new Circle(200));
        userScreen.setShape(new Circle(200));

        Background productsButtons = new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY));

        //Bottom Box - to tell people what we vended.
        VBox vendBox = new VBox();
        pane.setBottom(vendBox);

        VBox leftBox = new VBox();
        leftBox.getChildren().add(userScreen);
        leftBox.getChildren().add(SeeProductList);
        leftBox.getChildren().add(logout);
        leftBox.getChildren().add(useCLI);
        leftBox.setSpacing(40);
        pane.setRight(leftBox);

        Popup popup = new Popup();
        Button okButton = new Button("OK");


        for(i=0; i<buttonProducts.size(); i++) {
            buttonProducts.get(i).setBackground(productsButtons);
            buttonProducts.get(i).setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            int j=i;//cant use the i in the lambda for some reason.
            buttonProducts.get(i).setOnAction(e -> {

                //needs to be read in inside the lambda loop so as to reflect updates in quantity.
                ArrayList<sample.Product> allProducts = vend.getProducts();

                titleBox.getChildren().clear();

                sample.Product product = new sample.Product(allProducts.get(j).getName(), allProducts.get(j).getLocation(), allProducts.get(j).getPrice(),allProducts.get(j).getQuantiy());

                if(product.getQuantiy()<=0) {

                    //out of Stock message
                    VBox popBox = new VBox();
                    popBox.setSpacing(5);
                    popup.getContent().addAll(new Rectangle( 800, 50, Color.RED));
                    Label quantityLow = new Label ("\tSorry we are out of Stock of " + product.getName() + " please try another Product");
                    quantityLow.setAlignment(Pos.CENTER);
                    quantityLow.setFont(new Font ("Verdana",20));

                    quantityLow.setStyle("-fx-font-weight: bold");

                    popup.getContent().add(popBox);
                    popBox.getChildren().add(quantityLow);
                    popBox.getChildren().add(okButton);
                    popBox.setAlignment(Pos.BOTTOM_CENTER);
                    popup.show(primaryStage);

                }
                else {
                    if ((customer.getCredit() - product.getPrice() > 0)) {

                        vend.buyProduct(product);
                        customer.buyProduct(product.getPrice());

                        Label title2 = new Label("Welcome " + Username.toUpperCase() + " - current credit is : € " + df.format(customer.getCredit()));
                        title2.setFont(new Font("Verdana", 36));
                        title2.setPrefSize(1000, 100);
                        title2.setTextFill(Color.BLUE);
                        title2.setStyle("-fx-font-weight: bold");
                        titleBox.getChildren().add(title2);
                        vendBox.getChildren().clear();
                        Label title3 = new Label("We have vended 1 " + product.getName() + "\nWhy not try another selection?\nThere are now " + allProducts.get(j).getQuantiy() + " " + product.getName() + " in stock");
                        title3.setShape(new Rectangle(20, 100));
                        title3.setTextFill(Color.BLUE);
                        title3.setStyle("-fx-font-weight: bold");
                        title3.setAlignment(Pos.CENTER);
                        title3.setFont(new Font("Verdana", 36));
                        vendBox.getChildren().add(title3);

                    } else {
                        Label title2 = new Label("Sorry " + Username.toUpperCase() + " you only have € " + df.format(customer.getCredit()) + " so you \ndo not have enough credit to buy a " + product.getName() + "\n\t\t which costs: €" + df.format(product.getPrice()));
                        title2.setFont(new Font("Verdana", 36));
                        title2.setPrefSize(1000, 150);
                        title2.setTextFill(Color.RED);
                        title2.setStyle("-fx-font-weight: bold");
                        titleBox.getChildren().add(title2);



                    }

                }
            });
        }
//logout action button
        logout.setOnAction(e ->{
            sample.Login login = new sample.Login();
            customer.updateUserList();
            login.start(primaryStage);


        });
        userScreen.setOnAction(e -> {
            userscreen.start(primaryStage);

        });
        SeeProductList.setOnAction(e -> {
            seeList.start(primaryStage);


        });

        okButton.setOnAction(e -> {
           popup.hide();
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



