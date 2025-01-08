package com.example.demo.controllers;

import com.example.demo.services.game.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/list")
    public Collection<String> getGames() {
        return gameCatalog.getGameIdentifiers();
    }

}
