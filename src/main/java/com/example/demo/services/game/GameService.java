package com.example.demo.services.game;

import com.example.demo.plugins.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.List;

public interface GameService {

    Game getGameById(String gameId);

    Game instanceGame(String gameType);

    GameStatus getGameStatus(String gameId);

    boolean deleteGame(String gameId);

    List<GamePlugin> getAllGames();
}
