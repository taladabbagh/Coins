package com.example.project_1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    @FXML
    Button btn = new Button();

    @FXML
    TextArea tableta = new TextArea();

    @FXML
    TextField usercoinstf = new TextField();

    @FXML
    TextField exprestf = new TextField();

    @FXML
    TextField chosentf = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableta.setVisible(false);
        exprestf.setVisible(false);
        chosentf.setVisible(false);
    }
    public void start(){
        if(usercoinstf.getText()!=""){
            chosentf.setVisible(true);
            exprestf.setVisible(true);
            tableta.setVisible(true);
            Driver obj = new Driver();
            String str = usercoinstf.getText();
            String[] splitNumbers = str.split(" ");
            int[] coins = new int[splitNumbers.length];
            for (int i = 0; i < splitNumbers.length; i++) {
                coins[i] = Integer.parseInt(splitNumbers[i]);
            }

            exprestf.setText(String.valueOf(Driver.optimalStrategy(coins)));
            tableta.setText(Arrays.deepToString(Driver.getTable()));
            chosentf.setText(Arrays.toString(Driver.getUsedCoins()));
            //String used = Driver.max + " " + Driver.min;
        }
        else{
            usercoinstf.setPromptText("ENTER COIN VALUES HERE! (5 4 12 10)") ;
        }
    }

    public void exit(){
        System.exit(1);
    }
}
