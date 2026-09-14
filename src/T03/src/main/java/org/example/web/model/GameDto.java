package org.example.web.model;

import java.util.UUID;

public class GameDto {
    private UUID id;
    private GameFieldDto field;

    public GameDto() {
    }

    public GameDto(UUID id, GameFieldDto field) {
        this.id = id;
        this.field = field;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GameFieldDto getField() {
        return field;
    }

    public void setField(GameFieldDto field) {
        this.field = field;
    }
}
