package com.example.demo.services.game;

import com.example.demo.dto.GamePositionDTO;
import com.example.demo.entities.GameEntity;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {

    Optional<Game> getGameById(String gameId);

    @Transactional
    Game instanceGame(String gameType);

    GameStatus getGameStatus(String gameId);

    @Transactional
    String deleteGame(String gameId);

    List<Game> getAllGames();

    void getTokenWithName(String game, String tokenName);

    void moveTo(Token token, GamePositionDTO position) throws InvalidPositionException;

}
