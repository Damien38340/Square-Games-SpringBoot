package com.example.demo.controller;

import com.example.demo.service.GameCatalogImpl;
import com.example.demo.GameCreationParams;
import com.example.demo.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

@Autowired
private GameCatalogImpl gameCatalog;
private GameService gameService;

    @PostMapping("/games")
        public Game createGame(@RequestBody GameCreationParams gameCreationParams) {
        return gameFactory.createGame(gameCreationParams.playerCount, gameCreationParams.boardSize);
    }

    @GetMapping("/games/{gameId}")
    public GameFactory getGame(@PathVariable String gameId){
       return gameCatalog.getGameFactory(gameId);
    }

    @GetMapping("/game/{gameStatus}")
    public GameStatus getGameStatus(@PathVariable String gameStatus){
        return gameCatalog.getGameStatus(gameStatus);
    }

}
