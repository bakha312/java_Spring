package org.example.domain.service;

import org.example.domain.model.Game;
import org.example.domain.model.GameField;

public class GameServiceImpl implements GameService {

    @Override
    public Game getNextMove(Game game) {
        int[][] cells = game.getField().getCells();

        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == 0) {
                    cells[row][col] = 1;                     // пробуем ход компьютера
                    int score = minimax(cells, false);         // дальше ход человека (минимизатор)
                    cells[row][col] = 0;                        // отменяем пробный ход

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        cells[bestRow][bestCol] = 1;   // теперь делаем РЕАЛЬНЫЙ, окончательный ход

        return new Game(game.getId(), new GameField(cells));
    }

    private int minimax(int[][] cells, boolean isMaximizing) {
        int winner = GameRules.checkWinner(cells);
        if (winner == 1) return 1;
        if (winner == 2) return -1;
        if (GameRules.isBoardFull(cells)) return 0;

        int best = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == 0) {
                    cells[row][col] = isMaximizing ? 1 : 2;
                    int score = minimax(cells, !isMaximizing);
                    cells[row][col] = 0;
                    best = isMaximizing ? Math.max(best, score) : Math.min(best, score);
                }
            }
        }
        return best;
    }

    @Override
    public boolean validateField(Game oldGame, Game newGame) {
        int[][] oldCells = oldGame.getField().getCells();
        int[][] newCells = newGame.getField().getCells();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (oldCells[row][col] != 0 && oldCells[row][col] != newCells[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isGameOver(Game game) {
        int[][] cells = game.getField().getCells();
        return GameRules.checkWinner(cells) != 0 || GameRules.isBoardFull(cells);
    }
}