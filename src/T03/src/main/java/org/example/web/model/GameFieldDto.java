package org.example.web.model;

public class GameFieldDto {
    private int[][] cells;

    public GameFieldDto() {
    }

    public GameFieldDto(int[][] cells) {
        this.cells = cells;
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }
}
