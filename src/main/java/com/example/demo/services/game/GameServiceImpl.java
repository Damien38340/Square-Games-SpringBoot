package com.example.demo.services.game;

import com.example.demo.dto.GamePositionDTO;
import com.example.demo.entities.*;
import com.example.demo.plugins.GamePlugin;
import com.example.demo.services.game.dao.JPAGameDao;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private List<GamePlugin> gamePluginList;

    @Autowired
    JPAGameDao jpaGameDao;

    @Override
    public Game instanceGame(String gameType) {

        Game game = gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .map(gamePlugin -> gamePlugin.createGame())
                .findFirst()
                .orElse(null);
        if (game != null) {
            jpaGameDao.saveGame(game);
        }
        return game;
    }

    @Override
    public Optional<Game> getGameById(String gameId) {
        return jpaGameDao.getGameById(gameId);
    }

    @Override
    public GameStatus getGameStatus(String gameId) {
        return jpaGameDao.getGameStatus(gameId);
    }


    @Override
    public String deleteGame(String gameId) {
        jpaGameDao.deleteGame(gameId);
        return gameId;
    }

    @Override
    public List<Game> getAllGames() {
      return jpaGameDao.getAllGames();
    }

    @Override
    public void getTokenWithName(String game, String tokenName) {

    }

//    @Override
//    public void getTokenWithName(String gameId, String tokenName) {
//        Game game = getGameById(gameId);
//        Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
//                .flatMap(Collection::stream)
//                .filter(t -> t.getName().equals(tokenName))
//                .filter(t -> t.canMove())
//                .findFirst();
//    }

    @Override
    public void moveTo(Token token, GamePositionDTO position) throws InvalidPositionException {
        CellPosition tokenPosition = new CellPosition(position.x(), position.y());
        token.moveTo(tokenPosition);
    }


}
