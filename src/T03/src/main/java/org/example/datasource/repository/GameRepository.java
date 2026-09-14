package org.example.datasource.repository;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;

import java.util.UUID;

public class GameRepository {

    private final GameStorage storage;
    private final GameMapper mapper;

    public GameRepository(GameStorage storage, GameMapper mapper) {
        this.storage = storage;
        this.mapper = mapper;
    }

    public void save(Game game) {
        GameEntity entity = mapper.toEntity(game);
        storage.save(entity);
    }

    public Game getById(UUID id) {
        GameEntity entity = storage.getById(id);
        if (entity == null) {
            return null;
        }
        return mapper.toDomain(entity);
    }
}