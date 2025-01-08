package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String tokenName;

    private int coordinateX;

    private int coordinateY;

    private boolean isPlayed;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity gameEntity;

    public GameEntity getGame() {
        return gameEntity;
    }

    public void setGame(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public PlayerEntity getPlayer() {
        return playerEntity;
    }

    public void setPlayer(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }


}
