package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity gameEntity;

    @OneToMany
    @JoinColumn(name = "token_id")
    private Set<TokenEntity> tokenEntity;

    public GameEntity getGame() {
        return gameEntity;
    }

    public void setGame(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    public Set<TokenEntity> getToken() {
        return tokenEntity;
    }

    public void setToken(Set<TokenEntity> tokenEntity) {
        this.tokenEntity = tokenEntity;
    }

}
