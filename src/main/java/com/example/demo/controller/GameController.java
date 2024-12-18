package com.example.demo.controller;

import com.example.demo.service.GameService;
import com.example.demo.DTO.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<String> createGame(@RequestBody GameCreationParams gameCreationParams) {
        return ResponseEntity.ok(gameService.createGame(gameCreationParams.gameType, gameCreationParams.playerCount, gameCreationParams.boardSize));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @GetMapping("/status/{gameStatus}")
    public ResponseEntity<String> getGameStatus(@PathVariable String gameStatus) {
        GameStatus status = gameService.getGameStatus(gameStatus);

        if (status != null) {
            return ResponseEntity.ok("Game status: " + status);
        } else {
            return ResponseEntity.status(400)
                    .body("Invalid game status" + gameStatus + "Allowed values are SETUP, ONGOING, TERMINATED.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        if (gameService.deleteGame(gameId)) {
            return ResponseEntity.ok("Game with ID " + gameId + " deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Game with ID " + gameId + " not found.");
        }
    }

}
