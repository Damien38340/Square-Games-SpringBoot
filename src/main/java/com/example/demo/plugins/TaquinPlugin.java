package com.example.demo.plugins;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePlugin {

    private final TaquinGameFactory taquinGame = new TaquinGameFactory();
    private final String gameType;

    public TaquinPlugin() {
        this.gameType = "15 puzzle";
    }


    @Autowired
    private MessageSource messageSource;

    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.name", null, locale);
    }

    @Override
    public Game createGame() {
        return taquinGame.createGame(defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public String getGameType() {
        return gameType;
    }


}