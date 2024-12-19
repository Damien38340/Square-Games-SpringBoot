package com.example.demo.DTO;


public class GameCreationParams {

    public String gameType;
    public int playerCount;
    public int boardSize;
    private String gameId;
    private String gameStatus;

    public GameCreationParams(String gameType, int playerCount, int boardSize, String gameId, String gameStatus) {
        this.gameType = gameType;
        this.playerCount = playerCount;
        this.boardSize = boardSize;
        this.gameId = gameId;
        this.gameStatus = gameStatus;
    }

}
