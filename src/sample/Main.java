package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.Circle;
import models.Line;
import models.Rectangle;

import java.util.ArrayList;

public class Main extends Application {

    private VBox inputFields;
    private HBox text1, text2;
    private TextField createOnX, createOnY, moveToX, moveToY, radius, changedRadius, length, changedLength,
            height, width, changedHeight, changedWidth;
    private Label figureName1, figureName2, movement, creation, inputError1, inputError2;
    private Button createBtn, moveBtn, createRandomBtn;
    private ComboBox<String> chooseFigure;
    private ArrayList<Circle> circlesList = new ArrayList<>();
    private ArrayList<Rectangle> rectangleList = new ArrayList<>();
    private ArrayList<Line> linesList = new ArrayList<>();
    private ObservableList<String> figuresNames = FXCollections.observableArrayList(Circle.NAME, Rectangle.NAME, Line.NAME);
    private static final String COORDINATE = "COORDINATE";
    private static final String DIMENSION = "DIMENSION";


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Second Lab");
        HBox root = new HBox();

        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, rectangle2D.getWidth(), rectangle2D.getHeight()-34);

        primaryStage.setScene(scene);
        primaryStage.show();

        initDefaultToolsField();
        initToolsText();

        createBtn = new Button("Создать");
        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inputError1.setVisible(false);
                switch (chooseFigure.getValue()){
                    case Circle.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(radius.getText(), DIMENSION);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Rectangle.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(height.getText(), DIMENSION);
                            isInputCorrect(width.getText(), DIMENSION);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Line.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(length.getText(), DIMENSION);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                }

            }
        });

        createRandomBtn = new Button("Создать со случайными значениями");
        createRandomBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        //TODO: Except input before creation of any object
        moveBtn = new Button("Переместить");
        moveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        text1 = new HBox();
        text1.getChildren().addAll(creation, figureName1);

        text2 = new HBox();
        text2.getChildren().addAll(movement, figureName2);

        chooseFigure = new ComboBox<>(figuresNames);
        chooseFigure.setPromptText("Изобразить:");
        chooseFigure.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (chooseFigure.getValue() != null) {
                    setSpecificTools(chooseFigure.getValue());
                }
            }
        });

        VBox settings = new VBox(16);
        settings.setAlignment(Pos.TOP_CENTER);
        settings.setPadding(new Insets(16));
        settings.setPrefSize(rectangle2D.getWidth()/4, rectangle2D.getHeight()-34);
        settings.setStyle("-fx-background-color: #EEEEEE;");
        settings.setFillWidth(false);
        settings.setBorder(new Border(new BorderStroke(Paint.valueOf("#CACACA"), BorderStrokeStyle.SOLID,
                new CornerRadii(0, 4, 4, 0, false), BorderStroke.DEFAULT_WIDTHS)));
//text1, createOnX, createOnY, createBtn, text2, moveToX, moveToY, moveBtn
        settings.getChildren().addAll(chooseFigure, inputFields);

        Canvas canvas = new Canvas(rectangle2D.getWidth()/4*3, rectangle2D.getHeight()-34);
        canvas.setStyle("-fx-background-color: #336699;");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(10,10, 20,20);

        root.getChildren().addAll(settings, canvas);

        primaryStage.setX(rectangle2D.getMinX());
        primaryStage.setY(rectangle2D.getMinY());
        primaryStage.setMinHeight(rectangle2D.getHeight());
        primaryStage.setMinWidth(rectangle2D.getWidth());

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void isInputCorrect(String text, String typeOfField) throws Exception{
        if (text == null) throw new Exception();
        if (typeOfField.equals(COORDINATE)) {
            if (!text.matches("(?!-0(\\.0+)?)-?(0|[1-9]\\d*)(\\.\\d+)?")) throw new Exception();
        }else {
            if (!text.matches("(0|[1-9]\\d*)(\\.\\d+)?")) throw new Exception();
        }
    }

    private void setSpecificTools(String name){
//     radius, changedRadius, length, changedLength, height, width
        figureName1.setText(name);
        figureName2.setText(name);
        switch (name) {
            case Circle.NAME:
                hasChildren(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, radius, inputError1, createBtn,
                        createRandomBtn, text2, moveToX, moveToY, changedRadius, inputError2, moveBtn);
                break;
            case Rectangle.NAME:
                hasChildren(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, height, width, inputError1, createBtn,
                        createRandomBtn, text2, moveToX, moveToY, changedHeight, changedWidth, inputError2, moveBtn);
                break;
            case Line.NAME:
                hasChildren(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, length, inputError1, createBtn,
                        createRandomBtn, text2, moveToX, moveToY, changedLength, inputError2, moveBtn);
                break;
        }
    }

    private void initDefaultToolsField(){
        inputFields = new VBox(16);
        inputFields.setAlignment(Pos.TOP_CENTER);

        createOnX = new TextField();
        createOnX.setPromptText("Задать Х");
        createOnY = new TextField();
        createOnY.setPromptText("Задать Y");
        moveToX = new TextField();
        moveToX.setPromptText("Задать Х");
        moveToY = new TextField();
        moveToY.setPromptText("Задать Y");
        radius = new TextField();
        radius.setPromptText("Задать радиус");
        changedRadius = new TextField();
        changedRadius.setPromptText("Задать радиус");
        length = new TextField();
        length.setPromptText("Задать длину");
        changedLength = new TextField();
        changedLength.setPromptText("Задать длину");
        height = new TextField();
        height. setPromptText("Задать высоту");
        width = new TextField();
        width. setPromptText("Задать ширину");
        changedHeight = new TextField();
        changedHeight. setPromptText("Задать высоту");
        changedWidth = new TextField();
        changedWidth. setPromptText("Задать ширину");
        inputError1 = new Label("В одном или нескольких полях допущена ошибка при вводе");
        inputError1.setTextFill(Color.RED);
        inputError1.setVisible(false);
        inputError2 = new Label("В одном или нескольких полях допущена ошибка при вводе");
        inputError2.setTextFill(Color.RED);
        inputError2.setVisible(false);
    }

    private void initToolsText(){
        figureName1 = new Label();
        figureName1.setFont(Font.font(16));
        figureName2 = new Label();
        figureName2.setFont(Font.font(16));
        creation = new Label("Создать ");
        creation.setFont(Font.font(16));
        movement = new Label("Переместить ");
        movement.setFont(Font.font(16));
    }

    private void hasChildren(VBox vBox){
        if (!vBox.getChildren().isEmpty()){
            vBox.getChildren().clear();
        }
    }

}
