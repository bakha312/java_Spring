package org.example.datasource.service;

import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.model.GameField;
import org.example.domain.service.GameRules;
import org.example.domain.service.GameService;

import java.util.UUID;

public class GameServiceImpl implements GameService {

    public static final int EMPTY = 0;
    public static final int PLAYER = 1;
    public static final int COMPUTER = 2;

    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game getNextMove(Game game) {
        Game stored = repository.getById(game.getId());
        if (stored == null) {
            stored = emptyGame(game.getId());
        }

        if (!validateField(stored, game)) {
            throw new IllegalArgumentException("Invalid game field: previous moves were changed or the player move is incorrect");
        }

        if (isGameOver(game)) {
            repository.save(game);
            return game;
        }

        int[][] cells = copyCells(game.getField().getCells());
        int[] move = findBestComputerMove(cells);
        cells[move[0]][move[1]] = COMPUTER;

        Game updated = new Game(game.getId(), new GameField(cells));
        repository.save(updated);
        return updated;
    }

    @Override
    public boolean validateField(Game oldGame, Game newGame) {
        if (oldGame == null || newGame == null) {
            return false;
        }
        if (oldGame.getId() == null || !oldGame.getId().equals(newGame.getId())) {
            return false;
        }
        if (oldGame.getField() == null || newGame.getField() == null) {
            return false;
        }

        int[][] oldCells = oldGame.getField().getCells();
        int[][] newCells = newGame.getField().getCells();
        if (!isThreeByThree(oldCells) || !isThreeByThree(newCells)) {
            return false;
        }

        int playerMoves = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int oldValue = oldCells[row][col];
                int newValue = newCells[row][col];

                if (oldValue != EMPTY && oldValue != newValue) {
                    return false;
                }
                if (oldValue == EMPTY && newValue != EMPTY) {
                    if (newValue != PLAYER) {
                        return false;
                    }
                    playerMoves++;
                }
            }
        }
        return playerMoves == 1;
    }

    @Override
    public boolean isGameOver(Game game) {
        int[][] cells = game.getField().getCells();
        return GameRules.checkWinner(cells) != 0 || GameRules.isBoardFull(cells);
    }

    private int[] findBestComputerMove(int[][] cells) {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == EMPTY) {
                    cells[row][col] = COMPUTER;
                    int score = minimax(cells, false);
                    cells[row][col] = EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }
        return new int[]{bestRow, bestCol};
    }

    private int minimax(int[][] cells, boolean isMaximizing) {
        int winner = GameRules.checkWinner(cells);
        if (winner == COMPUTER) {
            return 1;
        }
        if (winner == PLAYER) {
            return -1;
        }
        if (GameRules.isBoardFull(cells)) {
            return 0;
        }

        int best = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == EMPTY) {
                    cells[row][col] = isMaximizing ? COMPUTER : PLAYER;
                    int score = minimax(cells, !isMaximizing);
                    cells[row][col] = EMPTY;
                    best = isMaximizing ? Math.max(best, score) : Math.min(best, score);
                }
            }
        }
        return best;
    }

    private Game emptyGame(UUID id) {
        return new Game(id, new GameField(new int[3][3]));
    }

    private int[][] copyCells(int[][] cells) {
        int[][] copy = new int[3][3];
        for (int row = 0; row < 3; row++) {
            copy[row] = cells[row].clone();
        }
        return copy;
    }

    private boolean isThreeByThree(int[][] cells) {
        if (cells == null || cells.length != 3) {
            return false;
        }
        for (int row = 0; row < 3; row++) {
            if (cells[row] == null || cells[row].length != 3) {
                return false;
            }
        }
        return true;
    }
}
