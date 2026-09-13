package org.example.domain.service;

public class GameRules  {

    public static int checkWinner(int[][] cells) {
        // проверка строк
        for (int row = 0; row < 3; row++) {
            if (cells[row][0] != 0 && cells[row][0] == cells[row][1] && cells[row][1] == cells[row][2]) {
                return cells[row][0];   // вернёт 1 или 2 — кто победил
            }
        }

        // проверка столбцов — попробуй написать сам, по аналогии со строками
        // проверка столбцов
        for (int col = 0; col < 3; col++) {
            if (cells[0][col] != 0 && cells[0][col] == cells[1][col] && cells[1][col] == cells[2][col]) {
                return cells[0][col];
            }
        }

        // проверка двух диагоналей — тоже попробуй сам

        if (cells[0][0] != 0 && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return cells[0][0];
        }

        if (cells[0][2] != 0 && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return cells[0][2];
        }


        return 0;   // никто не выиграл (пока)
    }

    public static boolean isBoardFull(int[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == 0) {
                    return false;   // нашли ПУСТУЮ клетку → поле ТОЧНО не заполнено
                }
            }
        }
        return true;   // прошли ВСЕ клетки, пустых не встретили → поле ЗАПОЛНЕНО
    }
}
