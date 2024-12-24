package com.example.demo.service.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class MySQLGameUserDAO implements GameUserDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/square_games";
    private static final String DB_USER = "testuser";
    private static final String DB_PASSWORD = "testpassword";

    private Connection connection;

    public MySQLGameUserDAO() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database ", e);
        }
    }

    @Override
    public List<GameUser> getAllUsers() {
        String query = "SELECT * FROM USERS";
        List<GameUser> gameUsers = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                GameUser gameUser = new GameUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                gameUsers.add(gameUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameUsers;
    }

    @Override
    public GameUser getUserById(int id) {
        String query = "SELECT * FROM USERS WHERE id = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GameUser(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(GameUser gameUser) {
        String query = "INSERT INTO USERS (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, gameUser.getName());
            stmt.setString(2, gameUser.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(GameUser gameUser) {
        String query = "UPDATE USERS SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, gameUser.getName());
            stmt.setString(2, gameUser.getEmail());
            stmt.setInt(3, gameUser.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM USERS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the connection when the DAO is no longer needed
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
