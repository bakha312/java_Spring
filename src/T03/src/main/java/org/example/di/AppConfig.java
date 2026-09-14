package org.example.di;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.datasource.repository.GameStorage;
import org.example.datasource.service.GameServiceImpl;
import org.example.domain.service.GameService;
import org.example.web.mapper.WebGameMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GameStorage gameStorage() {
        return new GameStorage();
    }

    @Bean
    public GameMapper gameMapper() {
        return new GameMapper();
    }

    @Bean
    public GameRepository gameRepository(GameStorage gameStorage, GameMapper gameMapper) {
        return new GameRepository(gameStorage, gameMapper);
    }

    @Bean
    public GameService gameService(GameRepository gameRepository) {
        return new GameServiceImpl(gameRepository);
    }

    @Bean
    public WebGameMapper webGameMapper() {
        return new WebGameMapper();
    }
}
