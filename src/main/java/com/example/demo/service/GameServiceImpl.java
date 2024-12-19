package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    private final Map<String, Game> games = new HashMap<>(); // Active games tracked by ID
    private final Map<String, GameFactory> gameFactories = new HashMap<>(); // Map game identifiers to their factories

    @Autowired
    private List<GamePlugin> gamePlugins;

    public GameServiceImpl(GameCatalog gameCatalog) {
        // Initialize game factories from the catalog's identifiers
        for (String gameId : gameCatalog.getGameIdentifiers()) {
            switch (gameId) {
                case "tictactoe" -> gameFactories.put(gameId, new TicTacToeGameFactory());
                case "connect4" -> gameFactories.put(gameId, new ConnectFourGameFactory());
                case "15 puzzle" -> gameFactories.put(gameId, new TaquinGameFactory());
                default -> throw new IllegalArgumentException("Unknown game ID: " + gameId);
            }
        }
    }

    @Override
    public Game getGameById(String gameId) {
        return games.get(gameId);
    }

    @Override
    public String createGame(String gameName, int numOfPlayers, int boardSize) {
        GameFactory factory = gameFactories.get(gameName);
        if (factory == null) {
            throw new IllegalArgumentException("Invalid game name: " + gameName);
        }

        Game game = factory.createGame(numOfPlayers, boardSize);
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, game);

        return gameId;
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Game game = games.get(gameId);
        return game != null ? game.getStatus() : null;
    }

    @Override
    public boolean deleteGame(String gameId) {
        return games.remove(gameId) != null;
    }

    @Override
    public Collection<Game> getAllGames(){
        return games.values();
    }

    private static Token getTokenWithName(Game game, String tokenName) {
        return Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
                .flatMap(Collection::stream)
                .filter(t -> t.getName().equals(tokenName))
                .filter(t -> t.canMove())
                .findFirst()
                .orElse(null);
    }

}
