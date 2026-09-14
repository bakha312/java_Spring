package org.example.web.mapper;

import org.example.domain.model.Game;
import org.example.domain.model.GameField;
import org.example.web.model.GameDto;
import org.example.web.model.GameFieldDto;

import java.util.UUID;

public class WebGameMapper {

    public Game toDomain(UUID pathId, GameDto dto) {
        if (dto == null || dto.getField() == null || dto.getField().getCells() == null) {
            throw new IllegalArgumentException("Game field is required");
        }
        return new Game(pathId, new GameField(copyCells(dto.getField().getCells())));
    }

    public GameDto toDto(Game game) {
        GameFieldDto fieldDto = new GameFieldDto(copyCells(game.getField().getCells()));
        return new GameDto(game.getId(), fieldDto);
    }

    private int[][] copyCells(int[][] cells) {
        int[][] copy = new int[cells.length][];
        for (int row = 0; row < cells.length; row++) {
            copy[row] = cells[row].clone();
        }
        return copy;
    }
}
