package org.example.datasource.model;

public class GameFieldEntity {
    private int [][] cells;

    public GameFieldEntity(int [][] cells){
        this.cells = cells;
    }
    public int[][] getCells(){
        return cells;
    }
}
