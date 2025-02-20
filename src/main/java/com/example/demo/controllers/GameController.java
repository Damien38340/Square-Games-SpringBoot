package com.example.demo.controllers;

import com.example.demo.dto.GamePositionDTO;
import com.example.demo.entities.GameEntity;
import com.example.demo.services.game.GameService;
import com.example.demo.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/homepage")
    public String accessGamePage(Authentication authentication) {
        if (authentication != null) {
            return "Welcome to the Game, " + authentication.getName() + "!";
        } else {
            return "Unauthorized access.";
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameCreationParams gameCreationParams) {

        return ResponseEntity.ok(gameService.instanceGame(gameCreationParams.gameType()));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Optional<Game>> getGame(@PathVariable String gameId) {
        if (gameService.getGameById(gameId) == null) {
            ResponseEntity.ok("Game with ID " + gameId + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @GetMapping("/status/{gameId}")
    public ResponseEntity<String> getGameStatus(@PathVariable String gameId) {
        GameStatus status = gameService.getGameStatus(gameId);

        if (status != null) {
            return ResponseEntity.ok("Game status: " + status);
        } else {
            return ResponseEntity.status(400)
                    .body("Game with ID " + gameId + " not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
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

    @PutMapping("/{gameId}/tokens/{tokenName}/position")
    public ResponseEntity<String> setTokenPosition(@RequestBody GamePositionDTO positionDTO, @PathVariable String gameId, @PathVariable String tokenName) {
        gameService.getTokenWithName(gameId, tokenName);
        return ResponseEntity.ok("Token " + tokenName + " from " + gameId + " has been moved to " + positionDTO);
    }

}
