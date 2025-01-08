package com.example.demo.services.game;

import com.example.demo.entities.GameEntity;
import com.example.demo.entities.GameUserEntity;
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
        }
        assert game != null;
        saveGame(game);
        return game;
    }

    public void saveGame(Game game) {
        GameEntity gameEntity = new GameEntity();

        gameEntity.setId(game.getId());
        gameEntity.setFactoryId(game.getFactoryId());
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setStatus(game.getStatus());

        gameRepository.save(gameEntity);
    }

    @Override
    public Game getGameById(String gameId) {
        return activeGames.get(UUID.fromString(gameId));
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Game game = activeGames.get(UUID.fromString(gameId));
        return game != null ? game.getStatus() : null;
    }

    @Override
    public boolean deleteGame(String gameId) {
        return activeGames.remove(UUID.fromString(gameId)) != null;
    }

    @Override
    public List<GamePlugin> getAllGames() {
        findAllGames();
        return gamePluginList;
    }

    public List<GameEntity> findAllGames() {
        return gameRepository.findAll();
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
