package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ticTacToe extends Application {

    //board button
    private Button[][] buttons= new Button[3][3];

    // variable for increment the score
    private Label playerXscoreLabel, playerYscoreLabel;
    private int playerXScore = 0, playerYScore = 0;

    private boolean playerXturn = true;


    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        //***************** title ***************
        Label titleLable = new Label("Tic Tac Toe");
        titleLable.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold");
        root.setTop(titleLable);
        BorderPane.setAlignment(titleLable, Pos.CENTER);

        // ************************game board*********************

        // we use the gridPane to add add our buttons which is act as X ans 0;
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 23pt; -fx-font-weight : bold");

                // action on the button
                button.setOnAction(actionEvent -> clickingButton(button));
                buttons[i][j] = button;
                gridPane.add(button,j,i);
            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        // ************************* score ****************************
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXscoreLabel = new Label("Player X : 0");
        playerXscoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        playerYscoreLabel = new Label("Player O : 0");
        playerYscoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");

        // add score of x and y inside the Hbox;
        scoreBoard.getChildren().addAll(playerXscoreLabel,playerYscoreLabel);

        // add score inside the pane's bottom;

        root.setBottom(scoreBoard);


        return root;
    }

    // after clicking the button
    private void clickingButton(Button button){
        if(button.getText().equals("")){
            if(playerXturn){
                button.setText("X");
            }
            else{
                button.setText("O");
            }

            //reverse the turn of x player
            playerXturn = !playerXturn;

            checkWinner();
        }

        return;
    }

    // finding the winner
    private void checkWinner(){
        // check row
        for (int row = 0; row < 3; row++) {
            if(   buttons[row][0].getText().equals(buttons[row][1].getText())
               && buttons[row][1].getText().equals(buttons[row][2].getText())
               && !buttons[row][0].getText().isEmpty()
            ){

                String winner = buttons[row][0].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return ;
            }

        }

        // check col
        for (int col = 0; col < 3; col++) {
            if(   buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()
            ){

                String winner = buttons[0][col].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // check diagonal
        int dia = 0;
        if(   buttons[dia][dia].getText().equals(buttons[dia+1][dia+1].getText())
                && buttons[dia+1][dia+1].getText().equals(buttons[dia+2][dia+2].getText())
                && !buttons[dia][dia].getText().isEmpty()
        ){

            String winner = buttons[dia][dia].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

//        dia = 2;
        if(   buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()
        ){

            String winner = buttons[0][2].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        boolean tie = true;
        for(Button row[] : buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }

        if(tie){
            showTieDialogue();
            resetBoard();
        }


    }

    private void showWinnerDialogue(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations! " + winner + " is the winner");
        alert.setHeaderText("");
        alert.showAndWait();

    }

    private void showTieDialogue(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game over! it's a tie");
        alert.setHeaderText("");
        alert.showAndWait();

    }


    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXscoreLabel.setText("Player X : " + playerXScore);
        }
        else{
            playerYScore++;
            playerYscoreLabel.setText("Player O : "+ playerYScore);
        }
    }

    // reset the board
    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}