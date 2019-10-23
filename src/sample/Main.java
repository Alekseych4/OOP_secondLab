package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField createOnX, createOnY, moveToX, moveToY;
    private Label figureName, movement, creation;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Second Lab");
        HBox root = new HBox();

        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, rectangle2D.getWidth(), rectangle2D.getHeight()-34);

        createOnX = new TextField();
        createOnX.setPromptText("Задать Х");
        createOnY = new TextField();
        createOnY.setPromptText("Задать Y");
        moveToX = new TextField();
        moveToX.setPromptText("Задать Х");
        moveToY = new TextField();
        moveToY.setPromptText("Задать Y");

        figureName = new Label();
        creation = new Label("Создать ");
        creation.setFont(Font.font(16));
        movement = new Label("Переместить ");
        movement.setFont(Font.font(16));

        HBox text1 = new HBox();
        text1.getChildren().addAll(creation, figureName);

        HBox text2 = new HBox();
        text2.getChildren().addAll(movement, figureName);

        VBox settings = new VBox(16);
        settings.setAlignment(Pos.TOP_CENTER);
        settings.setPadding(new Insets(16));
        settings.setPrefSize(rectangle2D.getWidth()/4, rectangle2D.getHeight()-34);
        settings.setStyle("-fx-background-color: #EEEEEE;");
        settings.setFillWidth(false);
        settings.setBorder(new Border(new BorderStroke(Paint.valueOf("#CACACA"), BorderStrokeStyle.SOLID,
                new CornerRadii(0, 4, 4, 0, false), BorderStroke.DEFAULT_WIDTHS)));

        settings.getChildren().addAll(text1, createOnX, createOnY, text2, moveToX, moveToY);

        Canvas canvas = new Canvas(rectangle2D.getWidth()/4*3, rectangle2D.getHeight()-34);
        canvas.setStyle("-fx-background-color: #336699;");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(10,10, 20,20);
        graphicsContext.setFill(Color.BLACK);

        root.getChildren().addAll(settings, canvas);

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setX(rectangle2D.getMinX());
        primaryStage.setY(rectangle2D.getMinY());
        primaryStage.setMinHeight(rectangle2D.getHeight());
        primaryStage.setMinWidth(rectangle2D.getWidth());
    }


    public static void main(String[] args) {
        launch(args);
    }

}
