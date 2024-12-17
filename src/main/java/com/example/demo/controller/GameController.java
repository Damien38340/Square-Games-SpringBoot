package com.example.demo.controller;

import com.example.demo.service.GameCatalog;
import com.example.demo.service.GameService;
import com.example.demo.DTO.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

@Autowired
private GameCatalog gameCatalog;

@Autowired
private GameService gameService;


    @PostMapping("/games")
        public Game createGame(@RequestBody GameCreationParams gameCreationParams) {
        return gameService.createGame(gameCreationParams.gameType,gameCreationParams.playerCount,gameCreationParams.boardSize);
    }

    @GetMapping("/games/{gameId}")
    public GameFactory getGame(@PathVariable String gameId){
       return gameCatalog.getGameFactory(gameId);
    }

    @GetMapping("/game/{gameStatus}")
    public ResponseEntity<String> getGameStatus(@PathVariable String gameStatus){
        GameStatus status = gameCatalog.getGameStatus(gameStatus);

        if (status != null){
            return ResponseEntity.ok("Game status: " + status);
        }
        else {
         return ResponseEntity.status(400)
                 .body("Invalid game status" + gameStatus + "Allowed values are SETUP, ONGOING, TERMINATED.");
        }
    }

    @DeleteMapping("/games/{gameId}")
    public boolean deleteGame(@PathVariable String gameId){
        return gameCatalog.deleteGame(gameId);
    }

}
