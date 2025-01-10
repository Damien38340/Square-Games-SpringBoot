package com.example.demo.services.game.dao;

import com.example.demo.entities.GameEntity;
import com.example.demo.entities.TokenEntity;
import com.example.demo.plugins.GamePlugin;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.TokenRepository;
import fr.le_campus_numerique.square_games.engine.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JPAGameDao implements GameDAO {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void saveGame(Game game) {
        GameEntity gameEntity = new GameEntity();

        gameEntity.setId(String.valueOf(game.getId()));
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setFactoryId(game.getFactoryId());
        gameEntity.setPlayerIds(game.getPlayerIds().toString());

        for (Token token : game.getBoard().values()) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setOwnerId(String.valueOf(token.getOwnerId()));
            tokenEntity.setTokenName(token.getName());
            tokenEntity.setCoordinateX(token.getPosition().x());
            tokenEntity.setCoordinateY(token.getPosition().y());
            tokenEntity.setPlayed(true);
//            gameEntity.getTokens().add(tokenEntity);
            tokenRepository.save(tokenEntity);
        }

        for (Token token : game.getRemainingTokens()) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setOwnerId(String.valueOf(token.getOwnerId()));
            tokenEntity.setTokenName(token.getName());
            tokenEntity.setPlayed(false);
//            gameEntity.getTokens().add(tokenEntity);
            tokenRepository.save(tokenEntity);

        }
        gameRepository.save(gameEntity);
    }

    @Override
    public Optional<Game> getGameById(String gameId) {

        Optional <GameEntity> optionalGameEntity = gameRepository.findById(gameId);

        GameEntity gameEntity = optionalGameEntity.get();

        GamePlugin gamePlugin;
        GameFactory gameFactory;

        int boardSize = gameEntity.getBoardSize();

        List<UUID> playerIds = getListOfPlayers();
        Collection<TokenPosition<UUID>> boardTokens = getBoardTokens();
        Collection<TokenPosition<UUID>> removedTokens = getRemovedTokens();

        return Optional.empty();
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        return gameEntity.get().getStatus();
    }

    @Override
    public void deleteGame(String gameId) {
        gameRepository.deleteById(gameId);
    }

    @Override
    public List<Game> getAllGames() {

        List<GameEntity> allGamesEntities = gameRepository.findAll();

         allGamesEntities.forEach(gameEntity -> {
            gameRepository.findAll();
        });

         return List.of();

    }


    @Override
    public List<UUID> getListOfPlayers() {
        return List.of();
    }

    @Override
    public Collection<TokenPosition<UUID>> getBoardTokens() {
        return List.of();
    }

    @Override
    public Collection<TokenPosition<UUID>> getRemovedTokens() {
        return List.of();
    }

}
