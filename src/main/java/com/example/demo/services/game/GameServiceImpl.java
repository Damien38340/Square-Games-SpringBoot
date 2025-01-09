package com.example.demo.services.game;

import com.example.demo.entities.*;
import com.example.demo.plugins.GamePlugin;
import com.example.demo.repositories.GameRepository;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private List<GamePlugin> gamePluginList;
    @Autowired
    private GameRepository gameRepository;

    private Map<UUID, Game> activeGames = new HashMap<>();

    @Override
    public Game instanceGame(String gameType) {

        Game game = gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .map(gamePlugin -> gamePlugin.createGame())
                .findFirst()
                .orElse(null);
        if (game != null) {
            activeGames.put(game.getId(), game);
            GameEntity gameEntity = new GameEntity();

            gameEntity.setId(game.getId().toString());
            gameEntity.setFactoryId(game.getFactoryId());
            gameEntity.setBoardSize(game.getBoardSize());
            gameEntity.setStatus(game.getStatus());

            gameRepository.save(gameEntity);
        }
        return game;
    }


    @Override
    public Game getGameById(String gameId) {
        gameRepository.findById(gameId);
        return activeGames.get(UUID.fromString(gameId));
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Game game = activeGames.get(UUID.fromString(gameId));

        if (game != null) {
            gameRepository.findById(gameId);
            return game.getStatus();
        }
        return null;
    }


    @Override
    public Game deleteGame(String gameId) {
        if (activeGames.containsKey(UUID.fromString(gameId))) {
            gameRepository.deleteById(gameId);
            return activeGames.remove(UUID.fromString(gameId));
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getAllGames() {
        // Fetch all games from the repository
        List<GameEntity> gameEntities = gameRepository.findAll();

        // Map GameEntity to a response object containing ID and game details
        return gameEntities.stream().map(gameEntity -> {
            Map<String, Object> gameInfo = new HashMap<>();
            gameInfo.put("id", gameEntity.getId());
            gameInfo.put("type", gameEntity.getFactoryId());
            gameInfo.put("status", gameEntity.getStatus());
            return gameInfo;
        }).toList();
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
