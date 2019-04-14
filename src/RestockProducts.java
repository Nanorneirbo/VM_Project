package sample;

import javafx.geometry.Insets;
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
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RestockProducts extends sample.UserScreen {

    //import background image
    Image image = new Image((new File("Nano_2.jpg").toURI().toString()));
    BackgroundImage myBI = new BackgroundImage((image),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    @Override
    public void start(Stage primaryStage) {
        int i;

        DecimalFormat df = new DecimalFormat("#.00");


        sample.VendingMachine vend = new sample.VendingMachine();
        ArrayList<Button> buttonProducts = vend.getbuttons();
        ArrayList<sample.Product> allProduts = vend.getProducts();

        sample.UserScreen userscreen = new sample.UserScreen();
        sample.Customer customer = new sample.Customer(Username);
        sample.BuyProductsScreen getList = new sample.BuyProductsScreen();

        BorderPane pane = new BorderPane();
        // pane.setBackground(new Background(myBI));
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 12.5));
        //pane.setPrefSize(600,400);
        pane.setStyle("-fx-border-color: black;");
        Background paneBackGround = new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY));
        pane.setBackground(paneBackGround);

        Scene scene = new Scene(pane, 1280, 720);

        primaryStage.setTitle("Welcome to Cheapo Solutions What would you like to do?"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        Label title = new Label("Available Products - Click to See Quantity and Location");
        title.setFont(new Font("Verdana", 36));
        title.setPrefSize(1000, 50);
        title.setWrapText(true);
        title.setAlignment(Pos.TOP_CENTER);

        VBox titleBox = new VBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefWidth(200);
        pane.setTop(titleBox);

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(buttonProducts);
        pane.setCenter(flowPane);

        //buttons - logout - back to Main Menu - and Use Command Line
        Button logout = new Button("Logout");
        Button userScreen = new Button ( "Main Menu");
        logout.setPrefSize(100,100);
        userScreen.setPrefSize(100,100);
        logout.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        userScreen.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Button useCLI = new Button("Use C-L-I");
        useCLI.setPrefSize(100,100);
        useCLI.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        useCLI.setShape(new Circle(200));



        Button GetProductList = new Button("Buy Products");
        GetProductList.setPrefSize(100,100);
        GetProductList.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GetProductList.setShape(new Circle(200));

        logout.setShape(new Circle(200));
        userScreen.setShape(new Circle(200));


        Background buttonBackGround = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        GetProductList.setBackground(buttonBackGround);

        userScreen.setBackground(buttonBackGround);
        logout.setBackground(buttonBackGround);
        useCLI.setBackground(buttonBackGround);

        //poorly named as I relocated it to the left hand side.
        VBox rightBox = new VBox();
        rightBox.setSpacing(40);
        rightBox.getChildren().add(GetProductList);
        rightBox.getChildren().add(userScreen);
        rightBox.getChildren().add(logout);
        rightBox.getChildren().add(useCLI);
        pane.setRight(rightBox);

        Popup popup = new Popup();
        Button okButton = new Button("OK");

        TextField quantity = new TextField();
        for (i = 0; i < buttonProducts.size(); i++) {

            //lambda to create the appropriate Label displayed when user clicks on each button
            int j = i;//cant use the i in the lambda for some reason.
            buttonProducts.get(i).setOnAction(e -> {
                Label attributesLabel = new Label("Location \t" + "   Price \t" + "Quantity");
                attributesLabel.setStyle("-fx-font-weight: bold");
                attributesLabel.setFont(new Font("Verdana",48));
                Label label1 = new Label(allProduts.get(j).getLocation() + "\t   €" + df.format(allProduts.get(j).getPrice()) + "\t     " + allProduts.get(j).getQuantiy() + "");
                label1.setFont(new Font("Verdana", 48));
                VBox bottomVBox = new VBox();
                bottomVBox.setAlignment(Pos.CENTER);
                bottomVBox.getChildren().add(attributesLabel);
                bottomVBox.getChildren().add(label1);
                pane.setBottom(bottomVBox);
                pane.setAlignment(bottomVBox, Pos.BOTTOM_CENTER);
                VBox popBox = new VBox();
                popBox.setSpacing(5);
                popup.getContent().addAll(new Rectangle( 850, 200, Color.WHITE));
                popup.getContent().add(popBox);
                Label quantboxLabel = new Label ("How many "+ allProduts.get(j).getName() + " do you want to add?");
                quantboxLabel.setFont(new Font("Verdana", 36));
                popBox.setAlignment(Pos.CENTER);
                popBox.getChildren().add(quantboxLabel);
                popBox.getChildren().add(quantity);
                popBox.getChildren().add(okButton);
                popBox.setAlignment(Pos.BOTTOM_CENTER);
                popup.show(primaryStage);

                okButton.setOnAction(x->{
                    int addQuantity = 0;
                    try {
                        addQuantity = Integer.parseInt(quantity.getText());
                    }
                    catch (Exception y){
                        popBox.getChildren().add(new Label("Must enter an integer!"));
                    }
                    vend.restockProduct(addQuantity, j);


                });

            });
        }

        logout.setOnAction(e ->{
            sample.Login login = new sample.Login();
            customer.updateUserList();
            login.start(primaryStage);

        });
        userScreen.setOnAction(e -> {
            userscreen.start(primaryStage);

        });
        GetProductList.setOnAction(e -> {
            getList.start(primaryStage);

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