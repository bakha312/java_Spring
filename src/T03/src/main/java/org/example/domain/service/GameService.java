package org.example.domain.service;

import org.example.domain.model.Game;

public interface GameService {
    Game getNextMove(Game game);
    boolean validateField(Game oldGame, Game newGame);
    boolean isGameOver(Game game);
}
