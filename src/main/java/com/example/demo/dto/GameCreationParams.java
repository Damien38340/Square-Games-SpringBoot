package com.example.demo.dto;


import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.UUID;

public record GameCreationParams(String gameType,
                                 int playerCount,
                                 int boardSize,
                                 UUID gameId,
                                 GameStatus gameStatus) {

}
