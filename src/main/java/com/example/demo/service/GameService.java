package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.Collection;

public interface GameService {

    String createGame(String gameName, int numOfPlayers, int boardSize);

    Game getGameById(String gameId);

    GameStatus getGameStatus(String gameStatus);

    boolean deleteGame(String gameId);

    Collection<Game> getAllGames();
}
