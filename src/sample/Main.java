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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.*;
import sketches.TFigure;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    private VBox inputFields;
    private HBox text1, text2, buttons, arrayUI;
    private TextField createOnX, createOnY, moveToX, moveToY, radius, changedRadius, x1, y1, changedLength,
            height, width, changedHeight, changedWidth, radius1, changedRadius1;
    private Label figureName1, figureName2, movement, creation, inputError1, inputError2, arraySection;
    private Button createBtn, moveBtn, createRandomBtn, hideBtn, showBtn, clearAllBtn, flipEllipse, createArray,
    showArray, eliminateArrayOfFigures, moveArray, deleteArrayFromCanvas;
    CheckBox checkBox = new CheckBox("Работа с массивом");
    private ComboBox<String> chooseFigure;
    private ArrayList<TFigure> figures = new ArrayList<>();
    private ArrayList<Circle> circlesList = new ArrayList<>();
    private ArrayList<Rectangle> rectangleList = new ArrayList<>();
    private ArrayList<Line> linesList = new ArrayList<>();
    private ArrayList<Rhombus> rhombusList = new ArrayList<>();
    private ArrayList<Trapezium> trapeziumList = new ArrayList<>();
    private ArrayList<Ellipse> ellipsesList = new ArrayList<>();
    private ObservableList<String> figuresNames = FXCollections.observableArrayList(Circle.NAME, Rectangle.NAME, Line.NAME,
            Rhombus.NAME, Trapezium.NAME, Ellipse.NAME);
    private static final String COORDINATE = "COORDINATE";
    private static final String DIMENSION = "DIMENSION";


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Fourth Lab");
        HBox root = new HBox();

        Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, rectangle2D.getWidth(), rectangle2D.getHeight()-34);

        primaryStage.setScene(scene);
        primaryStage.show();

        initDefaultToolsField();
        initToolsText();
        createBtn = new Button("Создать");
        createRandomBtn = new Button("Создать со случайными значениями");
        moveBtn = new Button("Переместить");
        hideBtn = new Button("Скрыть");
        Tooltip t = new Tooltip("Скрыть фигуры данного типа");
        t.setShowDelay(Duration.ZERO);
        hideBtn.setTooltip(t);
        showBtn = new Button("Показать");
        Tooltip t1 = new Tooltip("Показать фигуры данного типа");
        t1.setShowDelay(Duration.ZERO);
        showBtn.setTooltip(t1);
        clearAllBtn = new Button("Очистить холст");
        flipEllipse = new Button("Перевернуть эллипс");
        Tooltip t2 = new Tooltip("Переворачивает только эллипс!");
        t2.setShowDelay(Duration.ZERO);
        flipEllipse.setTooltip(t2);
        flipEllipse.setVisible(false);


        buttons = new HBox(16);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(clearAllBtn, hideBtn, showBtn, moveBtn);

        arrayUI = new HBox(16);
        arrayUI.setAlignment(Pos.CENTER);
        arrayUI.getChildren().addAll(createArray, deleteArrayFromCanvas, eliminateArrayOfFigures, showArray, moveArray);

        text1 = new HBox();
        text1.getChildren().addAll(creation, figureName1);

        text2 = new HBox();
        text2.getChildren().addAll(movement, figureName2);

        chooseFigure = new ComboBox<>(figuresNames);
        chooseFigure.setPromptText("Изобразить и переместить:");
        chooseFigure.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (chooseFigure.getValue() != null) {
                    setSpecificTools(chooseFigure.getValue());
                    inputError1.setVisible(false);
                    inputError2.setVisible(false);
                    flipEllipse.setVisible(true);
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
        settings.getChildren().addAll(chooseFigure, inputFields, arraySection, arrayUI);

        Canvas canvas = new Canvas(rectangle2D.getWidth()/4*3, rectangle2D.getHeight()-34);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

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

                            circlesList.add(new Circle(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(radius.getText())));
                            rectangleList.add(null);
                            linesList.add(null);
                            rhombusList.add(null);
                            trapeziumList.add(null);
                            ellipsesList.add(null);

                            circlesList.get(circlesList.size() - 1).show(graphicsContext);
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

                            rectangleList.add(new Rectangle(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(height.getText()), toDouble(width.getText())));
                            circlesList.add(null);
                            linesList.add(null);
                            rhombusList.add(null);
                            trapeziumList.add(null);
                            ellipsesList.add(null);

                            rectangleList.get(rectangleList.size() - 1).show(graphicsContext);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Line.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(x1.getText(), COORDINATE);
                            isInputCorrect(y1.getText(), COORDINATE);

                            linesList.add(new Line(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(x1.getText()), toDouble(y1.getText())));
                            circlesList.add(null);
                            rectangleList.add(null);
                            rhombusList.add(null);
                            trapeziumList.add(null);
                            ellipsesList.add(null);

                            linesList.get(linesList.size() - 1).show(graphicsContext);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Rhombus.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(height.getText(), DIMENSION);
                            isInputCorrect(width.getText(), DIMENSION);

                            linesList.add(null);
                            circlesList.add(null);
                            rectangleList.add(null);
                            rhombusList.add(new Rhombus(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(height.getText()), toDouble(width.getText())));
                            trapeziumList.add(null);
                            ellipsesList.add(null);

                            rhombusList.get(rhombusList.size() - 1).show(graphicsContext);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Trapezium.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(height.getText(), DIMENSION);
                            isInputCorrect(width.getText(), DIMENSION);

                            linesList.add(null);
                            circlesList.add(null);
                            rectangleList.add(null);
                            trapeziumList.add(new Trapezium(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(height.getText()), toDouble(width.getText())));
                            rhombusList.add(null);
                            ellipsesList.add(null);

                            trapeziumList.get(trapeziumList.size() - 1).show(graphicsContext);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                    case Ellipse.NAME:
                        try{
                            isInputCorrect(createOnX.getText(), COORDINATE);
                            isInputCorrect(createOnY.getText(), COORDINATE);
                            isInputCorrect(radius.getText(), DIMENSION);
                            isInputCorrect(radius1.getText(), DIMENSION);

                            linesList.add(null);
                            circlesList.add(null);
                            rectangleList.add(null);
                            ellipsesList.add(new Ellipse(toDouble(createOnX.getText()), toDouble(createOnY.getText()),
                                    toDouble(radius.getText()), toDouble(radius1.getText())));
                            rhombusList.add(null);
                            trapeziumList.add(null);

                            ellipsesList.get(ellipsesList.size() - 1).show(graphicsContext);
                        }catch (Exception e){
                            inputError1.setVisible(true);
                        }
                        break;
                }

            }
        });
        createRandomBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (chooseFigure.getValue()){
                    case Circle.NAME:
                        circlesList.add(new Circle());
                        rectangleList.add(null);
                        linesList.add(null);
                        rhombusList.add(null);
                        trapeziumList.add(null);
                        ellipsesList.add(null);

                        circlesList.get(circlesList.size() - 1).show(graphicsContext);
                        break;
                    case Rectangle.NAME:
                            rectangleList.add(new Rectangle());
                            circlesList.add(null);
                            linesList.add(null);
                        rhombusList.add(null);
                        trapeziumList.add(null);
                        ellipsesList.add(null);

                            rectangleList.get(rectangleList.size() - 1).show(graphicsContext);
                        break;
                    case Line.NAME:
                            linesList.add(new Line());
                            circlesList.add(null);
                            rectangleList.add(null);
                        rhombusList.add(null);
                        trapeziumList.add(null);
                        ellipsesList.add(null);

                            linesList.get(linesList.size() - 1).show(graphicsContext);
                        break;
                    case Trapezium.NAME:
                        rectangleList.add(null);
                        circlesList.add(null);
                        linesList.add(null);
                        rhombusList.add(null);
                        trapeziumList.add(new Trapezium());
                        ellipsesList.add(null);

                        trapeziumList.get(trapeziumList.size() - 1).show(graphicsContext);
                        break;
                    case Rhombus.NAME:
                        rectangleList.add(null);
                        circlesList.add(null);
                        linesList.add(null);
                        rhombusList.add(new Rhombus());
                        trapeziumList.add(null);
                        ellipsesList.add(null);

                        rhombusList.get(rhombusList.size() - 1).show(graphicsContext);
                        break;
                    case Ellipse.NAME:
                        circlesList.add(null);
                        rectangleList.add(null);
                        linesList.add(null);
                        rhombusList.add(null);
                        trapeziumList.add(null);
                        ellipsesList.add(new Ellipse());

                        ellipsesList.get(ellipsesList.size() - 1).show(graphicsContext);
                        break;
                }
            }
        });
        moveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                if (circlesList.size() != 0){
                    inputError2.setVisible(false);

                    switch (chooseFigure.getValue()){
                        case Circle.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                if (checkBox.isSelected()){
                                    for (TFigure f : figures){
                                        if (f instanceof Circle) {
                                            Circle c = (Circle) f;
                                            if (!isOptionalFieldCorrect(changedRadius.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                c.changeRadius(toDouble(changedRadius.getText()));
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }else {
                                    for (Circle c : circlesList) {
                                        if (c != null) {
                                            if (!isOptionalFieldCorrect(changedRadius.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                c.changeRadius(toDouble(changedRadius.getText()));
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                        case Rectangle.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                if (checkBox.isSelected()){
                                    for (TFigure f : figures){
                                        if (f instanceof Rectangle) {
                                            Rectangle r = (Rectangle) f;
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }else {
                                    for (Rectangle r : rectangleList) {
                                        if (r != null) {
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            } else {
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                        case Line.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                for (Line l : linesList){
                                    if (l != null){
                                        if (!isOptionalFieldCorrect(changedLength.getText(), DIMENSION)){
                                            // If optional field is null, this code is executed
                                            l.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()));
                                        }else {
                                            l.changeLength(toDouble(changedLength.getText()));
                                            l.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()));
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                        case Trapezium.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                if (checkBox.isSelected()){
                                    for (TFigure f : figures){
                                        if (f instanceof Trapezium) {
                                            Trapezium r = (Trapezium) f;
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                System.out.println("HEH");
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }else {
                                    for (Trapezium r : trapeziumList) {
                                        if (r != null) {
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            } else {
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                        case Rhombus.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                if (checkBox.isSelected()){
                                    for (TFigure f : figures){
                                        if (f instanceof Rhombus) {
                                            Rhombus r = (Rhombus) f;
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }else {
                                    for (Rhombus r : rhombusList) {
                                        if (r != null) {
                                            if (!isOptionalFieldCorrect(changedWidth.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedHeight.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            } else {
                                                r.changeDimensions(toDouble(changedWidth.getText()),
                                                        toDouble(changedHeight.getText()));
                                                r.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                        case Ellipse.NAME:
                            try{
                                isInputCorrect(moveToX.getText(), COORDINATE);
                                isInputCorrect(moveToY.getText(), COORDINATE);
                                if (checkBox.isSelected()){
                                    for (TFigure f : figures){
                                        if (f instanceof Ellipse) {
                                            Ellipse c = (Ellipse) f;
                                            if (!isOptionalFieldCorrect(changedRadius.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedRadius1.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);

                                            } else {
                                                c.changeRadii(toDouble(changedRadius.getText()),
                                                        toDouble(changedRadius1.getText()));
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }else {
                                    for (Ellipse c : ellipsesList) {
                                        if (c != null) {

                                            if (!isOptionalFieldCorrect(changedRadius.getText(), DIMENSION) &&
                                                    !isOptionalFieldCorrect(changedRadius1.getText(), DIMENSION)) {
                                                // If optional field is null, this code is executed
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            } else {
                                                c.changeRadii(toDouble(changedRadius.getText()),
                                                        toDouble(changedRadius1.getText()));
                                                c.move(toDouble(moveToX.getText()), toDouble(moveToY.getText()), graphicsContext);
                                            }
                                        }
                                    }
                                }
                                clearCanvas(canvas, graphicsContext);
                                drawAgain(graphicsContext);
                            }catch (Exception e){
                                inputError2.setVisible(true);
                            }
                            break;
                    }
                }
//            }
        });
        hideBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (circlesList.size() != 0){
                    switch (chooseFigure.getValue()){
                        case Circle.NAME:
                            for (Circle c : circlesList){
                                if (c != null){
                                    c.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Rectangle.NAME:
                            for (Rectangle r : rectangleList){
                                if (r != null){
                                    r.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Line.NAME:
                            for (Line l : linesList){
                                if (l != null){
                                    l.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Rhombus.NAME:
                            for (Rhombus r : rhombusList){
                                if (r != null){
                                    r.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Trapezium.NAME:
                            for (Trapezium r : trapeziumList){
                                if (r != null){
                                    r.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Ellipse.NAME:
                            for (Ellipse c : ellipsesList){
                                if (c != null){
                                    c.setVisibility(false);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                    }
                }
            }
        });
        showBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (circlesList.size() != 0) {
                    switch (chooseFigure.getValue()){
                        case Circle.NAME:
                            for (Circle c : circlesList){
                                if (c != null){
                                    c.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Rectangle.NAME:
                            for (Rectangle r : rectangleList){
                                if (r != null){
                                    r.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Line.NAME:
                            for (Line l : linesList){
                                if (l != null){
                                    l.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Ellipse.NAME:
                            for (Ellipse c : ellipsesList){
                                if (c != null){
                                    c.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Rhombus.NAME:
                            for (Rhombus r : rhombusList){
                                if (r != null){
                                    r.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                        case Trapezium.NAME:
                            for (Trapezium r : trapeziumList){
                                if (r != null){
                                    r.setVisibility(true);
                                }
                            }
                            clearCanvas(canvas, graphicsContext);
                            drawAgain(graphicsContext);
                            break;
                    }
                }
            }
        });
        clearAllBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (circlesList.size() != 0) {
                    clearCanvas(canvas, graphicsContext);
                    circlesList.clear();
                    rectangleList.clear();
                    linesList.clear();
                    trapeziumList.clear();
                    rhombusList.clear();
                    ellipsesList.clear();
                }
            }
        });
        flipEllipse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Ellipse e : ellipsesList){
                    if (e != null){
                        e.flip();
                    }
                }
                clearCanvas(canvas, graphicsContext);
                drawAgain(graphicsContext);
            }
        });

        createArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Random random = new Random();
                for(int i = 0; i < 30; i++){
                    int r = random.nextInt(5);
                    switch (r){
                        case 0:
                            figures.add(new Circle());
                            break;
                        case 1:
                            figures.add(new Ellipse());
                            break;
                        case 2:
                            figures.add(new Rectangle());
                            break;
                        case 3:
                            figures.add(new Rhombus());
                            break;
                        case 4:
                            figures.add(new Trapezium());
                            break;
                    }
                }
            }
        });
        showArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (TFigure fig : figures){
                    fig.setVisibility(true);
                    fig.show(graphicsContext);
                }
            }
        });
        deleteArrayFromCanvas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (TFigure fig : figures){
                    fig.setVisibility(false);
                }
                clearCanvas(canvas, graphicsContext);
                drawAgain(graphicsContext);
            }
        });
        eliminateArrayOfFigures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearCanvas(canvas, graphicsContext);
                figures.clear();
                drawAgain(graphicsContext);
            }
        });
        moveArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (TFigure f : figures){
                    f.move(toDouble("10"), toDouble("10"), graphicsContext);
                }
                clearCanvas(canvas, graphicsContext);
                drawAgain(graphicsContext);
            }
        });

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

    private boolean isOptionalFieldCorrect(String text, String typeOfField) throws Exception{
        if (!text.isBlank()) {
            if (typeOfField.equals(COORDINATE)) {
                if (!text.matches("(?!-0(\\.0+)?)-?(0|[1-9]\\d*)(\\.\\d+)?")) throw new Exception();
            } else {
                if (!text.matches("(0|[1-9]\\d*)(\\.\\d+)?")) throw new Exception();
            }
            return true;
        }
        return false;
    }

    private double toDouble(String s){
        return Double.parseDouble(s);
    }

    private void setSpecificTools(String name){
//     radius, changedRadius, length, changedLength, height, width
        figureName1.setText(name);
        figureName2.setText(name);
        switch (name) {
            case Circle.NAME:
                clearInputFields(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, radius, inputError1, createBtn,
                        createRandomBtn, text2, checkBox, moveToX, moveToY, changedRadius, inputError2, buttons);
                break;
            case Rhombus.NAME:
            case Trapezium.NAME:
            case Rectangle.NAME:
                clearInputFields(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, height, width, inputError1, createBtn,
                        createRandomBtn, text2, checkBox, moveToX, moveToY, changedHeight, changedWidth, inputError2, buttons);
                break;
            case Line.NAME:
                clearInputFields(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, x1, y1, inputError1, createBtn,
                        createRandomBtn, text2, moveToX, moveToY, changedLength, inputError2, buttons);
                break;
            case Ellipse.NAME:
                clearInputFields(inputFields);
                inputFields.getChildren().addAll(text1, createOnX, createOnY, radius, radius1, inputError1, createBtn,
                        createRandomBtn, text2, checkBox, moveToX, moveToY, changedRadius, changedRadius1,  inputError2, flipEllipse,
                        buttons);
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
        radius1 = new TextField();
        radius1.setPromptText("Задать второй радиус");
        changedRadius = new TextField();
        changedRadius.setPromptText("Задать радиус");
        changedRadius1 = new TextField();
        changedRadius1.setPromptText("Задать второй радиус");
        x1 = new TextField();
        x1.setPromptText("Задать конечную точку X");
        y1 = new TextField();
        y1.setPromptText("Задать конечную точку Y");
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


        createArray = new Button("Создать");
        showArray = new Button("Показать");
        eliminateArrayOfFigures = new Button("Уничтожить");
        moveArray = new Button("Переместить");
        deleteArrayFromCanvas = new Button("Стереть");

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
        arraySection = new Label("Массив фигур");
        arraySection.setFont(Font.font(16));
    }

    private void clearInputFields(VBox vBox){
        if (!vBox.getChildren().isEmpty()){
            vBox.getChildren().clear();
        }
    }

    private void clearCanvas(Canvas canvas, GraphicsContext gc){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawAgain(GraphicsContext gc){
        for (int i = 0; i < circlesList.size(); i++){
            if (circlesList.get(i) != null) {
                if (circlesList.get(i).getVisibility()) circlesList.get(i).show(gc);
            }
            if (rectangleList.get(i) != null){
                if (rectangleList.get(i).getVisibility()) rectangleList.get(i).show(gc);
            }
            if (linesList.get(i) != null) {
                if (linesList.get(i).getVisibility()) linesList.get(i).show(gc);
            }
            if (rhombusList.get(i) != null){
                if (rhombusList.get(i).getVisibility()) rhombusList.get(i).show(gc);
            }
            if (trapeziumList.get(i) != null){
                if (trapeziumList.get(i).getVisibility()) trapeziumList.get(i).show(gc);
            }
            if (ellipsesList.get(i) != null) {
                if (ellipsesList.get(i).getVisibility()) ellipsesList.get(i).show(gc);
            }
        }
        for (TFigure f : figures){
            if (f != null) {
                if (f.getVisibility()) f.show(gc);
            }
        }
    }
}
