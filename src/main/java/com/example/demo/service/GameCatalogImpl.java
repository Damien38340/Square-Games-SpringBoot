package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final Collection<String> games = new ArrayList<>();

    @Override
    public Collection<String> getGameIdentifiers() {
        if (games.isEmpty()) {
            games.add(new TicTacToeGameFactory().getGameFactoryId());
            games.add(new ConnectFourGameFactory().getGameFactoryId());
            games.add(new TaquinGameFactory().getGameFactoryId());
        }
        return games;
    }

}
