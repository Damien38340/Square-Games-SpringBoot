package com.example.demo.entities;

import fr.le_campus_numerique.square_games.engine.GameStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String factoryId;

    @NotNull
    private int boardSize;

    private GameStatus status;

    @OneToMany
    @JoinColumn(name = "player_id")
    private Set<PlayerEntity> playerEntity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Set<PlayerEntity> getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(Set<PlayerEntity> playerEntity) {
        this.playerEntity = playerEntity;
    }

    public Set<PlayerEntity> getPlayer() {
        return playerEntity;
    }

    public void setPlayer(Set<PlayerEntity> playerEntity) {
        this.playerEntity = playerEntity;
    }

}
