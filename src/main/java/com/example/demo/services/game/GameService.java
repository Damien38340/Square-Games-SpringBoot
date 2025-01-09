package com.example.demo.services.game;

import com.example.demo.plugins.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GameService {

    Game getGameById(String gameId);

    @Transactional
    Game instanceGame(String gameType);

    GameStatus getGameStatus(String gameId);

    @Transactional
    Game deleteGame(String gameId);

    List<Map<String, Object>> getAllGames();
}
