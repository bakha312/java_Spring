package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private final Map<UUID, GameEntity> games = new ConcurrentHashMap<>();

    public void save(GameEntity gameEntity) {
        games.put(gameEntity.getId(), gameEntity);
    }

    public GameEntity getById(UUID id) {
        return games.get(id);
    }
}