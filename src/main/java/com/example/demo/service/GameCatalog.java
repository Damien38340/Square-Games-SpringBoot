package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.Collection;

public interface GameCatalog {

    Collection<String> getGameIdentifiers();

    GameFactory getGameFactory(String gameId);


    GameStatus getGameStatus(String gameStatus);

    boolean deleteGame(String gameId);
}
