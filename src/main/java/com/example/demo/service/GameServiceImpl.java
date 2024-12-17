package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameCatalog gameCatalog;

    @Override
    public Game createGame(String gameName, int numOfPlayers, int boardSize) {
        return gameCatalog.getGameFactory(gameName).createGame(numOfPlayers, boardSize);
    }

}
