package miinaharava.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import miinaharava.logic.Board;
import miinaharava.logic.Tile;

public class MinesweeperUI extends Application {

    private Board board;
    private Scene menuScene;
    private Scene lostScene;
    private Scene winScene;

    private Parent getMenuWindow(Stage stage) {
        VBox vBox = new VBox();

        Text text = new Text("MIINAHARAVA");
        text.setFont(Font.font(44));

        Button easy = new Button("HELPPO --- 9x9 ALUE --- 10 MIINAA");
        easy.setPrefWidth(260);
        easy.setOnAction(value -> {
            board = new Board(9, 9, 10);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });
        Button medium = new Button("KOHTALAINEN --- 16x16 ALUE --- 40 MIINAA");
        medium.setPrefWidth(260);
        medium.setOnAction(value -> {
            board = new Board(16, 16, 40);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });
        Button hard = new Button("VAIKEA --- 30x16 ALUE --- 99 MIINAA");
        hard.setPrefWidth(260);
        hard.setOnAction(value -> {
            board = new Board(30, 16, 99);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });

        vBox.getChildren().addAll(text, easy, medium, hard);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(600, 400);
        vBox.setSpacing(10);

        return vBox;
    }

    private Parent getBoardWindow(Stage stage) {
        int test = board.getMinesCount();
        BorderPane borderPane = new BorderPane();

        BorderPane headerPane = new BorderPane();
        Button button = new Button("Päävalikkoon");
        button.setOnAction(value -> {
            stage.setScene(menuScene);
        });
        Text minesLeft = new Text("X: " + test);
        Text timeLeft = new Text("--:--");
        headerPane.setLeft(button);
        headerPane.setCenter(minesLeft);
        headerPane.setRight(timeLeft);
        headerPane.setAlignment(timeLeft, Pos.CENTER);
        headerPane.setPadding(new Insets(5, 5, 5, 5));
        headerPane.setPrefHeight(35);

        GridPane gridPane = new GridPane();
        int tileSize = 30;

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                StackPane tile = new StackPane();

                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setFill(Color.LIGHTGRAY);
                rectangle.setStroke(Color.GRAY);

                Text flag = new Text();
                flag.setFont(Font.font(16));
                flag.setVisible(false);
                flag.setText("P");

                Text value = new Text();
                value.setText(board.getGrid()[x][y].hasMine() ? "X" : String.valueOf(board.getGrid()[x][y].getNumber()));
                value.setFont(Font.font(16));
                value.setVisible(false);

                EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
                    if (e.getButton() == MouseButton.PRIMARY && !flag.isVisible() && !value.isVisible()) {
                        rectangle.setFill(Color.WHITE);
                        value.setVisible(true);
                        board.updateOpenTiles();
                        if (board.isGameOver()) {
                            stage.setScene(winScene);
                        }
                    } else if (e.getButton() == MouseButton.SECONDARY && !value.isVisible()) {
                        flag.setVisible(!flag.isVisible());
                        if (flag.isVisible()) {
                            board.updateFlagsSet(1);
                        } else {
                            board.updateFlagsSet(-1);
                        }
                        minesLeft.setText("X: " + (board.getMinesCount() - board.getFlagsSet()));
                    }
                };

                tile.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                tile.getChildren().addAll(rectangle, value, flag);
                tile.setAlignment(Pos.CENTER);
                tile.setTranslateX(x * tileSize);
                tile.setTranslateY(y * tileSize);

                gridPane.getChildren().add(tile);
            }
        }

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                Tile tile = board.getTile((int) (e.getX() / tileSize), (int) (e.getY() / tileSize));
                if (tile.hasMine()) {
                    stage.setScene(lostScene);
                }
            }
        };
        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        borderPane.setTop(headerPane);
        borderPane.setCenter(gridPane);
        int prefW = tileSize * board.getWidth() + 1;
        int prefH = tileSize * board.getHeight() + 35 + 1;
        borderPane.setPrefSize(prefW, prefH);

        return borderPane;
    }

    private Parent getLostWindow(Stage stage) {
        VBox vBox = new VBox();
        Text text = new Text();
        text.setText("HÄVISIT PELIN");
        text.setFont(Font.font(22));
        Button button = new Button("PALAA PÄÄVALIKKOON");
        button.setOnAction(value -> {
            stage.setScene(menuScene);
        });
        vBox.getChildren().addAll(text, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(300, 200);
        vBox.setSpacing(10);
        return vBox;
    }

    private Parent getWinWindow(Stage stage) {
        VBox vBox = new VBox();

        Text won = new Text();
        won.setText("VOITIT PELIN");
        won.setFont(Font.font(22));

        Text time = new Text();
        time.setText("AIKA: --:--");
        time.setFont(Font.font(16));

        HBox hBox = new HBox();
        TextField textField = new TextField();
        textField.setPromptText("SYÖTÄ NIMESI");
        textField.setDisable(true);
        Button saveButton = new Button("TALLENNA JA PALAA PÄÄVALIKKOON");
        saveButton.setDisable(true);
        hBox.getChildren().addAll(textField, saveButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Button button = new Button("PALAA PÄÄVALIKKOON");
        button.setOnAction(value -> {
            stage.setScene(menuScene);
        });

        vBox.getChildren().addAll(won, time, hBox, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(400, 300);
        vBox.setSpacing(10);

        return vBox;
    }

    @Override
    public void start(Stage stage) {
        menuScene = new Scene(getMenuWindow(stage));
        lostScene = new Scene(getLostWindow(stage));
        winScene = new Scene(getWinWindow(stage));
        stage.setScene(menuScene);
        stage.setTitle("Miinaharava");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
