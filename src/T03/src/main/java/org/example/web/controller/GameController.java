package org.example.web.controller;

import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.web.mapper.WebGameMapper;
import org.example.web.model.GameDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {

    private final GameService gameService;
    private final WebGameMapper mapper;

    public GameController(GameService gameService, WebGameMapper mapper) {
        this.gameService = gameService;
        this.mapper = mapper;
    }

    @PostMapping("/game/{id}")
    public GameDto play(@PathVariable UUID id, @RequestBody GameDto dto) {
        Game incoming = mapper.toDomain(id, dto);
        Game updated = gameService.getNextMove(incoming);
        return mapper.toDto(updated);
    }
}
