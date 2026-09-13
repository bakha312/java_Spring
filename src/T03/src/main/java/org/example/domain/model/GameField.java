package org.example.domain.model;

public class GameField {
    private int [][] cells;

    public GameField(int [][] cells){
        this.cells = cells;
    }
    public int[][] getCells(){
        return cells;
    }
}
