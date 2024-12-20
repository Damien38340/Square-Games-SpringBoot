package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin implements GamePlugin {


    private final ConnectFourGameFactory connectFourGame = new ConnectFourGameFactory();
    private final String gameType;

    public ConnectFourPlugin() {
        this.gameType = "connect4";
    }


    @Autowired
    private MessageSource messageSource;

    @Value("${game.connect4.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.connect4.default-board-size}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connect4.name", null, locale);
    }

    @Override
    public Game createGame() {
        return connectFourGame.createGame(defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public String getGameType() {
        return gameType;
    }

}
