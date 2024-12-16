package com.example.demo;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGame;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
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
}
