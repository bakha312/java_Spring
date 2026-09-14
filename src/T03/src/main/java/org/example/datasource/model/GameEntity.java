package org.example.datasource.model;

import java.util.UUID;

public class GameEntity {
    private UUID id;
    private GameFieldEntity field;

    public GameEntity(UUID id, GameFieldEntity field){
        this.id = id;
        this.field = field;
    }
    public UUID getId() {
        return id;
    }

    public GameFieldEntity getField() {
        return field;
    }
}
