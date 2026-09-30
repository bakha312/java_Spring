package org.example.domain.service;

public class GameRules  {

    public static int checkWinner(int[][] cells) {

        for (int row = 0; row < 3; row++) {
            if (cells[row][0] != 0 && cells[row][0] == cells[row][1] && cells[row][1] == cells[row][2]) {
                return cells[row][0];   // вернёт 1 или 2 — кто победил
            }
        }


        for (int col = 0; col < 3; col++) {
            if (cells[0][col] != 0 && cells[0][col] == cells[1][col] && cells[1][col] == cells[2][col]) {
                return cells[0][col];
            }
        }



        if (cells[0][0] != 0 && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return cells[0][0];
        }

        if (cells[0][2] != 0 && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return cells[0][2];
        }


        return 0;
    }

    public static boolean isBoardFull(int[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
