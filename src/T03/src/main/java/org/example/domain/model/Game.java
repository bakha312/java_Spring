package org.example.domain.model;

import java.util.UUID;

public class Game {
    private UUID id;
    private GameField field;

    public Game(UUID id, GameField field){
        this.id = id;
        this.field = field;
    }
    public UUID getId() {
        return id;
    }

    public GameField getField() {
        return field;
    }
}
