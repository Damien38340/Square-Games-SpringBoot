package com.example.demo;

import fr.le_campus_numerique.square_games.engine.Game;

public interface GameService {

Game createGame(String gameName, int numOfPlayers, int boardSize);

}
