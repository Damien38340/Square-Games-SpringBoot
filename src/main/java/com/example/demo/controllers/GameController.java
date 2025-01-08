package com.example.demo.controllers;

import com.example.demo.entities.GameEntity;
import com.example.demo.plugins.GamePlugin;
import com.example.demo.services.game.GameService;
import com.example.demo.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameCreationParams gameCreationParams) {
        GameEntity toAdd = new GameEntity();
        toAdd.setFactoryId(gameCreationParams.gameType());
        toAdd.setBoardSize(gameCreationParams.boardSize());
        toAdd.setId(gameCreationParams.gameId());
        toAdd.setStatus(gameCreationParams.gameStatus());

        return ResponseEntity.ok(gameService.instanceGame(toAdd.getFactoryId()));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @GetMapping("/status/{gameId}")
    public ResponseEntity<String> getGameStatus(@PathVariable String gameId) {
        GameStatus status = gameService.getGameStatus(gameId);

        if (status != null) {
            return ResponseEntity.ok("Game status: " + status);
        } else {
            return ResponseEntity.status(400)
                    .body("Invalid game status" + gameId + "Allowed values are SETUP, ONGOING, TERMINATED.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GamePlugin>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        if (gameService.deleteGame(gameId) != null) {
            return ResponseEntity.ok("Game with ID " + gameId + " deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Game with ID " + gameId + " not found.");
        }
    }

}
