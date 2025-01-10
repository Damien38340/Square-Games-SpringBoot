package com.example.demo.services.game.dao;

import com.example.demo.dto.GamePositionDTO;
import com.example.demo.entities.GameEntity;
import com.example.demo.entities.TokenEntity;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.TokenPosition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface GameDAO {

    public void saveGame(Game game);

    public Optional<Game> getGameById(String gameId);

    public GameStatus getGameStatus(String gameId);

    public void deleteGame(String gameId);

    public List<Game> getAllGames();

    public List<UUID> getListOfPlayers();

    public Collection<TokenPosition<UUID>> getBoardTokens();

    public Collection<TokenPosition<UUID>> getRemovedTokens();


}