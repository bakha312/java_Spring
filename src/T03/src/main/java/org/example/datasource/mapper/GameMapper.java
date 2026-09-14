package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.datasource.model.GameFieldEntity;
import org.example.domain.model.Game;
import org.example.domain.model.GameField;

public class GameMapper {

    public GameEntity toEntity(Game game) {
        GameFieldEntity fieldEntity = new GameFieldEntity(copyCells(game.getField().getCells()));
        return new GameEntity(game.getId(), fieldEntity);
    }

    public Game toDomain(GameEntity entity) {
        GameField field = new GameField(copyCells(entity.getField().getCells()));
        return new Game(entity.getId(), field);
    }

    private int[][] copyCells(int[][] cells) {
        int[][] copy = new int[cells.length][];
        for (int row = 0; row < cells.length; row++) {
            copy[row] = cells[row].clone();
        }
        return copy;
    }
}
