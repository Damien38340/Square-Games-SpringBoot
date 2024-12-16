package com.example.demo.service;

import com.example.demo.GameCatalog;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToe = new TicTacToeGameFactory();
    private final ConnectFourGameFactory connectFour = new ConnectFourGameFactory();
    private final TaquinGameFactory taquin = new TaquinGameFactory();

    private final Collection<String> games = new ArrayList<>();

    @Override
    public Collection<String> getGameIdentifiers() {

        String ticTacToeId = ticTacToe.getGameFactoryId();
        String connectFourId = connectFour.getGameFactoryId();
        String taquinId = taquin.getGameFactoryId();

        games.add(ticTacToeId);
        games.add(connectFourId);
        games.add(taquinId);

        return games;
    }

    public GameFactory getGameFactory(String gameId) {
        return switch (gameId) {
            case "tictactoe" -> ticTacToe;
            case "connectfour" -> connectFour;
            case "taquin" -> taquin;
            default -> null;
        };
    }

    public GameStatus getGameStatus(String gameStatus) {
        return switch (gameStatus) {
            case "SETUP": yield GameStatus.SETUP;
            case "ONGOING": yield GameStatus.ONGOING;
            case "TERMINATED": yield GameStatus.TERMINATED;
            default: yield null;
        };
    }
}
