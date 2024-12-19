package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Locale;

public interface GamePlugin {

    @NotBlank String getName(Locale locale);

    @NotNull Game createGame();

    String getGameType();

}
