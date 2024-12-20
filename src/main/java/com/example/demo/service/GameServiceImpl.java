package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {


    @Autowired
    private List<GamePlugin> gamePluginList;

    private Map<String, Game> activeGames = new HashMap<>();

    @Override
    public Game instanceGame(String gameType) {

        return gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .findFirst()
                .map(gamePlugin -> {
                    Game game = gamePlugin.createGame();
                    String gameId = UUID.randomUUID().toString();
                    activeGames.put(gameId, game);
                    return game;
                })
                .orElse(null);
    }

    @Override
    public Game getGameById(String gameId) {
        return activeGames.get(gameId);
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Game game = activeGames.get(gameId);
        return game != null ? game.getStatus() : null;
    }

    @Override
    public boolean deleteGame(String gameId) {
        return activeGames.remove(gameId) != null;
    }

    @Override
    public List<GamePlugin> getAllGames() {
        return gamePluginList;
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
