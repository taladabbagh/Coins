package com.example.project_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("Optimal Game Strategy!");
        stage.setScene(scene);
        stage.show();
    }

    static int[][] dp;
    static int[] usedCoins;

    public static int optimalStrategy(int[] coins) {
        // Create a table to store the maximum value that can be obtained
        // from the coins at each index.
        int n = coins.length;
        dp = new int[n][n];
        usedCoins = new int[n];

        // Fill the table in a bottom-up manner, starting from subproblems of size 1.
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                // Calculate the end index of the subproblem.
                int j = i + len - 1;
                // If there are no coins left, the maximum value is 0.
                if (len == 1) {
                    dp[i][j] = 0;
                }
                // If there is only one coin left, the maximum value is the value of that coin.
                else if (len == 2) {
                    int f = Math.max(coins[i], coins[j]);
                    dp[i][j] = f;
                    usedCoins[i] = f;
                }
                // If there are two coins left, the maximum value is the maximum of the two coins.
                else if (len == 3) {
                    int y = Math.max(coins[i], coins[j]);
                    dp[i][j] = y;
                    usedCoins[i] = y;
                }
                // If there are three or more coins left, the maximum value is the maximum of:
                // - The value of the current coin plus the minimum value of the coins that the opponent will choose.
                // - The value of the next coin plus the minimum value of the coins that the opponent will choose.
                else {
                    int valueFromFirstCoin = coins[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int valueFromLastCoin = coins[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    if (valueFromFirstCoin > valueFromLastCoin) {
                        dp[i][j] = valueFromFirstCoin;
                        usedCoins[i] = coins[i];
                    } else {
                        dp[i][j] = valueFromLastCoin;
                        usedCoins[i] = coins[j];
                    }
                }
            }
        }
        // The maximum value that can be obtained from the coins is the first entry in the table.
        return dp[0][n - 1];
    }


    static int[][] getTable() {
        return dp;
    }

    static int[] getUsedCoins() {
        int[] used=new int[usedCoins.length/2];
     //   int q = 0;
        for (int i = 0, q = 0; i < usedCoins.length; i += 2,q++){
            System.out.println("The first player chose coin with value: " + usedCoins[i]);
            used[q]= usedCoins[i];
        }

        return used;
    }
}