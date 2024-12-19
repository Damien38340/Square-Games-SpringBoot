package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {


    @Value("${ticTacToe.name}")
    private String tictactoeName;
    @Value("${connect4.name}")
    private String Connect4Name;
    @Value("${taquin.name}")
    private String taquinName;


    @Autowired
    List<GamePlugin> gamePluginList;

    @Override
    public Game getGameById(String gameId) {
        return gamePlugins.get(gameId);
    }

    @Override
    public Game instanceGame(String gameType) {

        return gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .findFirst()
                .map(gamePlugin -> gamePlugin.createGame())
                .orElse(null);

    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Game game = gamePlugins.get(gameId);
        return game != null ? game.getStatus() : null;
    }

    @Override
    public boolean deleteGame(String gameId) {
        return gamePlugins.remove(gameId);
    }

    @Override
    public List<GamePlugin> getAllGames() {
        return gamePlugins;
    }

//    private static Token getTokenWithName(Game game, String tokenName) {
//        return Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
//                .flatMap(Collection::stream)
//                .filter(t -> t.getName().equals(tokenName))
//                .filter(t -> t.canMove())
//                .findFirst()
//                .orElse(null);
//    }

}
